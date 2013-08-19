package info.bowkett.bowling.io;

import info.bowkett.bowling.model.FrameTally;
import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.TallyCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 19, 2013
 * Time: 8:06:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerScorePrinterTest {

  @Test
  public void testPrintHeader(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("James |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |", extractLine(consoleOutput, 0));
  }


  @Test
  public void testPrintBall_1_Score(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("      |  2 |  4 |  1 |  0 |  1 |  9 |  2 |  3 |  7 |  7 |", extractLine(consoleOutput, 1));
  }

  @Test
  public void testPrintBall_2_Score(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("      |  1 |  1 |  5 |  2 |  4 |  0 |  3 |  1 |  1 |  1 |", extractLine(consoleOutput, 2));
  }

  private Player mockPlayerAndScore() {
    final Player mockPlayer = mock(Player.class);
    when(mockPlayer.getName()).thenReturn("James");
    final TallyCard mockTallyCard = mockTallyCard(new int[]{2,4,1,0,1,9,2,3,7,7},
      new int[]{1,1,5,2,4,0,3,1,1,1});
    when(mockPlayer.getTallyCard()).thenReturn(mockTallyCard);
    return mockPlayer;
  }

  private TallyCard mockTallyCard(int[] ballOneScores, int [] ballTwoScores) {
    final TallyCard mockTallyCard = mock(TallyCard.class);
    for (int i = 0; i < ballOneScores.length; i++) {
      final int ballOneScore = ballOneScores[i];
      final int ballTwoScore = ballTwoScores[i];
      final FrameTally mockTally = mock(FrameTally.class);
      when(mockTally.getBallTally(1)).thenReturn(ballOneScore);
      when(mockTally.getBallTally(2)).thenReturn(ballTwoScore);
      when(mockTallyCard.getFrameTallyForFrame(i+1)).thenReturn(mockTally);
    }
    return mockTallyCard;
  }


  private String extractLine(StringBuilder consoleOutput, int lineNumber) {
    return consoleOutput.toString().split("\n")[lineNumber];
  }


  private Console mockConsole(final StringBuilder buffer){
    return new Console(System.console()){
      public Console msg(String msg){
        buffer.append(msg);
        return this;
      }
      public Console msgln(String msg){
        return msg(msg).msg("\n");
      }
    };
  }
}
