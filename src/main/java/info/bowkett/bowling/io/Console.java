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

  public int getIntInput(int minValue, int maxValue) throws QuitException, InvalidInputException {
    final String input = getStringPrompt("Name > ");
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


  public void msgln(String msg) {
    System.out.println(msg);
  }

  public void msg(String msg) {
    System.out.print(msg);
  }

  public void exception(InvalidInputException e) {
    msgln(e.getMessage() + " :: " +e.getAdvice());
  }
}
