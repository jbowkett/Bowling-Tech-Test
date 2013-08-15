package info.bowkett.bowling;

import java.util.Iterator;

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
    final Iterator<FrameTally> frameIterator = tallyCard.iterator();
    while(frameIterator.hasNext()){
      final FrameTally tally = frameIterator.next();
      totalScore += tally.getBallOneScore();
      totalScore += tally.getBallTwoScore();
    }

    return totalScore;
  }
}
