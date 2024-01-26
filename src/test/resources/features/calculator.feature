Feature: calculator

  Scenario Outline: client makes a sum
    When the client add <x> and <y>
    Then the client receives status code of <status>
    And the client receives a body "<body>"

    Examples:
    | x   | y   | status | body |
    | 5   | 3   | 200    | 8.0  |
    | 2.2 | 4.3 | 200    | 6.5  |

  Scenario Outline: client makes a sum
    When the client divide <x> and <y>
    Then the client receives status code of <status>
    And the client receives a body "<body>"

    Examples:
      | x   | y   | status | body       |
      | 10  | 2   | 200    | 5.0        |
      | 5   | 2   | 200    | 2.5        |
      | 0   | 3   | 200    | 0.0        |
      | 3   | 0   | 500    | / by zero  |