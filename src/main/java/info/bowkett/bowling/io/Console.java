package info.bowkett.bowling.io;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 9:06:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Console {
  private final java.io.Console console;

  public Console(java.io.Console console) {
    this.console = console;
  }

  public String getStringPrompt(String prompt) throws QuitException {
    String input = null;
    while(isBlank(input)){
      msg(prompt);
      input = console.readLine();
      if(input.equalsIgnoreCase("QUIT")){
        throw new QuitException();
      }
    }
    return input;
  }

  private boolean isBlank(String input) {
    return input == null || input.trim().length() == 0;
  }

  public int getIntInput(String prompt, int minValue, int maxValue) throws QuitException {
    while(true){
      final String input = getStringPrompt(prompt);
      try{
        final int parsedValue = toInt(input);
        if(withinBounds(minValue, maxValue, parsedValue)){
          return parsedValue;
        }
        throw new InvalidInputException("Invalid number input :["+input+"]");
      }
      catch(InvalidInputException e) {
        exception(e, "Please enter a number between "+minValue+" & "+ maxValue);
      }
    }
  }

  private int toInt(String input) throws InvalidInputException {
    try{
      return Integer.parseInt(input);
    }
    catch(NumberFormatException nfe){
      throw new InvalidInputException("Invalid number input :["+input+"]");
    }
  }

  private boolean withinBounds(int minValue, int maxValue, int parsedValue) {
    return parsedValue >= minValue && parsedValue <= maxValue;
  }

  public void msgln(String msg) {
    System.out.println(msg);
  }

  public void msg(String msg) {
    System.out.print(msg);
  }

  public void exception(InvalidInputException e, String advice) {
    msgln(e.getMessage() + " :: " + advice);
  }
}
