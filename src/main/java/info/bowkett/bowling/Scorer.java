package info.bowkett.bowling;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:56:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Scorer {
  public int getScore(TallyCard tallyCard) {
    int totalScore = 0;
    final List<FrameTally> frames = tallyCard.getFrames();
    for(int i = 0; thereIsANextFrame(i, frames); i++){
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
    return tally.getBallOneScore() == 10;
  }

  private boolean frameIsSpare(int frameTotal) {
    return frameTotal == 10;
  }

  private int getStrikeBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      final FrameTally nextFrame = nextFrame(index, frames);
      return nextFrame.getBallOneScore() + nextFrame.getBallTwoScore();
    }
    return 0;
  }

  private int getSpareBonus(int index, List<FrameTally> frames) {
    if(thereIsANextFrame(index, frames)){
      return nextFrame(index, frames).getBallOneScore();
    }
    return 0;
  }

  private boolean thereIsANextFrame(int index, List<FrameTally> frames) {
    return index < frames.size();
  }

  private FrameTally nextFrame(int index, List<FrameTally> frames) {
    return frames.get(index+1);
  }
}
