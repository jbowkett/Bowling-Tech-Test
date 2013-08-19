package info.bowkett.bowling.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class ScorerTest {

  @Test
  public void testZeroTallyYieldsZeroScore() throws Exception{

    final int [] ballOneScores = {0,0,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores = {0,0,0,0,0,0,0,0,0,0};
    final int [] bonusBallScores = {0,0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(0, score);
  }

  @Test
  public void testOneScoreTallyYieldsTotalScore() throws Exception{

    final int [] ballOneScores =   {5,0,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores =   {1,0,0,0,0,0,0,0,0,0};
    final int [] bonusBallScores = {0,0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(6, score);
  }

  @Test
  public void testSpareAddsNextBallToTotalScore() throws Exception{

    final int [] ballOneScores =   {7,4,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores =   {3,2,0,0,0,0,0,0,0,0};
    final int [] bonusBallScores = {0,0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(20, score);
  }

  @Test
  public void testStrikeAddsNextTwoBallsToTotalScore() throws Exception{

    final int [] ballOneScores =   {10,3,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores =   {0, 6,0,0,0,0,0,0,0,0};
    final int [] bonusBallScores = {0, 0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(28, score);
  }

  @Test
  public void testStrikeAddsNextTwoBallsToTotalScoreWhenAtTheEndOfTheFrame() throws Exception{

    final int [] ballOneScores =   {0,0,0,0,0,0,0,0,10,3};
    final int [] ballTwoScores =   {0,0,0,0,0,0,0,0, 0,6};
    final int [] bonusBallScores = {0,0,0,0,0,0,0,0, 0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(28, score);
  }

  @Test
  public void testFullScoreGives300() throws Exception{

    final int [] ballOneScores =   {10,10,10,10,10,10,10,10,10,10};
    final int [] ballTwoScores =   { 0, 0, 0, 0, 0, 0, 0, 0, 0,10};
    final int [] bonusBallScores = { 0, 0, 0, 0, 0, 0, 0, 0, 0,10};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getScore(tallyCard);
    assertEquals(300, score);
  }

  @Test
  public void testGetFrameScoreAtStartOfScoreCard() throws Exception{

    final int [] ballOneScores =   {10,10,10};
    final int [] ballTwoScores =   { 0, 0, 0};
    final int [] bonusBallScores = { 0, 0, 0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getFrameScore(tallyCard, 1);
    assertEquals(30, score);
  }

  @Test
  public void testGetFrameScoreInMiddleOfScoreCard() throws Exception{

    final int [] ballOneScores =   {10,10,5};
    final int [] ballTwoScores =   { 0, 0,2};
    final int [] bonusBallScores = { 0, 0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getFrameScore(tallyCard, 2);
    assertEquals(17, score);
  }

  @Test
  public void testGetFrameScoreAtEndOfScoreCard() throws Exception{

    final int [] ballOneScores =   {10,10,10};
    final int [] ballTwoScores =   { 0, 0, 0};
    final int [] bonusBallScores = { 0, 0, 0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores, bonusBallScores);

    final int score = new Scorer().getFrameScore(tallyCard, 3);
    assertEquals(10, score);
  }



  private TallyCard mockTallyCard(int[] ballOneScores, int [] ballTwoScores, int [] bonusBallScores) {
    final TallyCard tallyCard = mock(TallyCard.class);
    final List<FrameTally> tallies = new ArrayList<FrameTally>();
    for (int i = 0 ; i< ballOneScores.length ; i++) {
      final FrameTally tally = mock(FrameTally.class);
      when(tally.getBallTally(1)).thenReturn(ballOneScores[i]);
      when(tally.getBallTally(2)).thenReturn(ballTwoScores[i]);
      when(tally.getBallTally(3)).thenReturn(bonusBallScores[i]);
      tallies.add(tally);
    }
    when(tallyCard.getFrames()).thenReturn(tallies);
    return tallyCard;
  }
}

