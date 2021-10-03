package sanctuary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the all the functionality of Isolation class.
 */
public class IsolationTest {

  Isolation e1;
  Isolation e2;

  /**
   * Method for initializing object of Isolation class to be used
   * in multiple test cases.
   */
  @Before
  public void setup() {
    e1 = new Isolation();
    e2 = new Isolation();
  }

  /**
   * Unit test case for checking if the ID is generated
   * correctly.
   */
  @Test
  public void getIsolationID() {
    assertEquals(500, e1.getIsolationId());
    assertEquals(501, e2.getIsolationId());
  }
}