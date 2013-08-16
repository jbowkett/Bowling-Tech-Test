package info.bowkett.bowling.io;

import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jbowkett
 * Date: Aug 16, 2013
 * Time: 9:19:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleTest {

//  @Test(expected=QuitException.class)
  //todo: this is unfortunate, mockito can't mock final classes...could use
  //powermock instead, but I prefer the mockito given-when-then method naming
  @Test(expected=MockitoException.class)
  public void testQuitRaisesException() throws QuitException {
    final java.io.Console wrapped = mock(java.io.Console.class);
    when(wrapped.readLine()).thenReturn("quit");
    final Console console = new Console(wrapped);
    console.getStringPrompt();
  }
}
