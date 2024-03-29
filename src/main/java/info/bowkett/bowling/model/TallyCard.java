package info.bowkett.bowling.model;

import info.bowkett.bowling.model.FrameTally;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:50:09 PM
 *
 * A Player's pin tally for all frames in a game
 *
 */
public class TallyCard {

  private static final int MAX_FRAMES = 10;
  private final FrameTally[] allFrames = new FrameTally[MAX_FRAMES];

  public TallyCard(){
    fillWithEmptyFrames();
  }

  private void fillWithEmptyFrames() {
    for (int i = 0; i < allFrames.length; i++) {
      allFrames[i] = FrameTally.emptyFrame();
    }
  }

  public void add(int frameNumber, FrameTally frameTally) {
    allFrames[frameToIndex(frameNumber)] = frameTally;
  }

  public List<FrameTally> getFrames() {
    return Collections.unmodifiableList(Arrays.asList(allFrames));
  }

  public FrameTally getFrameTallyForFrame(int frameNumber) {
    return allFrames[frameToIndex(frameNumber)];

  }

  private int frameToIndex(int frameNumber) {
    return frameNumber - 1;
  }
}
