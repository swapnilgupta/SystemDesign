# System Design

Production-quality **Low-Level Design (LLD)** and **High-Level Design (HLD)** implementations demonstrating object-oriented design principles and scalable architecture patterns.

## Low-Level Designs

| Project | Description | Patterns |
|---------|-------------|----------|
| [ParkingLot](LLD/ParkingLot/) | Multi-level parking system | Singleton, Strategy, Factory |
| [BookMyShowSystem](LLD/BookMyShowSystem/) | Movie ticket booking | Singleton, Repository |
| [FileStorageSystem](LLD/FileStorageSystem/) | Hierarchical file system | Composite, Strategy |
| [ShoppingCart](LLD/ShoppingCart/) | E-commerce cart with coupons | Decorator |
| [LimitedTimeDeal](LLD/LimitedTimeDeal/) | Flash sale management | Service Layer |
| [AmazonLocker](LLD/AmazonLocker/) | Package locker allocation | Strategy |
| [GoogleCalendarSystem](LLD/GoogleCalendarSystem/) | Calendar & event management | Observer |
| [PizzaStoreSystem](LLD/PizzaStoreSystem/) | Pizza ordering system | Builder, Factory |
| [VotingSystem](LLD/VotingSystem/) | Preferential voting | Strategy |
| [BattleshipGame](LLD/BattleshipGame/) | Battleship game | Strategy |
| [VehicleRentalSystem](LLD/VehicleRentalSystem/) | Vehicle rental platform | Factory |
| [MultiLevelCache](LLD/MultiLevelCache/) | Multi-tier caching | Chain of Responsibility |
| [ProblemSolvingSystem](LLD/ProblemSolvingSystem/) | Coding platform | Strategy |
| [PromocodeSystem](LLD/PromocodeSystem/) | Coupon & discount management | Strategy |
| [LargeNumberArithmetic](LLD/LargeNumberArithmetic/) | Big integer multiplication | Algorithm |

## High-Level Designs

| Design | Description |
|--------|-------------|
| [Health Check System](HLD/HealthcheckSystem.md) | Server fleet health monitoring |
| [Rule Propagation Service](HLD/RulePropagationService.md) | Config distribution to 60K+ servers |
| [TikTok System](HLD/TikTokSystem.md) | Short-form video platform |
| [Trending Posts](HLD/TrendingPosts.md) | Top K trending posts system |

**AWS Architecture Patterns**: Batch Processing, Content Delivery, E-Commerce, Fault Tolerance & HA, Large Scale Computing, Time Series Processing, File Sync ([See HLD/README.md](HLD/README.md))

## Quick Start

```bash
cd LLD/ParkingLot
mvn clean compile
mvn test
```

## Tech Stack

Java 21 • Maven • JUnit 5 • Mermaid

## License

[MIT](LICENSE)
