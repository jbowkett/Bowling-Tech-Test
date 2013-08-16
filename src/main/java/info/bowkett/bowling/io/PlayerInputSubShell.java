package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;
import info.bowkett.bowling.model.Scorer;
import info.bowkett.bowling.model.TallyCard;

import java.io.Console;

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
      System.out.println("How many players will there be? ");
      try {
        playerCount = getIntInput(1, Integer.MAX_VALUE);
      }
      catch (InvalidInputException e) {
        System.out.println(e.getMessage() + " :: " +e.getAdvice());
      }
    }
    while(playerCount <= 0);
    return playerCount;
  }

  private Player [] getPlayers(int playerCount) throws QuitException {
    final Player[] players = new Player[playerCount];
    for(int i =0 ; i < playerCount ; i++){
      System.out.print("Name >");
      players[i] = new Player(getStringInput(), new TallyCard(), new Scorer());
    }
    return players;
  }

  private String getStringInput() throws QuitException {
    String input = console.readLine();
    if(input.equalsIgnoreCase("QUIT")){
      throw new QuitException();
    }
    return input;
  }

  private int getIntInput(int minValue, int maxValue) throws QuitException, InvalidInputException {
    final String input = getStringInput();
    try{
      final int parsedValue = Integer.parseInt(input);
      if(withinBounds(minValue, maxValue, parsedValue)){
        return parsedValue;
      }
      return outOfBoundsInputException(input, minValue, maxValue);
    }
    catch(NumberFormatException nfe){
      return outOfBoundsInputException(input, minValue, maxValue);
    }
  }

  private boolean withinBounds(int minValue, int maxValue, int parsedValue) {
    return parsedValue >= minValue && parsedValue < maxValue;
  }

  private int outOfBoundsInputException(String input, int minValue, int maxValue) throws InvalidInputException {
    throw new InvalidInputException("Invalid number input :["+input+"]", "Please enter a number between "+minValue+" & "+ maxValue);
  }
}

