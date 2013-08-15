package info.bowkett.bowling.model;

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
  private final int ballOneScore;
  private final int ballTwoScore;
  private final int bonusBallScore;

  public FrameTally(int ballOneScore, int ballTwoScore, int bonusBallScore) {
    this.ballOneScore = ballOneScore;
    this.ballTwoScore = ballTwoScore;
    this.bonusBallScore = bonusBallScore;
  }
  
  public static FrameTally ordinaryFrame(int ballOneScore, int ballTwoScore){
    return new FrameTally(ballOneScore, ballTwoScore, 0);
  }

  public static FrameTally bonusFrame(int ballOneScore, int ballTwoScore, int bonusBallScore){
    return new FrameTally(ballOneScore, ballTwoScore, bonusBallScore);
  }

  public int getBallOneScore() {
    return ballOneScore;
  }

  public int getBallTwoScore() {
    return ballTwoScore;
  }

  public int getBonusBallScore() {
    return bonusBallScore;
  }
}
