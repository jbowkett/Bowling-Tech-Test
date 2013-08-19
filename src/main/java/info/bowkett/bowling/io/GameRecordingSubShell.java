package info.bowkett.bowling.io;

import info.bowkett.bowling.model.GameState;
import info.bowkett.bowling.model.Turn;
import info.bowkett.bowling.model.Player;


/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 12:01:40 AM
 *
 * Sub shell for recording tallies from bowling games.  Is told which player to
 * record for by the GameState.
 *
 */
public class GameRecordingSubShell {
  private final GameState gameState;
  private final Console console;

  public GameRecordingSubShell(GameState gameState, Console console) {
    this.gameState = gameState;
    this.console = console;
  }

  public void setPlayers(Player[] players) {
    gameState.setPlayers(players);
  }

  public void start() throws QuitException {
    Turn turn = null;
    while((turn = gameState.getNextTurn()) != GameState.GAME_OVER){
      final int frameNo = turn.getFrameNumber();
      final int ballNo = turn.getBallNumber();
      final Player player = turn.getPlayer();
      final int availablePins = turn.getAvailablePins();

      if(firstBallForPlayerInFrame(ballNo)){
        console.msgln(nextPlayerMsg(player, frameNo));
      }
      console.msgln("Ball #["+ballNo+"]");
      final int tally = console.getIntInput("> ", 0, availablePins);
      gameState.setTally(tally);
    }
  }

  private String nextPlayerMsg(Player player, int frame) {
    return "Frame #["+frame+"] Please enter scores for player ["+player.getName()+"]";
  }

  private boolean firstBallForPlayerInFrame(int ballNumber) {
    return ballNumber == 1;
  }
}
