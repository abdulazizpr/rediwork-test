# Rediwork Test Project

This project is a test project for Rediwork. The project is a simple CRUD application for managing fruits.

## Requirements:
* Java 17 
* Maven 3.6.3
* Spring Boot 3.3.2
* Postgres 13

## Dependencies
* Project Reactor
* Spring Reactive Programming

## How to Develop It
- Refactoring from existing Base Code into Current Base Code
- Renaming packaging and class name for better understanding
- Refactoring all modular
- Create Modeling for Fruit
- Create Data Migration for Fruit
- Create Repository for Fruit
- Create Command Patten for Fruit
    - DeleteFruitCommand
    - GetAllFruitsPaginationCommand
    - SaveFruitCommand
    - UpdateFruitCommand
- Create Controller for Fruit
- Create Integration Testing
- Create UI Integration for Fruit
- Update bitbucket-pipelines.yml
- Update README.md

## How to run the project

1. Clone the repository

```shell
git clone git@bitbucket.org:katapangfreelance/rediwork-test-project.git
```

2. Run migrations on the database

```shell
cd rediwork-test-migration/

mvn flyway:migrate
```

3. Run the following command to start the project

```shell
mvn spring-boot:run
```

4. The project will start on port 8080

## How to run the tests

Run the following command to run the tests

```shell
mvn test
```

## How to clean the project
```shell
mvn clean
```

## API Documentation
After running the application, you can access the API documentation on http://localhost:8080/swagger-ui.html

## UI Example Integration
After running the application, you can access the UI example on http://localhost:8080/fruits

## References Project Modules
* https://github.com/bliblidotcom/blibli-backend-framework
* https://github.com/khannedy/spring-command-pattern
