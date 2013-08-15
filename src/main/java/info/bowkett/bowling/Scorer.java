package info.bowkett.bowling;

import info.bowkett.bowling.model.FrameTally;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:56:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Scorer {
  private static final int MAX_FRAMES = 10;
  private static final int STRIKE_TALLY = 10;

  public int getScore(TallyCard tallyCard) {
    int totalScore = 0;
    final List<FrameTally> frames = tallyCard.getFrames();
    for(int i = 0; i < MAX_FRAMES; i++){
      final FrameTally tally = frames.get(i);
      final int frameTotal = tally.getBallOneScore() + tally.getBallTwoScore();

      if(frameIsStrike(tally)){
        totalScore += getStrikeBonus(i, frames);
      }
      else if(frameIsSpare(frameTotal)){
        totalScore += getSpareBonus(i, frames);
      }
      totalScore += frameTotal;
    }
    return totalScore;
  }

  private boolean frameIsStrike(FrameTally tally) {
    return tally.getBallOneScore() == STRIKE_TALLY;
  }

  private boolean frameIsSpare(int frameTotal) {
    return frameTotal == STRIKE_TALLY;
  }

  private int getStrikeBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      final FrameTally nextFrame = nextFrame(index, frames);
      final int nextBallOneScore = nextFrame.getBallOneScore();
      if(frameIsStrike(nextFrame)){
        if(thereIsANextFrameAfter(index, frames)){
          return nextBallOneScore + nextFrame(index+1, frames).getBallOneScore();
        }
        //next frame after is the last frame
        else{
          return nextBallOneScore;
        }
      }
      //next frame not a strike
      else{
        return nextBallOneScore + nextFrame.getBallTwoScore();
      }
    }
    //last frame
    else{
      return frames.get(index).getBallTwoScore() + frames.get(index).getBonusBallScore();
    }
  }

  private boolean thereIsANextFrameAfter(int index, List<FrameTally> frames) {
    return thereIsANextFrame(index+1, frames);
  }

  private int getSpareBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      return nextFrame(index, frames).getBallOneScore();
    }
    return 0;
  }

  private boolean thereIsANextFrame(int index, List<FrameTally> frames) {
    return index+1 < frames.size() && nextFrameNotNull(index, frames);
  }

  private boolean nextFrameNotNull(int index, List<FrameTally> frames) {
    return nextFrame(index, frames) != null;
  }

  private FrameTally nextFrame(int index, List<FrameTally> frames) {
    return frames.get(index+1);
  }
}
