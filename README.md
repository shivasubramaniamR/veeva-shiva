# Test-Automation-Framework
This framework is designed to facilitate clear and concise test case documentation using Gherkin syntax within Cucumber's feature files. This enables the expression of software behavior without detailing how that functionality is implemented. The feature files serve as living documentation and a source for the automated test cases. Step definitions translate the human-readable descriptions into concrete actions that manipulate the application under test through the Selenium WebDriver.

The Page Object Model (POM) is implemented to enhance maintainability and reduce code duplication. Each page object class represents a specific page of the web application, encapsulating the page's elements and the interactions with them. This separation of concerns ensures that changes in the UI only require updates in the page objects, not in the step definitions or test scenarios.

Custom hooks are used for setup and teardown processes, ensuring a clean state for each test scenario and improving the reliability of the test suite.Test data management is handled , allowing tests to consume data from structured formats in JSON . This flexibility means that test data can be externalized, making it easy to update without altering the test code. The framework also offers utility classes, which provide common functionalities that can be reused across different parts of the framework, reducing code redundancy and promoting efficiency.
The entire framework is constructed with modularity, reusability, and scalability in mind, making it suitable for both small and large-scale projects

# Framework Offers
Parallel test runs
Shared state across cucumber step definitions
Dependency injection
Page Object pattern
Common web page interaction methods
Multi-Maven Module
Externalised test configuration
Commonly used test utility classes
Maven Dependency Management
Maven Profile Management

# Tools
Maven
Cucumber-JVM
Selenium Webdriver
Testng
Jackson
Picco Container
ExtentReports

# Requirements
In order to utilise this project below needs to be installed locally
Java (greater than 1.8)
Maven
Any of the Browsers(Chrome,Firefox and Edge)

# Usage
This project is a multi maven Module with the Parent Module . there are four modules
1. Automation Framework
2. Core Product
3. Derived Product 1
4. Derived Product 2

Automation Framework is extended to all other three modules as a dependency.

To run all modules, navigate to test-automation-framework directory and run:
mvn clean install -DskipTests

To run the corresponding module , navigate to the module and run:
mvn clean test

To run with firefox browser, run the following:
mvn clean test -Pfirefox

To run with chrome browser, run the following:
mvn clean test -Pchrome

To run with edge browser, run the following:
mvn clean test -Pedge

If no -P tag was passed, then the default browse chrome will set

# Parallel Execution
By default , Parallel Execution is enabled via TestNG's AbstractTestNGCucumberTests by overriding the data Provider .
To Turn Off, Kindly comment out the below lines in the Runner
@Override
     @DataProvider(parallel = true)
     public Object[][] scenarios() {
         return super.scenarios();
     }


# Reporting
Reports for each module are written into their respective /target directories after a successful run.
Screenshots are automatically captured for every step and attached to the report


