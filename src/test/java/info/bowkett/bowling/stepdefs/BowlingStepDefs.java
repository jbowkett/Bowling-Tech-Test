package info.bowkett.bowling.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import info.bowkett.bowling.Frame;
import info.bowkett.bowling.Player;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 8:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class BowlingStepDefs {

  @Given("^a player with the following scores:$")
  public void a_player_with_the_following_scores(DataTable table) throws Throwable {
    final Player player = new Player("Player_"+System.currentTimeMillis());

    final List<DataTableRow> dataTableRows = table.getGherkinRows();
    final List<String> ballOne = dataTableRows.get(1).getCells();
    final List<String> ballTwo = dataTableRows.get(2).getCells();

    final Frame [] frames = new Frame[ballOne.size()];
    for (int i = 1; i< ballOne.size() ; i++) {
      final String ballOneTallyStr = ballOne.get(i);
      final String ballTwoTallyStr = ballTwo.get(i);

      final int ballOneTally = ballOneTallyStr.equals("-") ? 0 : Integer.parseInt(ballOneTallyStr);
      final int ballTwoTally = ballTwoTallyStr.equals("-") ? 0 : Integer.parseInt(ballTwoTallyStr);
      frames[i] = new Frame(ballOneTally, ballTwoTally);
    }


  }

  @When("^the game is over$")
  public void the_game_is_over() throws Throwable {
    throw new PendingException();
  }

  @Then("^the players score is (\\d+)$")
  public void the_players_score_is(int score) throws Throwable {
    throw new PendingException();
  }


}
