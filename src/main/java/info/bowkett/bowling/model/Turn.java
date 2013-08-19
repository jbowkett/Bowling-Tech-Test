package info.bowkett.bowling.model;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 18, 2013
 * Time: 9:35:42 PM
 *
 * Describes a ball about to be thrown, i.e. a Player's turn
 *
 */
public class Turn {
  private final int frameNumber;
  private final int ballNumber;
  private final Player player;
  private int availablePins;
  private int frameTally;
  private int tally;
  private static final int MAX_PINS = 10;

  public Turn(int frameNumber, int ballNumber, Player player, int availablePins, int frameTally) {
    this.frameNumber = frameNumber;
    this.ballNumber = ballNumber;
    this.player = player;
    this.availablePins = availablePins;
    this.frameTally = frameTally;
  }

  public int getFrameNumber() {
    return frameNumber;
  }

  public int getBallNumber() {
    return ballNumber;
  }

  public Player getPlayer() {
    return player;
  }

  public int getAvailablePins() {
    if(finalFrame()){
      if(wasStrikeOnFirstBall() || wasSpareOnFirstTwoBalls()){
        availablePins = 10;
      }
    }
    return availablePins;
  }

  private boolean wasSpareOnFirstTwoBalls() {
    return ballNumber == 3 && frameTally == 20;
  }

  private boolean wasStrikeOnFirstBall() {
    return ballNumber == 2 && frameTally == 10;
  }

  private boolean finalFrame() {
    return frameNumber == 10;
  }

  public void setTally(int tally) {
    this.tally = tally;
    frameTally += tally;
  }

  public int getTally(){
    return tally;
  }

  public Turn samePlayerNextBall() {
    return new Turn(frameNumber, ballNumber+1, player, MAX_PINS - tally, frameTally);
  }

  public int frameTally() {
    return frameTally;
  }
}
