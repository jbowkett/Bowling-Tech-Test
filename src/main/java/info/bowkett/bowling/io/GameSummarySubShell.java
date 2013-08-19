package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 12:04:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameSummarySubShell {
  private final PlayerScorePrinter playerScorePrinter;
  private TeamScorePrinter teamScorePrinter;
  private final Console console;
  private Player[] players;

  public GameSummarySubShell(PlayerScorePrinter playerScorePrinter, TeamScorePrinter teamScorePrinter, Console console) {
    this.playerScorePrinter = playerScorePrinter;
    this.teamScorePrinter = teamScorePrinter;
    this.console = console;
  }

  public void start() {
    final Player winner = getWinner();
    console.msgln("\nThe winner was: ["+winner.getName()+"] with ["+winner.getTotalScore()+"] points!\n");
    sortPlayersByScore();
    printAllPlayersScores();
    console.msgln("Had you been playing as a team, the scores would have been:");
    printAllPLayersAsTeamScore();
    console.msgln("");
  }

  private void printAllPLayersAsTeamScore() {
    teamScorePrinter.printScoreCardFor(players);
  }

  private void printAllPlayersScores() {
    for (Player player : players) {
      playerScorePrinter.printScoreCardFor(player);
      console.msgln("").msgln("");
      console.msgln("Total Score :["+player.getTotalScore()+"]\n");
    }
  }

  private void sortPlayersByScore() {
    Arrays.sort(players, new Comparator<Player>(){
      public int compare(Player o1, Player o2) {
        return o1.getTotalScore() - o2.getTotalScore();
      }
    });
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
