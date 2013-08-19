package info.bowkett.bowling.model;

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

  //strike is a scoring concept
  private static final int STRIKE_TALLY = 10;

  public int getScore(TallyCard tallyCard) {
    int totalScore = 0;
    final List<FrameTally> frames = tallyCard.getFrames();
    for(int i = 0; i < MAX_FRAMES; i++){
      int frameScore = getFrameScore(frames, i);
      totalScore += frameScore;
    }
    return totalScore;
  }

  public int getFrameScore(TallyCard tallyCard, int index){
    return getFrameScore(tallyCard.getFrames(), index);
  }


  private int getFrameScore(List<FrameTally> frames, int index) {
    final FrameTally tally = frames.get(index);
    int frameScore = 0;
    frameScore += tally.getBallOneTally() + tally.getBallTwoTally();
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
    return tally.getBallOneTally() == STRIKE_TALLY;
  }

  private boolean frameIsSpare(FrameTally frame) {
    if(!frameIsStrike(frame)){
      final int frameScore = frame.getBallOneTally() + frame.getBallTwoTally();
      return frameScore == STRIKE_TALLY;
    }
    return false;
  }

  private int getStrikeBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      final FrameTally nextFrame = nextFrame(index, frames);
      final int nextBallOneScore = nextFrame.getBallOneTally();
      if(frameIsStrike(nextFrame)){
        if(thereIsANextFrameAfter(index, frames)){
          return nextBallOneScore + nextFrame(index+1, frames).getBallOneTally();
        }
        //next frame after is the last frame
        else{
          return nextBallOneScore;
        }
      }
      //next frame not a strike
      else{
        return nextBallOneScore + nextFrame.getBallTwoTally();
      }
    }
    //last frame
    else{
      return frames.get(index).getBallTwoTally() + frames.get(index).getBonusBallTally();
    }
  }

  private boolean thereIsANextFrameAfter(int index, List<FrameTally> frames) {
    return thereIsANextFrame(index+1, frames);
  }

  private int getSpareBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      return nextFrame(index, frames).getBallOneTally();
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
