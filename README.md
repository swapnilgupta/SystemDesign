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

**AWS Architecture Patterns**: Batch Processing, Content Delivery, E-Commerce, Fault Tolerance & HA, Large Scale Computing, Time Series Processing, File Sync

**System Designs** (`.drawio`): E-Commerce, Health Check System, News Feed, Rule Propagation Service

## Quick Start

```bash
cd LLD/ParkingLot
mvn clean compile
mvn test
```

## Tech Stack

Java 21 • Maven • JUnit 5 • Draw.io

## License

[MIT](LICENSE)
