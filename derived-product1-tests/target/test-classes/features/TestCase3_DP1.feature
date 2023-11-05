# Author: Shivasubramaniam SR
# Date: 2023-11-05

Feature: Slide Validation on DP1 Home Page

  Scenario: Validate number, title, and duration of slides below the Tickets Menu on DP1 home page
    Given I am on the DP1 home page
    When I count the number of slides below the Tickets Menu
    Then I should see expected number slides
    When I retrieve the title of each slide
    Then the slide titles should match the expected test data
    When I measure the duration of each slide
    Then the slide durations should match the expected durations

