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
  private final int availablePins;
  private int tally;

  public Turn(int frameNumber, int ballNumber, Player player, int availablePins) {
    this.frameNumber = frameNumber;
    this.ballNumber = ballNumber;
    this.player = player;
    this.availablePins = availablePins;
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
    return availablePins;
  }

  public void setTally(int tally) {
    this.tally = tally;
  }

  public int getTally(){
    return tally;
  }
}
