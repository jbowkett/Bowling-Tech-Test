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
    for(int i = 0; i< frames.size(); i++){
      final FrameTally tally = frames.get(i);
      final int frameTotal = tally.getBallOneScore() + tally.getBallTwoScore();
      if(frameIsSpare(frameTotal)){
        totalScore += getSpareBonus(i, frames);
      }
      totalScore += frameTotal;
    }

    return totalScore;
  }

  private int getSpareBonus(int index, List<FrameTally> frames) {
    if(index < frames.size()){
      return frames.get(index+1).getBallOneScore();
    }
    return 0;
  }

  private boolean frameIsSpare(int frameTotal) {
    return frameTotal == 10;
  }
}
