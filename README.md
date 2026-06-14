# Web Management Backend

A Spring Boot backend for managing departments, employees, classes, students, file uploads, login authentication, operation logs, and simple report statistics.

The current runnable Maven module is located in `web-management`.

## Features

- Employee login with JWT authentication
- Department management
- Employee management with work experience records
- Class management
- Student management and violation score updates
- File upload to Tencent Cloud Object Storage
- Operation log recording through AOP
- Statistical report APIs for employee and student data
- Unified API response format
- Global exception handling

## Tech Stack

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- MyBatis
- MySQL
- PageHelper
- Lombok
- JJWT
- Tencent COS SDK
- Maven Wrapper

## Project Structure

```text
web-01/
|-- .gitignore
|-- README.md
`-- web-management/
    |-- pom.xml
    |-- mvnw
    |-- mvnw.cmd
    |-- .mvn/
    |-- src/
    |   |-- main/
    |   |   |-- java/org/starry/webmanagement/
    |   |   |   |-- anno/          # Custom annotations
    |   |   |   |-- aop/           # Operation log and timing aspects
    |   |   |   |-- config/        # Web configuration
    |   |   |   |-- controllers/   # REST controllers
    |   |   |   |-- exception/     # Global exception handling
    |   |   |   |-- filter/        # JWT request filter
    |   |   |   |-- mapper/        # MyBatis mapper interfaces
    |   |   |   |-- pojo/          # Data objects and response wrappers
    |   |   |   |-- service/       # Service interfaces and implementations
    |   |   |   `-- utils/         # JWT, COS, and current-user helpers
    |   |   `-- resources/
    |   |       |-- application.yaml
    |   |       |-- logback.xml
    |   |       `-- org/starry/webmanagement/mapper/ # MyBatis XML mappers
    |   `-- test/
    `-- target/                      # Generated build output, ignored by Git
```

## Requirements

- JDK 17 or later
- MySQL 8.x
- Maven is optional because the project includes Maven Wrapper
- Tencent COS credentials if file upload is used

## Configuration

Main configuration file:

```text
web-management/src/main/resources/application.yaml
```

Configure the database connection:

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/management
    username: root
    password: your_password
```

Tencent COS configuration:

```yaml
tencent:
  cos:
    endpoint: myqcloud.com
    bucketName: your-bucket-name
    region: ap-shanghai
```

Tencent COS credentials are read from environment variables:

```text
SECRETID=your-secret-id
SECRETKEY=your-secret-key
```

For production use, avoid committing real database passwords, JWT secrets, or cloud credentials.

## Run Locally

Enter the Maven module directory:

```powershell
cd E:\JavaStudy\web-01\web-management
```

Compile the project without running tests:

```powershell
.\mvnw.cmd -DskipTests compile
```

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

The service starts on the default Spring Boot port unless another port is configured.

## API Response Format

Most APIs return the unified `Result` object:

```json
{
  "code": 1,
  "msg": "success",
  "data": {}
}
```

Failure example:

```json
{
  "code": 0,
  "msg": "Service not available",
  "data": null
}
```

## Authentication

Login endpoint:

```http
POST /login
```

After login succeeds, the response contains a JWT token. Protected requests should include the token in the request header:

```http
token: <jwt-token>
```

The `TokenFilter` validates this token and stores the current employee id in `CurrentHolder` for the current request thread.

## API Overview

### Authentication

| Method | Path | Description |
| --- | --- | --- |
| POST | `/login` | Employee login and JWT generation |

### Departments

| Method | Path | Description |
| --- | --- | --- |
| GET | `/depts` | List all departments |
| POST | `/depts` | Create a department |
| GET | `/depts/{id}` | Get department details |
| PUT | `/depts` | Update a department |
| DELETE | `/depts?id={id}` | Delete a department |

### Employees

| Method | Path | Description |
| --- | --- | --- |
| GET | `/emps` | Paginated employee query |
| POST | `/emps` | Create an employee |
| GET | `/emps/{id}` | Get employee details |
| PUT | `/emps` | Update an employee |
| DELETE | `/emps?ids=1,2,3` | Delete employees |
| GET | `/emps/list` | List all employees |

### Classes

| Method | Path | Description |
| --- | --- | --- |
| GET | `/clazzs` | Paginated class query |
| POST | `/clazzs` | Create a class |
| GET | `/clazzs/{id}` | Get class details |
| PUT | `/clazzs` | Update a class |
| DELETE | `/clazzs/{id}` | Delete a class |
| GET | `/clazzs/list` | List all classes |

### Students

| Method | Path | Description |
| --- | --- | --- |
| GET | `/students` | Paginated student query |
| POST | `/students` | Create a student |
| GET | `/students/{id}` | Get student details |
| PUT | `/students` | Update a student |
| DELETE | `/students/{ids}` | Delete students |
| PUT | `/students/violation/{id}/{score}` | Add violation score to a student |

### Reports

| Method | Path | Description |
| --- | --- | --- |
| GET | `/report/empJobData` | Employee job distribution |
| GET | `/report/empGenderData` | Employee gender distribution |
| GET | `/report/studentDegreeData` | Student degree distribution |
| GET | `/report/studentCountData` | Student count grouped by class |

### Uploads

| Method | Path | Description |
| --- | --- | --- |
| POST | `/upload` | Upload a multipart file to Tencent COS |

### Operation Logs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/log/page` | Paginated operation log query |

## Notes

- `SessionController` contains Cookie and HttpSession demo endpoints and is not part of the main JWT-based business flow.
- The current test suite contains a JWT parsing test with an expired hard-coded token. Compile with `-DskipTests` if you only want to verify main source compilation before updating tests.
- Passwords are currently checked as plain text. For production, store hashed passwords and verify them with a password encoder such as BCrypt.
- JWT secret and database credentials should be externalized before deployment.
- IDE metadata such as `.idea/` and `*.iml` is ignored by the root `.gitignore`.

## License

MIT License
Copyright (c) 2026 Starry
