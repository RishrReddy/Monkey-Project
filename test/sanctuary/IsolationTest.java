package sanctuary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsolationTest {

  Isolation e1;
  Isolation e2 ;

  @Before
  public void setup(){
    e1= new Isolation();
    e2= new Isolation();
  }
  @Test
  public void getIsolationID() {
    assertEquals(500,e1.getIsolationID());
    assertEquals(501,e2.getIsolationID());
  }
}