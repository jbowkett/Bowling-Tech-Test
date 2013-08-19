package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;


/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 12:04:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameSummarySubShell implements SubShell {
  private final ScorePrinter scorePrinter;
  private final Console console;
  private Player[] players;

  public GameSummarySubShell(ScorePrinter scorePrinter, Console console) {
    this.scorePrinter = scorePrinter;
    this.console = console;
  }

  public void start() {
    final Player winner = getWinner();
    console.msgln("The winner was: ["+winner.getName()+"] with ["+winner.getTotalScore()+"] points!");
    
  }

  private Player getWinner() {
    Player highestScorer = players[0];
    for (Player player : players) {
      if(player.getTotalScore() > highestScorer.getTotalScore()){
        highestScorer = player;
      }
    }
    return highestScorer;
  }

  public void setPlayers(Player[] players) {
    this.players = players;
  }
}
