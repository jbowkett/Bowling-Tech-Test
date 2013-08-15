package info.bowkett.bowling.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import info.bowkett.bowling.model.FrameTally;
import info.bowkett.bowling.Player;
import info.bowkett.bowling.Scorer;
import info.bowkett.bowling.TallyCard;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class BowlingStepDefs {
  private FrameTally[] frameTallies;
  private Player player;
  private static final int GHERKIN_STARTING_INDEX = 1;

  @Given("^a player with the following scores:$")
  public void a_player_with_the_following_scores(DataTable table) throws Throwable {
    player = new Player("Player_" + System.currentTimeMillis(), new TallyCard(), new Scorer());

    final List<DataTableRow> dataTableRows = table.getGherkinRows();
    final List<String> ballOne = dataTableRows.get(1).getCells();
    final List<String> ballTwo = dataTableRows.get(2).getCells();
    final List<String> bonusBall = dataTableRows.get(3).getCells();

    frameTallies = new FrameTally[ballOne.size()];
    for (int index = GHERKIN_STARTING_INDEX; index < ballOne.size(); index++) {
      final String ballOneTallyStr = ballOne.get(index);
      final String ballTwoTallyStr = ballTwo.get(index);

      final int ballOneTally = getBallTally(ballOneTallyStr);
      final int ballTwoTally = getBallTally(ballTwoTallyStr);
      if (isLastFrame(ballOne, index)) {
        final int bonusBallTally = getBallTally(bonusBall.get(index));
        frameTallies[index - 1] = FrameTally.bonusFrame(ballOneTally, ballTwoTally, bonusBallTally);
      }
      else {
        frameTallies[index - 1] = FrameTally.ordinaryFrame(ballOneTally, ballTwoTally);
      }
    }
  }

  private boolean isLastFrame(List<String> ballOne, int i) {
    return i == ballOne.size() - 1;
  }

  private int getBallTally(String ballOneTallyStr) {
    return ballOneTallyStr.equals("-") ? 0 : Integer.parseInt(ballOneTallyStr);
  }


  @When("^the game is played$")
  public void the_game_is_played() throws Throwable {
    for (FrameTally frameTally : frameTallies) {
      player.addFrameTally(frameTally);
    }
  }

  @Then("^the player's score is (\\d+)$")
  public void the_player_s_score_is(int score) throws Throwable {
    assertEquals(score, player.getScore());
  }
}
