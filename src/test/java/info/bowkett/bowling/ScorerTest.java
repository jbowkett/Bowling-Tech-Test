package info.bowkett.bowling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
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

  private TallyCard mockTallyCard(int[] ballOneScores, int [] ballTwoScores) {
    final TallyCard tallyCard = mock(TallyCard.class);
    final List<FrameTally> tallies = new ArrayList<FrameTally>();
    for (int i = 0 ; i< ballOneScores.length ; i++) {
      final FrameTally tally = mock(FrameTally.class);
      when(tally.getBallOneScore()).thenReturn(ballOneScores[i]);
      when(tally.getBallTwoScore()).thenReturn(ballTwoScores[i]);
      tallies.add(tally);
    }

    when(tallyCard.iterator()).thenReturn(tallies.iterator());
    return tallyCard;
  }

  @Before
  public void init() throws Exception{
    scorer = new Scorer();

  }


}

