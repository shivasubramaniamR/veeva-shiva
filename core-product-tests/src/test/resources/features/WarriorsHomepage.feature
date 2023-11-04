Feature: Visit NBA Team Homepages

  Scenario Outline: Visit the NBA Team Homepage
    Given I am on the website "<Website URL>"
    When I view the homepage
    Then I should see the team logo

    Examples:
      | Website URL                        |
      #| https://www.nba.com/warriors       |
      | https://www.nba.com/sixers/        |
     # | https://www.nba.com/bulls/         |
