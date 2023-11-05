Feature: Count Video Feeds on CP Home Page

  Scenario: Count total number of Video Feeds and those with duration >= 3 days
    Given I am on the NBA Warriors homepage
    When I hover over the menu item "News & Features"
    And I click on "News & Features"
    Then I count the total number of Video Feeds
    And I count the Video Feeds with a duration of 3 days or more
