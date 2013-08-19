package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;
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
    final Player mockPlayer = mock(Player.class);
    when(mockPlayer.getName()).thenReturn("James");
    printer.printScoreCardFor(mockPlayer);
    assertEquals("James |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |", extractLine(consoleOutput, 0));
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
