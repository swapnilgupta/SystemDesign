# Trending Posts System - High Level Design

## Problem Statement

Design a system to show top K trending posts on a social media platform with 1 billion users.

## Requirements

### Functional
- Get top K trending posts (configurable duration, default 24hrs)
- Posts contain text + images (image URLs)
- Trending = likes + comments + reposts (equal weight)

### Non-Functional
- ~10^6 QPS (Daily Active Users: 1B)
- Eventual consistency acceptable
- High availability (AP > CP)
- Super fast reads via CDN/Cache

## Capacity Estimation

```
Daily Active Users: 1 billion
Posts/day: 200 million (20% posting rate)
Storage/day: 200M × (metadata + content) = ~50TB
```

## Architecture

```mermaid
flowchart TB
    subgraph Ingestion["Data Ingestion"]
        K[Kafka]
        KC[Kafka Consumer]
    end
    
    subgraph Processing["Stream Processing"]
        S1[Spark Worker 1]
        S2[Spark Worker 2]
        S3[Spark Worker N]
    end
    
    subgraph Aggregation["Aggregation Layer"]
        MS[Merge Sort]
    end
    
    subgraph Storage["Storage Layer"]
        DB[(Cassandra)]
        REDIS[(Redis Cache)]
    end
    
    subgraph API["API Layer"]
        GW{API Gateway}
    end
    
    subgraph Client["Client"]
        C((Users))
    end
    
    K --> KC
    KC --> S1
    KC --> S2
    KC --> S3
    
    S1 --> MS
    S2 --> MS
    S3 --> MS
    
    MS --> DB
    DB --> REDIS
    
    C --> GW
    GW --> REDIS
```

## Data Flow

```mermaid
sequenceDiagram
    participant U as User
    participant K as Kafka
    participant S as Spark
    participant DB as Database
    participant R as Redis
    participant API as API Gateway
    
    U->>K: Like/Comment/Repost event
    K->>S: Stream event
    S->>S: Count by post_id (window)
    S->>DB: Update scores
    DB->>R: Cache top K
    U->>API: GET /v1/posts/trending
    API->>R: Fetch top K
    R->>U: Return trending posts
```

## Stream Processing Logic

```mermaid
flowchart LR
    subgraph Window1["Window 1 (1hr)"]
        W1[A:15, B:10, C:5, D:2]
    end
    
    subgraph Window2["Window 2 (1hr)"]
        W2[C:8, D:6, A:2, B:1]
    end
    
    subgraph Window3["Window 3 (1hr)"]
        W3[B:7, A:5, D:3, C:2]
    end
    
    subgraph Merge["Merge & Sort"]
        M[MergeSort]
    end
    
    subgraph Result["Top K"]
        R[A:22, B:18, C:15, D:11]
    end
    
    W1 --> M
    W2 --> M
    W3 --> M
    M --> R
```

## Data Model

```mermaid
erDiagram
    POST {
        string post_id PK
        timestamp created_at
        int score
    }
    
    EVENT {
        string event_id PK
        string post_id FK
        string event_type
        timestamp created_at
    }
    
    POST ||--o{ EVENT : receives
```

## API Design

```
GET /v1/posts/trending?k=10&duration=24h
```

**Response:**
```json
{
  "trending_posts": [
    {"post_id": "A", "score": 22, "title": "..."},
    {"post_id": "B", "score": 18, "title": "..."}
  ],
  "timestamp": "2024-01-01T00:00:00Z"
}
```

## Schema

```sql
-- Posts scoring table (Cassandra)
CREATE TABLE trending_posts (
    post_id TEXT,
    timestamp TIMESTAMP,
    score INT,
    PRIMARY KEY (post_id, timestamp)
) WITH CLUSTERING ORDER BY (timestamp DESC);
```

## Key Design Decisions

1. **Stream Processing**: Apache Spark for real-time aggregation
2. **Message Queue**: Kafka for event streaming
3. **Storage**: Cassandra for write-heavy workload
4. **Caching**: Redis for fast reads with TTL
5. **CDN**: For global distribution of trending content

