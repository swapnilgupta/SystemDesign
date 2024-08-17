# Low-Level Design (LLD) Problem: File System Design

## Problem Statement

Design a file system that supports the following functionalities:

1. **Create a New File**: Allow users to create new files in specific directories.
2. **Add Content to a File**: Provide a mechanism to add content to a file.
3. **List Files in a Directory**: List all files and directories present in a specific directory.
4. **Create a Directory**: Allow users to create a new directory.
5. **Search for Files**: Implement a search functionality to locate files by their name.
6. **Filter Files**: Provide the ability to filter files based on specific criteria (e.g., file name contains a certain substring).
   Design a solution in OOD, extensible solution to implement search filter when given a path to directory and find all files with matching filters like by size or name
## Design Requirements

- **Object-Oriented Principles**: Ensure that the design adheres to OOP principles like the Single Responsibility Principle (SRP), Open/Closed Principle (OCP), and Dependency Inversion Principle (DIP).
- **Composite Design Pattern**: Use the Composite Design Pattern to treat individual files and directories uniformly.
- **Extendability**: The design should allow for easy extension of functionality without modifying the existing codebase.
