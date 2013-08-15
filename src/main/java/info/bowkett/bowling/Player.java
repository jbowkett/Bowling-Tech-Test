package info.bowkett.bowling;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:23:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
  private final String name;
  private TallyCard tallyCard;
  private Scorer scorer;

  public Player(String name, TallyCard tallyCard, Scorer scorer) {
    this.name = name;
    this.tallyCard = tallyCard;
    this.scorer = scorer;
  }

  public String getName() {
    return name;
  }

  public void addFrameTally(FrameTally frameTally) {
    tallyCard.add(frameTally);
    
  }

  public int getScore() {
    return scorer.getScore(tallyCard);
  }
}
