package info.bowkett.bowling;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:31:35 PM
 *
 * Represents the amount of pins knocked down in a frame
 *
 */
public class FrameTally {
  private final int ballOneScore, ballTwoScore;

  public FrameTally(int ballOneScore, int ballTwoScore) {
    this.ballOneScore = ballOneScore;
    this.ballTwoScore = ballTwoScore;
  }
}
