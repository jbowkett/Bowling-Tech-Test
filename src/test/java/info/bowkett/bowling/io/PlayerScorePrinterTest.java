package info.bowkett.bowling.io;

import info.bowkett.bowling.model.FrameTally;
import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.Scorer;
import info.bowkett.bowling.model.TallyCard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    assertEquals("      |  2 |  4 |  1 |  0 |  1 |  9 |  2 |  3 |  7 |  X |", extractLine(consoleOutput, 1));
  }

  @Test
  public void testPrintBall_2_Score(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("      |  1 |  1 |  5 |  2 |  4 |  0 |  3 |  1 |  1 |  X |", extractLine(consoleOutput, 2));
  }

  @Test
  public void testPrintBall_3_Score(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("      |  0 |  0 |  0 |  0 |  0 |  0 |  0 |  0 |  0 |  X |", extractLine(consoleOutput, 3));
  }

  @Test
  public void testPrintTallySeparator(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("--------------------------------------------------------", extractLine(consoleOutput, 4));
  }

  @Test
  public void testPrintAllScoresSeparator(){
    final StringBuilder consoleOutput = new StringBuilder();
    final PlayerScorePrinter printer = new PlayerScorePrinter(mockConsole(consoleOutput));
    final Player mockPlayer = mockPlayerAndScore();
    printer.printScoreCardFor(mockPlayer);
    assertEquals("      |  3 |  5 |  6 |  2 |  5 |  9 |  5 |  4 |  8 | 40 |", extractLine(consoleOutput, 5));
  }

  private Player mockPlayerAndScore() {
    final Player mockPlayer = mock(Player.class);
    when(mockPlayer.getName()).thenReturn("James");
    final TallyCard mockTallyCard = mockTallyCard(
      new int[]{2,4,1,0,1,9,2,3,7,10},
      new int[]{1,1,5,2,4,0,3,1,1,10},
      new int[]{0,0,0,0,0,0,0,0,0,10}
      );
    when(mockPlayer.getTallyCard()).thenReturn(mockTallyCard);
    when(mockPlayer.getScorer()).thenReturn(new Scorer());
    return mockPlayer;
  }


  // this method is starting to make this look more like an integration test
  private TallyCard mockTallyCard(int[] ballOneScores, int [] ballTwoScores, int [] ballThreeScores) {
    final TallyCard mockTallyCard = mock(TallyCard.class);
    final List<FrameTally> allMockedTallies = new ArrayList<FrameTally>();
    for (int i = 0; i < ballOneScores.length; i++) {
      final int ballOneScore = ballOneScores[i];
      final int ballTwoScore = ballTwoScores[i];
      final int ballThreeScore = ballThreeScores[i];
      final FrameTally mockTally = mock(FrameTally.class);
      when(mockTally.getBallTally(1)).thenReturn(ballOneScore);
      when(mockTally.getBallTally(2)).thenReturn(ballTwoScore);
      when(mockTally.getBallTally(3)).thenReturn(ballThreeScore);
      allMockedTallies.add(mockTally);
      when(mockTallyCard.getFrameTallyForFrame(i+1)).thenReturn(mockTally);
    }
    when(mockTallyCard.getFrames()).thenReturn(allMockedTallies);
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
