Feature: Adding numbers
  Scenario Outline: add number

    Given I have a calculator
    When I add <num1> and <num2>
    Then I should have <results>
    Examples:
      |num1 |num2| results |
      | 12 | 5 | 17 |
      | 20 | 5 | 25 |