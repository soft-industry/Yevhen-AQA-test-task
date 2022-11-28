# How to run
## Required setup
- Java 8
- Maven
## Installation
To install this project simply run this command in your command line:
```json
$ mvn install
```
It will download all required dependencies and build maven project
## Running tests
There are two options to run tests. Both of them are described in a separate topics
### Running tests in command line
To run tests in command line use this command:
```json
$ mvn verify
```
### Running test using test runner
To run test using test runner open TestRunner class located in:
```Gherkin
srcsrc
  + main
    + test
      + java
        + starter
```
and run it
# Refactoring changes log
## Added 'models' package
'models' package was added to store classes which are describing models of expected responses 
## Added 'services' package
'services' package was added to store classes for step methods which interact with API using Serenity REST methods and 
GSON to convert JSON responses to custom response model classes
## Renamed 'stepdefinitions' package
Renamed 'stepdefinitions' package to 'stepDefinitions' to match camel-case style in naming
## Removed Serenity REST imports from SearchStepDefinitions class
Serenity REST imports were removed from SearchStepDefinitions class as all work with API was moved to a separate class 
in 'services' package
## Renamed 'CarsService' class
Renamed 'CarsService' class to 'FoodService' as current endpoint supports only search keywords related to food
## Rephrased Cucumber feature description
Rephrased Cucumber feature description to match both positive and negative cases
## Separated positive and negative cases in different scenarios
Separated positive and negative cases in different scenarios to make reports and feature itself more readable 
## Added step to call endpoint
Added step to call endpoint with two arguments (url and http method) to be able to use the same method for different cases
## Added step to check status code
Added step to check status code also to be able to use the same method for every case (positive and negative)
## Used Scenario Outline
Used Scenario Outline combined with Examples table to re-use same test case for different arguments values and check more cases
## Added different steps for different errors
Added separate steps for case when no items were found, but request is correct, and other case when something is wrong with request.
The main reason here is that structure of response is different for these two cases. When request is correct in response we have 'detail' JSON 
object with 5 elements. And when something is wrong with request we have only one JSON element 'detail' which contains error message
## Implemented custom error messages for some assertions 
When we check that response to search request contains items with expected search keyword, 
in case of failure by default we see only message that size is not correct. So, to understand what the problem is 
in Serenity report, custom assertion errors were implemented. Also, for 'water' search keyword there are five items without
this keyword in title. It is a known issue and I`m not sure how to handle it without further discussion with team.