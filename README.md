# Spring Boot GraphQL API with H2 In-Memory Database

This project is a simple Spring Boot application that uses GraphQL to provide a simple API for performing CRUD operations on a H2 in-memory database.

## Features

- Create, read, update, and delete records using GraphQL
- Use H2 in-memory database for database storage
- Simple API for easy integration with other systems

## Architecture

The application is built using Spring Boot and consists of several layers:

- `GraphQLController`: Handles incoming GraphQL requests and maps them to the appropriate service methods
- `GraphQLService`: Contains the business logic for managing records and interacts with the repository layer
- `Repository`: Provides an abstraction for interacting with the database

The `GraphQLService` class is a Spring service that provides methods for managing records in the database. It uses a `Repository` to interact with the database and provides methods for managing the records on the database. Consequently, the client requests are going through the `GraphQLController` before being redirected to `GraphQLService` and back to `GraphQLController` as a JSON response to the client.

![spring-graphql](https://user-images.githubusercontent.com/84593887/231897694-47bf0c0a-3668-45f9-8346-a72c0c339a1b.png)

## Database

The project uses H2 in-memory database for database storage. The database is initialized with sample data on startup.

## Getting Started

### Prerequisites

- Java 8 or higher

### Installation

Clone the repository

```sh
git clone https://github.com/yourusername/spring-graphql-h2.git
```

Build the project

```sh
Copy code
cd spring-graphql-h2
mvn clean install
```

Run the application

```sh
Copy code
java -jar target/spring-graphql-h2-0.0.1-SNAPSHOT.jar
```

The application will start and be available at http://localhost:8080.

## Usage

GraphQL endpoint: http://localhost:8080/graphql

GraphQL UI endpoint: http://localhost:8080/graphiql

Swagger UI documentation: http://localhost:8080/swagger-ui/index.html

![Screenshot_20230413_193628](https://user-images.githubusercontent.com/84593887/231897838-6b254f7b-fc57-45c9-8378-7ff01502d86b.png)

The following is an example of how to create a new record using GraphQL:

```graphql
mutation {
  createRecord(input: {
    field1: "value1",
    field2: "value2"
  }) {
    id
    field1
    field2
  }
}
```

And an example of how to retrieve all records:

```graphql
query {
  records {
    id
    field1
    field2
  }
}
```

## Contributing

Contributions are welcome! Just fork the project and make a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
