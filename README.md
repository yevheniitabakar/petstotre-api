# Petstore API Testing Project

This project contains automated API tests for the [Petstore API](https://petstore.swagger.io/v2) using TestNG, RestAssured, and Gradle.
The tests cover various endpoints of the Petstore API, including store inventory and order management.

## Project Overview
The goal of this project is to test the functionality of the Petstore API endpoints, including:
- Retrieving store inventory (`GET /store/inventory`)
- Placing an order (`POST /store/order`)
- Retrieving an order by ID (`GET /store/order/{id}`)
- Deleting an order by ID (`DELETE /store/order/{id}`)

## Project Structure
````
petstore-api/
├── gradle/                                         # Gradle Wrapper files
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── report/                                         # TestNG HTML reports (generated after test runs)
│   ├── 2025-03-19_16-56-44/                        # Example timestamped report folder
│   │   └── index.html                              # Main HTML report
│   └── smoke/                                      # Example report folder for smoke tests
│       └── index.html                              # Smoke test report
├── src/                                            # Source code
│   ├── main/                   
│   │   ├── java/               
│   │   │   └── com/store/models/
│   │   │       └── Order.java                      # Order model class    
│   └── test/                   
│       ├── java/               
│       │   └── com/store/
│       │       ├── base/                           # Base test class
│       │       │   └── BaseTest.java
│       │       ├── constants/                      # Constants
│       │       │   └── Constants.java
│       │       ├── tests/                          # Test classes
│       │       │   ├── StoreInventoryTests.java
│       │       │   └── StoreOrderTests.java
│       │       └── utils/                          # Utility classes
│       │           ├── ApiTestListener.java
│       │           ├── ApiUtils.java
│       │           ├── Logable.java
│       │           └── RequestBuilder.java
│       └── resources/                              # Resources for tests
├── .gitignore                                      # Git ignore file
├── build.gradle                                    # Gradle build configuration
├── gradlew                                         # Gradle Wrapper script (Unix/macOS/Linux)
├── gradlew.bat                                     # Gradle Wrapper script (Windows)
├── README.md                                       # Project README file
└── settings.gradle                                 # Gradle settings file
````
## Test execution
To run all tests and generate an HTML report, use the following command:
`./gradlew test`

## Test reporting 
TestNG generates an HTML report after each test run.
Reports are saved in the report/ directory with a timestamped folder (e.g., report/2025-03-19_10-30-45/index.html).
### Note:
This project interacts with a public test environment that may sometimes exhibit unexpected behavior, resulting in intermittent or flaky test failures.