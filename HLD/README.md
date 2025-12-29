# High Level Design (HLD) Documentation

This directory contains high-level system design documentation with architecture diagrams rendered using Mermaid.

## System Designs

| System | Description | Status |
|--------|-------------|--------|
| [Health Check System](HealthcheckSystem.md) | Distributed server health monitoring | ✅ Complete |
| [Rule Propagation Service](RulePropagationService.md) | Configuration distribution to 60K+ servers | ✅ Complete |
| [TikTok System](TikTokSystem.md) | Short-form video platform design | ✅ Complete |
| [Trending Posts](TrendingPosts.md) | Top K trending posts on social media | ✅ Complete |

## AWS Training HLD (PDF References)

Additional architecture patterns from AWS training materials:

| Topic | File |
|-------|------|
| Batch Processing | `AWS Training HLD/001 Architecture - Batch Processing.pdf` |
| Content & Media Serving | `AWS Training HLD/002 Architecture - Content and Media Serving.pdf` |
| E-Commerce (Frontend) | `AWS Training HLD/003 Architecture - eCommerce part 1 - Front End.pdf` |
| E-Commerce (Checkout) | `AWS Training HLD/003-02 Architecture - eCommerce part 2 - Checkout.pdf` |
| E-Commerce (Marketing) | `AWS Training HLD/003-03 Architecture - eCommerce part 3 - Marketing.pdf` |
| Fault Tolerance & HA | `AWS Training HLD/004 Architecture - Fault Tolerance and HA.pdf` |
| Large Scale Computing | `AWS Training HLD/005 Architecture - Large Scale Computing and Data Sets.pdf` |
| Online Games | `AWS Training HLD/006 Architecture - Online Games.pdf` |
| Time Series Processing | `AWS Training HLD/007 Architecture - Time Series Processing.pdf` |
| File Synchronization | `AWS Training HLD/008 Architecture - File Synchronization Service.pdf` |
| Advertisement Serving | `AWS Training HLD/Architecture - Advertisement Serving.pdf` |
| Disaster Recovery | `AWS Training HLD/Architecture - DR for Local Apps.pdf` |
| Financial Grid Computing | `AWS Training HLD/Architecture - Financial Grid Computing.pdf` |
| Log Analysis | `AWS Training HLD/Architecture - Log Analysis.pdf` |
| Media Sharing | `AWS Training HLD/Architecture - Media Sharing.pdf` |
| Web App Hosting | `AWS Training HLD/Architecture - Web App Hosting.pdf` |

## Diagram Format

All diagrams use [Mermaid](https://mermaid.js.org/) syntax which renders natively on GitHub. Common diagram types include:

- **Flowcharts**: System architecture and data flow
- **Sequence Diagrams**: API interactions and request flows
- **ER Diagrams**: Data models and relationships
- **Class Diagrams**: Object relationships

## Contributing

When adding new system designs:

1. Create a new `.md` file with the system name
2. Include sections for: Problem Statement, Requirements, Architecture, Data Flow, Data Model
3. Use Mermaid diagrams for visual representations
4. Update this README with a link to the new design

