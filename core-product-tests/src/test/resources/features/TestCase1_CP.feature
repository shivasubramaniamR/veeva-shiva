# Author: Shivasubramaniam SR
# Date: 2023-11-05

Feature: NBA Warriors Shop Men Jackets

  Scenario: Store and report prices, titles, and top seller messages of men jackets
    Given I am on the NBA Warriors homepage
    When I navigate to the Men Jackets section
    Then I collect all Jacket details from all pages
    And I store the details in a text file
    And I attach the text file to the report
