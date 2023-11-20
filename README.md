# Employee Management System (EMS)

Welcome to the EMS GitHub repository! This application provides a system for managing employees, tasks, and performance metrics. Below, you'll find an overview of the project structure and key components.

## Project Structure
- [Entities](#entities)
- [Controllers](#controllers)
- [Services](#services)
- [Security Configuration](#security-configuration)
- [DTO](#dto)
- [Utilities](#utilities)

## Entities

### Employee
Represents an employee with details such as name, email, phone, department, and salary.

### Performance
Tracks performance metrics of an employee. Shows number of tasks done this month, last month, and two months ago as well
as tasks completition rate (%).

### Role
Defines the roles that users can have within the system.

### Task
Represents tasks assigned to employees, including details like due date, completion date, and status.

### User
Represents system users with details such as name, email, password, and approval status.
Only approved users by Admin can login.

### Status (Enum)
Enumerates task status options like `Completed`, `In_progress`, `Cancelled`, and `Not_assigned`.

## Controllers

- **EmployeeController**: Handles employee-related endpoints.
- **MainController**: Main controller with general endpoints.
- **PerformanceController**: Manages performance-related endpoints.
- **TaskController**: Manages task-related endpoints.
- **UserApprovalController**: Handles user approval-related endpoints.
- **UserRegistrationController**: Manages user registration endpoints.

## Services

- **EmployeeService**: Implements business logic for employee-related operations.
- **PerformanceService**: Manages performance-related business logic.
- **TaskService**: Handles task-related business logic.
- **UserService**: Manages user-related operations, including registration, approval, and deletion.

## Security Configuration

The `SecurityConfiguration` class contains configurations for securing the application, including user authentication and authorization.

## DTO

- **UserRegistrationDto**: Data Transfer Object for user registration.

## Utilities

- **PerformanceCalculator**: Utility class for calculating performance metrics.

Feel free to explore the codebase for a deeper understanding of each component. If you have any questions or suggestions, please don't hesitate to reach out. Happy coding!
