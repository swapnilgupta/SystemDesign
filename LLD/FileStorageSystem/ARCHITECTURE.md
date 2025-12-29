# File Storage System - Architecture

## Class Diagram

```mermaid
classDiagram
    class FileSystem {
        <<interface>>
        +ls() void
        +search(Filter) List~File~
    }
    
    class Directory {
        -name: String
        -children: List~FileSystem~
        +ls() void
        +search(Filter) List~File~
        +addChild(FileSystem) void
    }
    
    class File {
        -name: String
        -size: int
        -extension: String
        +ls() void
        +search(Filter) List~File~
        +getSize() int
        +getName() String
    }
    
    class Filter {
        <<interface>>
        +apply(File) boolean
    }
    
    class NameFilter {
        -pattern: String
        +apply(File) boolean
    }
    
    class SizeFilter {
        -minSize: int
        -maxSize: int
        +apply(File) boolean
    }
    
    class ExtensionFilter {
        -extension: String
        +apply(File) boolean
    }
    
    FileSystem <|.. Directory
    FileSystem <|.. File
    Directory "1" *-- "*" FileSystem : contains
    Filter <|.. NameFilter
    Filter <|.. SizeFilter
    Filter <|.. ExtensionFilter
    FileSystem ..> Filter : uses
```

## Design Patterns

- **Composite Pattern**: `Directory` and `File` implement the same `FileSystem` interface, allowing uniform treatment
- **Strategy Pattern**: Different `Filter` implementations for flexible file searching

## Design Principles

1. **Single Responsibility**: Each class has one job
2. **Open/Closed**: New filters can be added without modifying existing code
3. **Dependency Inversion**: High-level modules depend on abstractions (`FileSystem`, `Filter`)

