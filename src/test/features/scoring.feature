Feature:  Ensure bowling scores are calculated correctly

 Scenario: Perfect scores
 Given a player with the following scores:
 | ball 1 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10
 | ball 2 |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - | -
 When the game is over
 Then the players score is 300

