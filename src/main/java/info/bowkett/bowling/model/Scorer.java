package info.bowkett.bowling.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:56:50 PM
 *
 * Immutable class (it has no state) to calculate a score from a player's pin
 * tally, either for a particular frame, (with look ahead to further frames
 * when there are strikes or spares)
 *
 */
public class Scorer {
  private static final int MAX_FRAMES = 10;

  //strike is a scoring concept
  private static final int STRIKE_TALLY = 10;

  public int getTotalScore(TallyCard tallyCard) {
    int totalScore = 0;
    for(int i = 1; i <= MAX_FRAMES; i++){
      int frameScore = getFrameScore(tallyCard, i);
      totalScore += frameScore;
    }
    return totalScore;
  }

  public int getFrameScore(TallyCard tallyCard, int frameNumber){
    return getFrameScore(tallyCard.getFrames(), frameToIndex(frameNumber));
  }

  private int frameToIndex(int frameNumber) {
    return frameNumber - 1;
  }

  private int getFrameScore(List<FrameTally> frames, int index) {
    final FrameTally tally = frames.get(index);
    int frameScore = 0;
    frameScore += tally.getBallTally(1) + tally.getBallTally(2);
    frameScore += getBonusPoints(frames, index, tally);
    return frameScore;
  }

  private int getBonusPoints(List<FrameTally> frames, int i, FrameTally tally) {
    int bonusPoints = 0;
    if(frameIsStrike(tally)){
      bonusPoints += getStrikeBonus(i, frames);
    }
    else if(frameIsSpare(tally)){
      bonusPoints += getSpareBonus(i, frames);
    }
    return bonusPoints;
  }

  private boolean frameIsStrike(FrameTally tally) {
    return tally.getBallTally(1) == STRIKE_TALLY;
  }

  private boolean frameIsSpare(FrameTally frame) {
    if(!frameIsStrike(frame)){
      final int frameScore = frame.getBallTally(1) + frame.getBallTally(2);
      return frameScore == STRIKE_TALLY;
    }
    return false;
  }

  private int getStrikeBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      final FrameTally nextFrame = nextFrame(index, frames);
      final int nextBallOneScore = nextFrame.getBallTally(1);
      if(frameIsStrike(nextFrame)){
        if(thereIsANextFrameAfter(index, frames)){
          return nextBallOneScore + nextFrame(index+1, frames).getBallTally(1);
        }
        //next frame after is the last frame
        else{
          return nextBallOneScore;
        }
      }
      //next frame not a strike
      else{
        return nextBallOneScore + nextFrame.getBallTally(2);
      }
    }
    //last frame
    else{
      return frames.get(index).getBallTally(2) + frames.get(index).getBallTally(3);
    }
  }

  private boolean thereIsANextFrameAfter(int index, List<FrameTally> frames) {
    return thereIsANextFrame(index+1, frames);
  }

  private int getSpareBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      return nextFrame(index, frames).getBallTally(1);
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
