package info.bowkett.bowling.model;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:31:35 PM
 *
 * Immutable class represents the amount of pins knocked down in a frame
 *
 */
public class FrameTally {
  private final int ballOneTally;
  private final int ballTwoTally;
  private final int bonusBallTally;

  public FrameTally(int ballOneTally, int ballTwoTally, int bonusBallTally) {
    this.ballOneTally = ballOneTally;
    this.ballTwoTally = ballTwoTally;
    this.bonusBallTally = bonusBallTally;
  }
  
  public static FrameTally ordinaryFrame(int ballOneScore, int ballTwoScore){
    return new FrameTally(ballOneScore, ballTwoScore, 0);
  }

  public static FrameTally bonusFrame(int ballOneScore, int ballTwoScore, int bonusBallScore){
    return new FrameTally(ballOneScore, ballTwoScore, bonusBallScore);
  }

  public int getBallOneTally() {
    return ballOneTally;
  }

  public int getBallTwoTally() {
    return ballTwoTally;
  }

  public int getBonusBallTally() {
    return bonusBallTally;
  }

  public int getBallTally(int ball) {
    switch(ball){
      case 1 : return ballOneTally;
      case 2 : return ballTwoTally;
      case 3 : return bonusBallTally;
      default : throw new IllegalArgumentException("Invalid ball# :["+ball+"]");
    }
  }
}
