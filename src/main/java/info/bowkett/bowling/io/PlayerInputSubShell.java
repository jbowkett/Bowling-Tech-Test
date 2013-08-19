package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.Scorer;
import info.bowkett.bowling.model.TallyCard;


/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 12:01:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInputSubShell {
  private final Console console;

  public PlayerInputSubShell(Console console) {
    this.console = console;
  }

  public Player[] start() throws QuitException {
    final int playerCount = promptForPlayerCount();
    return promptForPlayers(playerCount);
  }

  private int promptForPlayerCount() throws QuitException {
    console.msgln("How many players will there be? ");
    return console.getIntInput("> ", 1, Integer.MAX_VALUE);
  }

  private Player [] promptForPlayers(int playerCount) throws QuitException {
    console.msgln("Please enter the player names:");
    final Player[] players = new Player[playerCount];
    for(int i = 0 ; i < playerCount ; i++){
      final int playerNumber = i+1;
      players[i] = getPlayer(playerNumber);
    }
    return players;
  }

  private Player getPlayer(int playerNumber) throws QuitException {
    final String name = console.getStringPrompt("Player ["+playerNumber+"] name > ");
    return new Player(name, new TallyCard(), new Scorer());
  }
}

