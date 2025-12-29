# TikTok System - High Level Design

## Overview

TikTok is a social media platform for short-form videos (15s to 1min) including dance, comedy, and educational content.

## Requirements

### Functional
1. User Profile management
2. Video Upload & Processing
3. Video Viewing/Streaming
4. Like, Comment, Share
5. Follow Users
6. Live Streaming
7. Recommendations

### Non-Functional
1. **Security**: End-to-end encryption
2. **Performance**: Fast view/stream latency
3. **Storage**: Handle petabytes of video data
4. **Scalability**: Handle large read/write requests
5. **Latency**: Minimal buffering for video playback

## Capacity Estimation

| Metric | Value |
|--------|-------|
| Monthly Active Users (MAU) | 1 billion |
| Daily Video Uploads | 50 million |
| Requests Per Second (RPS) | ~5,787 |
| Peak RPS | ~11,574 |
| User Profile Storage | 1 PB |
| Daily Video Storage | 1 PB |
| Monthly Video Storage | 30 PB |

## Architecture

```mermaid
flowchart TB
    subgraph Client["Client Layer"]
        U((User))
    end
    
    subgraph Gateway["API Gateway"]
        LB[Load Balancer]
        AG{API Gateway}
    end
    
    subgraph Services["Microservices"]
        VUS[Video Upload Service]
        VSS[Video Streaming Service]
        UIS[User Interaction Service]
        RS[Recommendation Service]
        FOS[Fan-out Service]
    end
    
    subgraph Processing["Video Processing"]
        Q[(Message Queue)]
        ENC[Encoder/Transcoder]
    end
    
    subgraph Storage["Storage Layer"]
        PBS[(Primary Blob Storage)]
        VBS[(Video Blob Storage)]
        MDB[(Metadata DB)]
        FC[(Feed Cache)]
    end
    
    U --> LB
    LB --> AG
    
    AG --> VUS
    AG --> VSS
    AG --> UIS
    
    VUS --> PBS
    PBS --> Q
    Q --> ENC
    ENC --> VBS
    
    UIS --> MDB
    VSS --> FC
    RS --> MDB
    RS --> VBS
    RS --> FC
    FOS --> FC
```

## Video Upload Flow

```mermaid
sequenceDiagram
    participant U as User
    participant API as API Gateway
    participant VUS as Video Upload Service
    participant PBS as Primary Storage
    participant Q as Queue
    participant ENC as Encoder
    participant VBS as Video Storage
    
    U->>API: Upload Video
    API->>VUS: Forward request
    VUS->>PBS: Store raw video
    PBS->>Q: Enqueue for processing
    Q->>ENC: Process video
    ENC->>ENC: Transcode to multiple resolutions
    ENC->>VBS: Store processed videos
    VBS-->>U: Video ready notification
```

## Video Streaming Flow

```mermaid
sequenceDiagram
    participant U as User
    participant CDN as CDN
    participant VSS as Video Streaming Service
    participant FC as Feed Cache
    participant RS as Recommendation Service
    participant VBS as Video Storage
    
    U->>CDN: Request video feed
    CDN->>FC: Check cache
    alt Cache hit
        FC-->>CDN: Return cached feed
    else Cache miss
        FC->>RS: Get recommendations
        RS->>VBS: Fetch video metadata
        VBS-->>RS: Video list
        RS-->>FC: Update cache
        FC-->>CDN: Return feed
    end
    CDN-->>U: Stream video (HLS/DASH)
```

## Data Model

```mermaid
erDiagram
    USER {
        string user_id PK
        string username
        string email
        string password_hash
        string profile_picture_url
        string bio
    }
    
    VIDEO {
        string video_id PK
        string user_id FK
        string title
        string description
        timestamp upload_date
        int views_count
        int duration_seconds
    }
    
    SOCIAL_GRAPH {
        string user_id PK
        string follower_id
        string following_id
    }
    
    LIKE {
        string like_id PK
        string user_id FK
        string video_id FK
        timestamp created_at
    }
    
    COMMENT {
        string comment_id PK
        string user_id FK
        string video_id FK
        string text
        timestamp created_at
    }
    
    USER ||--o{ VIDEO : uploads
    USER ||--o{ LIKE : gives
    USER ||--o{ COMMENT : writes
    VIDEO ||--o{ LIKE : receives
    VIDEO ||--o{ COMMENT : has
    USER ||--o{ SOCIAL_GRAPH : has
```

## API Endpoints

```
POST /api/user/register
POST /api/user/login
POST /api/video/upload
GET  /api/video/{videoId}
POST /api/videos/{videoId}/like
POST /api/videos/{videoId}/dislike
POST /api/videos/{videoId}/comment
GET  /api/feed
GET  /api/user/{userId}/followers
POST /api/user/{userId}/follow
```

## Key Components

### 1. Video Processing Pipeline
- **Encoder/Transcoder**: Converts videos to multiple resolutions (240p, 480p, 720p, 1080p)
- **HLS/DASH**: Adaptive bitrate streaming based on network conditions

### 2. Recommendation Engine
- Machine learning models for personalized content
- Collaborative filtering + content-based filtering

### 3. Feed Cache
- Redis clusters for fast feed retrieval
- Fan-out on write for active users
- Fan-out on read for celebrity accounts

### 4. Content Delivery Network (CDN)
- Edge caching for video content
- Geographic distribution for low latency

## Scalability Strategies

1. **Horizontal Scaling**: Microservices architecture
2. **Database Sharding**: By user_id or video_id
3. **Caching**: Multi-layer (CDN → Redis → DB)
4. **Async Processing**: Message queues for video processing
5. **Read Replicas**: For read-heavy workloads

