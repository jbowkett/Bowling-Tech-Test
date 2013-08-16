package info.bowkett.bowling.io;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 8:04:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidInputException extends Throwable {
  private final String advice;

  public InvalidInputException(String msg, String advice) {
    super(msg);
    this.advice = advice;
  }

  public String getAdvice() {
    return advice;
  }
}
