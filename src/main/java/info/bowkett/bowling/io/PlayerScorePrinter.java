package info.bowkett.bowling.io;

import info.bowkett.bowling.model.FrameTally;
import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.TallyCard;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:58:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerScorePrinter {
  private Console console;

  public PlayerScorePrinter(Console console) {
    this.console = console;
  }

  public void printScoreCardFor(Player player) {
    printHeader(player.getName());
    console.msg("\n");
    printBallOne(player);
    console.msg("\n");
//    printBallTwo(player);
  }

  private void printBallOne(Player player) {
    printSpaceForName(player.getName());
    final TallyCard tallyCard = player.getTallyCard();
    for (int i = 1; i <= 10 ; i++) {
      final FrameTally frameTally = tallyCard.getFrameTallyForFrame(i);
      final int tally = frameTally.getBallOneTally();
      printCell(tally);
    }
    console.msg(" |");
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
}
