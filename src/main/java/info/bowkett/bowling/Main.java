package info.bowkett.bowling;

import info.bowkett.bowling.io.*;
import info.bowkett.bowling.model.GameState;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:34:30 PM
 *
 * Main entry point for the score card application.
 *
 */
public class Main {

  public void scoreCard(){
    final GameState constraints = new GameState();
    final ScorePrinter scorePrinter = new ScorePrinter();
    final Console console = new Console(System.console());
    final PlayerInputSubShell playerInputSubShell = new PlayerInputSubShell(console);
    final GameRecordingSubShell gameRecordingSubShell = new GameRecordingSubShell(constraints, console);
    final GameSummarySubShell gameSummarySubShell = new GameSummarySubShell(scorePrinter, console);
    final GameConsole gameConsole = new GameConsole(playerInputSubShell, gameRecordingSubShell, gameSummarySubShell, console);
    gameConsole.start();
  }

  public static void main(String [] args){
    final Main main = new Main();
    main.scoreCard();
  }
}
