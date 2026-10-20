# Spring-tutorial

A small Spring Boot REST API to use as a starting point while learning Spring.

## Requirements

- Java 21 or newer
- Maven 3.6.3 or newer

## Run the application

From this directory, start the app with:

```bash
mvn spring-boot:run
```

Then request the hello endpoint:

```bash
curl http://localhost:8080/api/hello
```

Response:

```json
{"message":"Hello, World!"}
```

To run the tests:

```bash
mvn test
```

## MySQL

MySQL is intended to run as a separate service, outside this project. The current
hello-world API does not connect to a database, so MySQL does not need to be
running to start the app. When database features are added, document the
connection setup here and add only the dependencies those features require.

## Learning updates

Add a short entry here as new Spring concepts and features are introduced.

- **Starter:** Created a Spring Boot REST endpoint at `GET /api/hello`.
