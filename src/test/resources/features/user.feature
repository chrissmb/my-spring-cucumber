Feature: the version can be retrieved

  Scenario: client makes call to GET users
    When the client calls users
    Then the client receives status code of 200
    And the client receives a body file "users.json"