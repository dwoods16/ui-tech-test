# QA Tech Test

Playwright + JUnit 5 + Maven automation project.

## Requirements

- Java 17
- Maven 3.9+

## Setup

```bash
mvn clean install
```

## Run Tests

By default the tests run headless
```bash
mvn test
```

To run tests headed, simply pass the system variable as follows
```bash
mvn test
```

To run a single test, pass the command as follows
```bash
mvn test -Dtest=HousingSortTests#sortOptionsAfterSearch
```

## Reporting

Test report is generated automatically and can be found under `target/allure-results`
To view the html version in your local browser, simply run
```bash
allure serve target/allure-results
```
Screenshots for failed tests are saved to `test-output/` directory.