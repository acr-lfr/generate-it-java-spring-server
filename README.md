# Acrontum Skeleton Spring Boot App

This is a skeleton spring boot application

## Technologies

- Docker, Docker-Compose
- Spring boot
- Maven
- Postgres
- Flyway
- Lombok, MapStruct
- Junit5, Mockito, AssertJ
- Testcontainers

## Architecture

<!-- Include image here when created -->

The main components of the architecture are:

* Rest Controller: It maps the request, validate if data is correct, and apply authorization based on roles.
* Service: It contains the business rules of the application.
* Repository: It connects to the database.
* Remote: It connects to external services.
* Mapper: It transforms the DTOs in the application.
* Filter: It allows you to run code before and after a request.

## Building the project

To build the project you should run:
> ./gradlew clean build

or the command below if you don't want to run the tests
> ./gradlew assemble 

## Running it with Docker

You can run it with docker by building the project and run:
> docker-compose up

## Debugging the project

You can debug the project using your favorite Java editor, if for instance you're using IntelliJ you can run the class
`SprintBootApplication` in debug mode, as seen below:

![debugging-in-intellij.png](debugging-in-intellij.png)

After that you can include the break points in your code.

## Testing

You can run all tests by:
> ./gradlew test

We can write two types of tests: Unit tests and Integration tests

### Unit tests

The units tests are written using JUnit5, Mockito and you can use either hamcrest or assertj to make your assertions.
Check [this folder](src/test/java/com/acrontum/template/services) to see examples of unit testing.

### Integration tests

The integration tests use spring framework to run.  
Check [this folder](src/test/java/com/acrontum/template/controllers) to see examples of integration tests.

#### Using Postgres on Integration tests

You can use Postgres in case you need some specific feature of Postgres. To do that all you have to do is to extend
the `com.acrontum.template.PostgresDockerEnv` class in your integration test. If you use this you'll be able to:

- Launching the docker-compose file before the tests
- Sharing the docker images between all test
- Cleaning the context after all tests are finished

## Auth

Except the base path and paths that include `/public/`, every endpoint is already protected by authentication using JWT.
You can
use [Spring Security](https://www.baeldung.com/spring-security-method-security#3-using-preauthorize-and-postauthorize-annotations)
annotations to control the authorization.

You can use this JWT to run your local tests, provided that you don't change the JWT secret:
> curl --location --request GET 'http://localhost:8080/users'
> --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWIiLCJyb2xlcyI6IkFETUlOIiwibmFtZSI6IlVzZXJuYW1lIiwiaWF0IjoxNjQ5ODQzMDgxfQ.\_n921EYQ6TBlKamQXC61bwgGMtHzzLRgG3pv_SVIwcQ'

## Sonar
To run the sonar task you should pass the host and credentials as seen below:

> ./gradlew clean test sonarqube \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=admin -Dsonar.password=password

## Reference Documentation

For further reference, please consider the following sections:

* [Testcontainers](https://www.testcontainers.org/)

## Code Generation 

See [nodegen folder](./.openapi-nodegen/README.md)
