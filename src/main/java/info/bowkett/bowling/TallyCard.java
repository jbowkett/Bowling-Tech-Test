package info.bowkett.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:50:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TallyCard {

  private final List<FrameTally> allFrames = new ArrayList<FrameTally>();

  public void add(FrameTally frameTally) {
    allFrames.add(frameTally);
  }

  public Iterator<FrameTally> iterator() {
    return allFrames.iterator();
  }
}
