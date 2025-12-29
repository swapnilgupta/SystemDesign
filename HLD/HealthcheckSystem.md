# Health Check System - High Level Design

## Problem Statement

Design a system to periodically run diagnostics and gather health data from a large fleet of servers (1000s).

## Requirements

- Monitor 1000s of servers across multiple locations
- Run 100s of diagnostic scripts hourly
- Report health status back to operators
- Support AP (Availability + Partition tolerance) from CAP theorem

## Architecture

```mermaid
flowchart TB
    subgraph Servers["Server Fleet"]
        S1[Server 1]
        S2[Server 2]
        S3[Server 3]
        S4[Server N...]
    end
    
    subgraph Aggregation["Aggregation Layer"]
        AG[Data Aggregator]
    end
    
    subgraph Storage["Storage Layer"]
        DB[(Time Series DB)]
        CACHE[(Redis Cache)]
    end
    
    subgraph Application["Application Layer"]
        ES[Event Service]
        QS[Query Service]
    end
    
    subgraph Consumers["Consumers"]
        OP((Operators))
        DASH[Dashboard]
        ALERT[Alerting System]
    end
    
    S1 --> AG
    S2 --> AG
    S3 --> AG
    S4 --> AG
    
    AG --> DB
    DB --> ES
    DB --> QS
    
    ES --> OP
    QS --> OP
    QS --> DASH
    ES --> ALERT
```

## Data Flow

```mermaid
sequenceDiagram
    participant S as Server
    participant A as Aggregation Layer
    participant D as Database
    participant E as Event Service
    participant O as Operator
    
    S->>A: Push diagnostics data
    A->>D: Store time series data
    D->>E: Trigger on anomaly
    E->>O: Send alert notification
    O->>D: Query historical data
```

## Key Design Decisions

1. **Diagnostics Location**: Centralized service remotely runs diagnostics
2. **Data Storage**: Time series database for historical data
3. **Schema**: Simple `(server_ip, diagnostic_name, result, timestamp)`

## Data Model

```mermaid
erDiagram
    SERVER {
        string server_id PK
        string ip_address
        string location
        string status
    }
    
    DIAGNOSTIC {
        string diagnostic_id PK
        string name
        string script_path
        int frequency_mins
    }
    
    HEALTH_DATA {
        string id PK
        string server_id FK
        string diagnostic_id FK
        timestamp recorded_at
        string result
        string status
    }
    
    SERVER ||--o{ HEALTH_DATA : generates
    DIAGNOSTIC ||--o{ HEALTH_DATA : produces
```

## Scalability Considerations

- **Load Balancing**: Distribute diagnostic requests across aggregation nodes
- **Partitioning**: Shard data by server location/region
- **Caching**: Cache recent health status in Redis
- **Monitoring**: Canaries, dashboards, and alarms for system health

