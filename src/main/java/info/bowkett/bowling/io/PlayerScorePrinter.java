package info.bowkett.bowling.io;

import info.bowkett.bowling.model.FrameTally;
import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.Scorer;
import info.bowkett.bowling.model.TallyCard;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:58:50 PM
 *
 * Prints a player's score sheet for all frames.
 *
 * todo: Could factor out common printing and padding functions into a
 * todo: separate class
 *
 */
public class PlayerScorePrinter {
  private Console console;
  private static final int SCORE_TABLE_WIDTH = 51;

  public PlayerScorePrinter(Console console) {
    this.console = console;
  }

  public void printScoreCardFor(Player player) {
    printHeader(player.getName());
    console.msg("\n");
    printBall(player, 1);
    console.msg("\n");
    printBall(player, 2);
    console.msg("\n");
    printBall(player, 3);
    console.msg("\n");
    printSeparator(player);
    console.msg("\n");
    printFrameScores(player);
  }

  private void printFrameScores(Player player) {
    printSpaceForName(player.getName());
    final Scorer scorer = player.getScorer();
    final TallyCard tallyCard = player.getTallyCard();
    for (int i = 1; i <= 10 ; i++) {
      final int score = scorer.getFrameScore(tallyCard, i);
      printCell(score);
    }
    console.msg(" |");
  }

  private void printSeparator(Player player) {
    final String name = player.getName();
    final int width = name.length() + SCORE_TABLE_WIDTH;
    console.msg(leftPad("-", width, '-'));
  }

  private void printBall(Player player, int ball) {
    printSpaceForName(player.getName());
    final TallyCard tallyCard = player.getTallyCard();
    for (int i = 1; i <= 10 ; i++) {
      final FrameTally frameTally = tallyCard.getFrameTallyForFrame(i);
      final int tally = frameTally.getBallTally(ball);
      printTally(tally);
    }
    console.msg(" |");
  }

  private void printTally(int tally) {
    if(wasStrike(tally)){
      printStrike();
    }
    else{
      printCell(tally);
    }
  }

  private boolean wasStrike(int tally) {
    return tally == 10;
  }

  private void printStrike() {
    console.msg(" |  ").msg("X");
  }

  private void printSpaceForName(String name) {
    console.msg(leftPad(" ", name.length(), ' '));
  }

  private void printHeader(String name) {
    console.msg(name);
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

  private String pad(String value, int width, char toPadWith) {
    final StringBuilder padding = new StringBuilder();
    for (int i = value.length(); i < width; i++) {
      padding.append(toPadWith);
    }
    return padding.toString();
  }
}
