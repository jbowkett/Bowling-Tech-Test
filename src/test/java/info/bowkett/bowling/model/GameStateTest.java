package info.bowkett.bowling.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameStateTest {

  @Test
  public void testGameStartsWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);
    Turn next = state.getNextTurn();
    assertEquals(10, next.getAvailablePins());
    assertEquals(1, next.getBallNumber());
    assertEquals(1, next.getFrameNumber());
    assertEquals(players[0], next.getPlayer());
  }

  @Test
  public void testCorrectTurnsWith_Frame_1_ball_2_WithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    Turn ball_2 = state.getNextTurn();
    assertEquals(players[0], ball_2.getPlayer());
  }

  @Test
  public void testCorrectTurnsWith_Frame_2_ball_1_WithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    Turn ball_2 = state.getNextTurn();
    Turn ball_3 = state.getNextTurn();
    assertEquals(players[0], ball_3.getPlayer());
  }

  @Test
  public void testCorrectTurnsWith_Frame_1_ball_2_WithTwoPlayers(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(2);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    Turn ball_2 = state.getNextTurn();
    assertEquals(1, ball_2.getFrameNumber());
    assertEquals(players[0], ball_2.getPlayer());
  }


  @Test
  public void testCorrectTurnsWith_Frame_2_ball_1_WithTwoPlayers(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(2);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    Turn ball_2 = state.getNextTurn();
    Turn ball_3 = state.getNextTurn();
    Turn ball_4 = state.getNextTurn();
    Turn ball_5 = state.getNextTurn();
    assertEquals(2, ball_5.getFrameNumber());
    assertEquals(players[0], ball_5.getPlayer());
  }

  @Test
  public void testCorrectTurnsWith_Frame_10_ball_1_WithTwoPlayers(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(2);
    state.setPlayers(players);
    for (int i = 0; i< 9; i++){
      //player 1
      Turn ball_1 = state.getNextTurn();
      Turn ball_2 = state.getNextTurn();
      //player 2
      Turn ball_3 = state.getNextTurn();
      Turn ball_4 = state.getNextTurn();
    }
    Turn ball_19 = state.getNextTurn();
    assertEquals(10, ball_19.getFrameNumber());
    assertEquals(1, ball_19.getBallNumber());
    assertEquals(players[0], ball_19.getPlayer());
  }

  @Test
  public void testCorrectFrameNumberFollowingAStrikeWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    ball_1.setTally(10);
    Turn ball_2 = state.getNextTurn();
    assertEquals(2, ball_2.getFrameNumber());
  }

  @Test
  public void testCorrectTurnsFollowingAStrikeWithTwoPlayers(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(2);
    state.setPlayers(players);
    Turn ball_1 = state.getNextTurn();
    ball_1.setTally(10);
    Turn ball_2 = state.getNextTurn();
    assertEquals(players[1], ball_2.getPlayer());
  }

  @Test
  public void testOnly10FramesArePlayedWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);

    for (int i = 1; i <= 10; i++){
      Turn ball_1 = state.getNextTurn();
      Turn ball_2 = state.getNextTurn();
    }
    Turn invalidTurn = state.getNextTurn();
    assertEquals(GameState.GAME_OVER, invalidTurn);
  }

  @Test
  public void testCorrectRemainingWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);

    Turn ball_1 = state.getNextTurn();
    ball_1.setTally(3);
    Turn ball_2 = state.getNextTurn();
    assertEquals(7, ball_2.getAvailablePins());
  }

  @Test
  public void testCorrectNumberOfBallsWithPerfectGameWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);

    for (int i = 0; i < 10; i++) {
      final Turn t = state.getNextTurn();
      t.setTally(10);
    }
    final Turn bonus_1 = state.getNextTurn();
    assertEquals(false, GameState.GAME_OVER == bonus_1);

    final Turn bonus_2 = state.getNextTurn();
    assertEquals(false, GameState.GAME_OVER == bonus_2);
  }

  @Test
  public void testCorrectNumberOfBallsWithSpareInLastFrameWithOnePlayer(){
    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);
    //play 9 frames
    for (int i = 0; i < 9; i++) {
      final Turn t = state.getNextTurn();
      t.setTally(10);
    }
    Turn ball_1 = state.getNextTurn();
    ball_1.setTally(3);
    Turn ball_2 = state.getNextTurn();
    ball_2.setTally(7);
    final Turn bonus_1 = state.getNextTurn();
    assertEquals(false, GameState.GAME_OVER == bonus_1);
  }

  public void testCorrectTurnsWith_Frame_10_ball_2_WithTwoPlayers(){

    final GameState state = new GameState();
    final Player[] players = getPlayers(1);
    state.setPlayers(players);


  }






  private Player[] getPlayers(int count) {
    final Player[] players = new Player[count];
    for (int i = 0; i < count; i++) {
      players[i] = new Player("Player #"+i, new TallyCard(), new Scorer());
    }
    return players;
  }
}

