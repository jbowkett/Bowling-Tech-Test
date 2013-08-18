package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 9:19:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInputSubShellTest {

  @Test
  public void testTwoPlayersInputCreatesTwoPlayers() throws QuitException {
    final Console mockConsole = mock(Console.class);
    final int playerCount = 2;
    when(mockConsole.getIntInput("> ", 1, Integer.MAX_VALUE)).thenReturn(playerCount);

    when(mockConsole.getStringPrompt("Player [1] name > ")).thenReturn("James");
    when(mockConsole.getStringPrompt("Player [2] name > ")).thenReturn("Gosling");
    
    final PlayerInputSubShell shell = new PlayerInputSubShell(mockConsole);
    Player[] players = shell.start();
    assertEquals(playerCount, players.length);
  }
}
