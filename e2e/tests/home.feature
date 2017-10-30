Feature: Home
  Scenario: Button Click
    Given I view the homepage
    When I click on the Start button
    Then I should see a message telling me that the button was clicked
