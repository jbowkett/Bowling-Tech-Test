Feature:  Ensure bowling scores are calculated correctly

 Scenario: Perfect scores
 Given a player with the following scores:
 | throw  |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
 | ball 1 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 | 10 |
 | ball 2 |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - | -  |
 When the game is played
 Then the players score is 300

