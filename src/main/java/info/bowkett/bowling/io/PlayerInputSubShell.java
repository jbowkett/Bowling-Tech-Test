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
public class PlayerInputSubShell implements SubShell {
  private final Console console;

  public PlayerInputSubShell(Console console) {
    this.console = console;
  }

  public Player[] start() throws QuitException {
    final int playerCount = promptForPlayerCount();
    return getPlayers(playerCount);
  }

  private int promptForPlayerCount() throws QuitException {
    int playerCount = 0;
    do{
      console.msgln("How many players will there be? ");
      try {
        playerCount = console.getIntInput("> ", 1, Integer.MAX_VALUE);
      }
      catch (InvalidInputException e) {
        console.exception(e);
      }
    }
    while(playerCount <= 0);
    return playerCount;
  }

  private Player [] getPlayers(int playerCount) throws QuitException {
    final Player[] players = new Player[playerCount];


    for(int i =0 ; i < playerCount ; i++){
      final String name = console.getStringPrompt("Name > ");
      players[i] = new Player(name, new TallyCard(), new Scorer());
    }
    return players;
  }
}

