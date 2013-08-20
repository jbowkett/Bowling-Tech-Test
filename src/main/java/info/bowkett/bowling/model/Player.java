package info.bowkett.bowling.model;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:23:45 PM
 *
 * Immutable class to represent a player
 *
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

  public void addFrameTally(int frameNumber, FrameTally frameTally) {
    tallyCard.add(frameNumber, frameTally);
  }

  public int getTotalScore() {
    return scorer.getScore(tallyCard);
  }

  public TallyCard getTallyCard(){
    return tallyCard;
  }

  public Scorer getScorer() {
    return scorer;
  }
}
