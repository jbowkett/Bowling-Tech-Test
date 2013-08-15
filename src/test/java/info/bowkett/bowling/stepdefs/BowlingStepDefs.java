package info.bowkett.bowling.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;

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
    final List<DataTableRow> dataTableRows = table.getGherkinRows();
    final DataTableRow ballOne = dataTableRows.get(1);
    final DataTableRow ballTwo = dataTableRows.get(2);
  }

  @When("^the game is over$")
  public void the_game_is_over() throws Throwable {
    throw new PendingException();
  }

  @Then("^the players score is (\\d+)$")
  public void the_players_score_is(int arg1) throws Throwable {
    throw new PendingException();
  }


}
