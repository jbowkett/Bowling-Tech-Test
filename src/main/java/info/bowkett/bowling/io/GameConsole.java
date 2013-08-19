package info.bowkett.bowling.io;

import info.bowkett.bowling.model.Player;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 15, 2013
 * Time: 11:57:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameConsole {
  private final PlayerInputSubShell playerInputSubShell;
  private final GameRecordingSubShell gameRecordingSubShell;
  private final GameSummarySubShell gameSummarySubShell;
  private final Console console;

  public GameConsole(PlayerInputSubShell playerInputSubShell, GameRecordingSubShell gameRecordingSubShell, GameSummarySubShell gameSummarySubShell, Console console) {
    this.playerInputSubShell = playerInputSubShell;
    this.gameRecordingSubShell = gameRecordingSubShell;
    this.gameSummarySubShell = gameSummarySubShell;
    this.console = console;
  }

  public void start() {
    printIntro();
    try {
      final Player[] players = playerInputSubShell.start();
      gameRecordingSubShell.setPlayers(players);
      gameRecordingSubShell.start();
      gameSummarySubShell.start();
    }
    catch (QuitException e) {
    }
  }

  private void printIntro() {
    console.msgln("\n\n\nWelcome to the Bowling Scorecard!");
    console.msgln("=================================");
    console.msgln("You may exit at any time by typing \"quit\".");
  }
}
