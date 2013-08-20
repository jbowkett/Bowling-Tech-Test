package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:58:50 PM
 *
 * Prints group of players' scores as if they were playing as a team.
 *
 * todo : this should be tallied as the play progresses.
 * todo: Currently it is effectively being calculated in the final view
 * todo: this is bad design and should be factored out to be calculated
 * todo: at the end of each frame
 *
 */
public class TeamScorePrinter {
  private Console console;
  private static final int SCORE_TABLE_WIDTH = 52;

  public TeamScorePrinter(Console console) {
    this.console = console;
  }

  public void printScoreCardFor(Player[] players) {
    final int longestPlayerName = getLongestPlayerName(players);
    printHeader(longestPlayerName);
    console.msg("\n");

    printPlayerTotals(players, longestPlayerName);
    printSeparator(longestPlayerName);
    console.msg("\n");
    printFrameTotals(players, longestPlayerName);
  }

  private void printFrameTotals(Player[] players, int longestPlayerName) {
    console.msg(rightPad("Team", longestPlayerName, ' '));
    for(int frameNumber = 1 ; frameNumber <= 10 ; frameNumber++){
      int totalFrameScore = 0;
      for (Player player : players) {
        final int score = getPlayerScoreForFrame(frameNumber, player);
        totalFrameScore += score;
      }
      printCell(totalFrameScore);
    }
    console.msg(" |");
  }

  private void printPlayerTotals(Player[] players, int longestPlayerName) {
    for (Player player : players) {
      console.msg(rightPad(player.getName(), longestPlayerName, ' '));
      for(int frameNumber = 1 ; frameNumber <= 10 ; frameNumber++){
        printFrameScoreFor(frameNumber, player);
      }
      console.msg(" |").msg("\n");
    }
  }

  private void printSeparator(int longestPlayerName) {
    final int width = longestPlayerName + SCORE_TABLE_WIDTH;
    console.msg(leftPad("-", width, '-'));
  }

  private void printFrameScoreFor(int frameNumber, Player player) {
    final int score = getPlayerScoreForFrame(frameNumber, player);
    printCell(score);
  }

  private int getPlayerScoreForFrame(int frameNumber, Player player) {
    return player.getScorer().getFrameScore(player.getTallyCard(), frameNumber);
  }

  private void printHeader(int longestPlayerName) {
    console.msg(leftPad(" ", longestPlayerName, ' '));
    printFrameNumbers();
  }

  private void printFrameNumbers() {
    for (int i = 1; i <= 10 ; i++) {
      printCell(i);
    }
    console.msg(" |");
  }

  private void printCell(int i) {
    console.msg(" | ").msg(padInt(i));
  }

  private String padInt(int i) {
    return leftPad(""+i, 2, ' ');
  }

  private String leftPad(String value, int width, char padChar){
    return pad(value, width, padChar) + value;
  }
  private String rightPad(String value, int width, char padChar){
    return value + pad(value, width, padChar);
  }

  private String pad(String value, int width, char toPadWith) {
    final StringBuilder padding = new StringBuilder();
    for (int i = value.length(); i < width; i++) {
      padding.append(toPadWith);
    }
    return padding.toString();
  }

  private int getLongestPlayerName(Player[] players) {
    int longestName = players[0].getName().length();
    for (Player player : players) {
      longestName = Math.max(longestName, player.getName().length());
    }
    return longestName;
  }
}
