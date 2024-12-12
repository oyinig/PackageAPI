# Package Management Service

A Spring Boot application that manages packages and their associated products, with support for multiple currencies.

## Features

- Create, read, update, and delete packages
- Products can be associated with packages
- Currency conversion support (defaults to USD)
- RESTful API endpoints
- H2 in-memory database for data storage

## Tech Stack

- Java 17
- Spring Boot
- H2 Database
- Docker
- Maven

## API Endpoints

- `POST /api/packages` - Create a new package
- `GET /api/packages` - List all packages
- `GET /api/packages/{id}` - Get a specific package
- `PUT /api/packages/{id}` - Update a package
- `DELETE /api/packages/{id}` - Delete a package

Query Parameters:
- `currency` (optional) - Convert prices to specified currency (defaults to USD)

## Running the Application

### Prerequisites

- Docker
- Docker Compose

### Steps to Run

1. Clone the repository:
```bash
git clone <repository-url>
cd <project-directory>
```

2. Start the application:
```bash
docker-compose up -d
```

3. The application will be available at:
```
http://localhost:8080
```

4. Access H2 Console (if needed):
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:packagedb
Username: sa
Password: password
```

5. To stop the application:
```bash
docker-compose down
```

## Example Requests

Create a Package:
```bash
curl -X POST http://localhost:8080/api/packages \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Package One",
    "description": "First package",
    "productIds": ["VqKb4tyj9V6i", "7dgX6XzU3Wds"]
  }'
```

Get Package with Different Currency:
```bash
curl http://localhost:8080/api/packages/1?currency=EUR
```

## Database Schema

The application uses an H2 in-memory database with two main entities:
- `Package`: Represents a collection of products
- `Product`: Represents individual items that can be part of a package

The database is recreated each time the application starts, making it perfect for testing and development.

## Development

To run the application locally without Docker:

1. Ensure you have Java 17 and Maven installed
2. Run:
```bash
mvn spring-boot:run
```

## Testing

Run the tests using:
```bash
mvn test
```
```

This updated documentation:
1. Correctly reflects the H2 in-memory database usage
2. Includes H2 console access information
3. Removes PostgreSQL-specific information
4. Maintains all other functionality descriptions
5. Provides accurate deployment and usage instructions
```



