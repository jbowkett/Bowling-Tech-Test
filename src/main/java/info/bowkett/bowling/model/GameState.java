package info.bowkett.bowling.model;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:57:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameState {
  private Player[] players;

  public static final Turn GAME_OVER = new Turn(-1, -1, null, 0, 0);
  public static final Turn AWAITING_FIRST_TURN = new Turn(0, 0, null, 0, 0);

  private Turn currentTurn = AWAITING_FIRST_TURN;
  private int currentPlayerIndex = 0;

  private static int MAX_FRAMES = 10;

  public void setPlayers(Player[] players) {
    this.players = players;
  }

  public Turn getNextTurn() {
    if (currentTurn == AWAITING_FIRST_TURN) {
      currentTurn = firstTurn();
    }
    else if (currentPlayerFinished()) {
      currentTurn = nextPlayer();
    }
    else {
      currentTurn = samePlayerNextBall();
    }
    return currentTurn;
  }

  private boolean currentPlayerFinished() {
    if(finalFrame()){
      return currentTurn.getBallNumber() == 3 || currentTurn.getBallNumber() == 2 && neitherStrikeNorSpareInFrame();
    }
    return wasPlayersLastBallInFrame() || wasStrike();
  }

  private boolean neitherStrikeNorSpareInFrame() {
    return !wasStrike() && currentTurn.frameTally() < 10;
  }

  private boolean finalFrame() {
    return currentTurn.getFrameNumber() == MAX_FRAMES;
  }

  private boolean wasPlayersLastBallInFrame() {
    return currentTurn.getBallNumber() == 2;
  }

  private boolean wasStrike() {
    return currentTurn.getTally() == 10;
  }

  private Turn firstTurn() {
    return new Turn(1, 1, players[currentPlayerIndex], 10, 0);
  }

  private Turn samePlayerNextBall() {
    return currentTurn.samePlayerNextBall();
  }

  private Turn nextPlayer() {
    final int currentFrame = currentTurn.getFrameNumber();
    if (frameComplete()) {
      return goToNextFrame(currentFrame);
    }
    else {
      return new Turn(currentFrame, 1, players[++currentPlayerIndex], 10, 0);
    }
  }

  private Turn goToNextFrame(int currentFrame) {
    goToFirstPlayer();
    if (thereIsANextFrame(currentFrame)) {
      return new Turn(currentFrame + 1, 1, players[currentPlayerIndex], 10, 0);
    }
    return GAME_OVER;
  }

  private boolean thereIsANextFrame(int currentFrame) {
    return currentFrame < MAX_FRAMES;
  }

  private void goToFirstPlayer() {
    currentPlayerIndex = 0;
  }

  private boolean frameComplete() {
    return currentPlayerIndex + 1 == players.length;
  }
}
