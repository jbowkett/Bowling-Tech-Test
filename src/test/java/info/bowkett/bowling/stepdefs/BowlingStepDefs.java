package info.bowkett.bowling.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import info.bowkett.bowling.FrameTally;
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

  @Given("^a player with the following scores:$")
  public void a_player_with_the_following_scores(DataTable table) throws Throwable {
    player = new Player("Player_"+System.currentTimeMillis(), new TallyCard(), new Scorer());

    final List<DataTableRow> dataTableRows = table.getGherkinRows();
    final List<String> ballOne = dataTableRows.get(1).getCells();
    final List<String> ballTwo = dataTableRows.get(2).getCells();

    frameTallies = new FrameTally[ballOne.size()];
    for (int i = 1; i< ballOne.size() ; i++) {
      final String ballOneTallyStr = ballOne.get(i);
      final String ballTwoTallyStr = ballTwo.get(i);

      final int ballOneTally = ballOneTallyStr.equals("-") ? 0 : Integer.parseInt(ballOneTallyStr);
      final int ballTwoTally = ballTwoTallyStr.equals("-") ? 0 : Integer.parseInt(ballTwoTallyStr);
      frameTallies[i] = new FrameTally(ballOneTally, ballTwoTally);
    }
  }


  @When("^the game is played$")
  public void the_game_is_played() throws Throwable {
    for (FrameTally frameTally : frameTallies) {
      player.addFrameTally(frameTally);
    }
  }

  @Then("^the players score is (\\d+)$")
  public void the_players_score_is(int score) throws Throwable {
    assertEquals(score, player.getScore());
  }
}
