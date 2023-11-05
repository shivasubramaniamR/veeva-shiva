# Author: Shivasubramaniam SR
# Date: 2023-11-05

Feature: Footer Hyperlinks Collection and Duplication Check for DP2

  Scenario: Collect and check for duplicate footer hyperlinks on DP2 home page
    Given I am on the DP2 home page
    When I scroll down to the footer
    Then I find all hyperlinks in the footer
    And I write the hyperlinks into a CSV file
    And I report if any duplicate hyperlinks are present