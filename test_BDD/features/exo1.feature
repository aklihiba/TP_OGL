Feature: calculate det
  Scenario: det
    Given i have a matrix
    |col1|col2|col3|
    | 1  | 2  | 3  |
    | 3  | 2  | 1  |
    | 0  | 4  | 3  |
    When i calculate the det
    Then i should have 20

  Scenario: transpose
    Given i have a matrix
      |col1|col2|col3|
      | 1  | 2  | 3  |
      | 3  | 2  | 1  |
      | 0  | 4  | 3  |
    When i calculate the the traspose
    Then i should have
      |col1|col2|col3|
      | 1  | 3  | 0  |
      | 2  | 2  | 4  |
      | 3  | 1  | 3  |