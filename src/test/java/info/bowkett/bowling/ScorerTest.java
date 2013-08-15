package info.bowkett.bowling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class ScorerTest {
  private Scorer scorer;

  @Test
  public void testZeroTallyYieldsZeroScore() throws Exception{

    final int [] ballOneScores = {0,0,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores = {0,0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores);

    final int score = scorer.getScore(tallyCard);
    assertEquals(0, score);
  }

  @Test
  public void testOneScoreTallyYieldsTotalScore() throws Exception{

    final int [] ballOneScores = {5,0,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores = {1,0,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores);

    final int score = scorer.getScore(tallyCard);
    assertEquals(6, score);
  }

  @Test
  public void testSpareAddsNextBallToTotalScore() throws Exception{

    final int [] ballOneScores = {7,4,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores = {3,2,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores);

    final int score = scorer.getScore(tallyCard);
    assertEquals(20, score);
  }

  @Test
  public void testStrikeAddsNextTwoBallsToTotalScore() throws Exception{

    final int [] ballOneScores = {10,3,0,0,0,0,0,0,0,0};
    final int [] ballTwoScores = {0, 6,0,0,0,0,0,0,0,0};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores);

    final int score = scorer.getScore(tallyCard);
    assertEquals(28, score);
  }

  @Test
  public void testStrikeAddsNextTwoBallsToTotalScoreWhenAtTheEndOfTheFrame() throws Exception{

    final int [] ballOneScores = {0,0,0,0,0,0,0,0,10,3};
    final int [] ballTwoScores = {0,0,0,0,0,0,0,0, 0,6};

    final TallyCard tallyCard = mockTallyCard(ballOneScores, ballTwoScores);

    final int score = scorer.getScore(tallyCard);
    assertEquals(28, score);
  }



  private TallyCard mockTallyCard(int[] ballOneScores, int [] ballTwoScores) {
    final TallyCard tallyCard = mock(TallyCard.class);
    final List<FrameTally> tallies = new ArrayList<FrameTally>();
    for (int i = 0 ; i< ballOneScores.length ; i++) {
      final FrameTally tally = mock(FrameTally.class);
      when(tally.getBallOneScore()).thenReturn(ballOneScores[i]);
      when(tally.getBallTwoScore()).thenReturn(ballTwoScores[i]);
      tallies.add(tally);
    }
    when(tallyCard.getFrames()).thenReturn(tallies);
    return tallyCard;
  }

  @Before
  public void init() throws Exception{
    scorer = new Scorer();
  }
}

