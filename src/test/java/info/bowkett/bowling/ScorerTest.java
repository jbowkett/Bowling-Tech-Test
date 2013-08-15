package info.bowkett.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class ScorerTest {
  private Scorer scorer;

  @Test
  public void testZeroTallyYieldsZeroScore() throws Exception{

    final TallyCard tallyCard = mock(TallyCard.class);
    final int score = scorer.getScore(tallyCard);
    assertEquals(0, score);
  }

  @Before
  public void init() throws Exception{
    scorer = new Scorer();

  }


}

