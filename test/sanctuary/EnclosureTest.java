package sanctuary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the all the functionality of Enclosure class.
 */
public class EnclosureTest {
  static Enclosure e1;
  static Enclosure e2;
  JungleSanctuary primateSanctuary = primate();

  /**
   * Method for initializing object of Enclosure class to be used
   * in multiple test cases.
   */
  @Before
  public void setup() {
    e1 = new Enclosure(100, Species.DRILL);
    e2 = new Enclosure(50, Species.HOWLER);
  }

  /**
   * Unit test case for checking if the ID is generated
   * correctly.
   */
  @Test
  public void getEnclosureIDTest() {
    assertEquals(102, e1.getEnclosureid());
    assertEquals(103, e2.getEnclosureid());
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when a total area threshold is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EnclosureNegAreaTest() {
    Enclosure e = new Enclosure(-100, Species.DRILL);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when a designated species is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EnclosureSpecieNullTest() {
    Enclosure e = new Enclosure(100, null);
  }

  /**
   * Unit test case for checking if the getSpecieHousedTest()
   * method to see if it returns accurate specie type.
   */
  @Test
  public void getSpecieHousedTest() {
    assertEquals(Species.DRILL, e1.getSpecieHoused());
    assertEquals(Species.HOWLER, e2.getSpecieHoused());
  }

  /**
   * Unit test case for checking if the getAvailableAreaTest()
   * method to see if it returns accurate available area.
   */
  @Test
  public void getAvailableAreaTest() {
    assertEquals(100, e1.getAvailableArea(),
            0.001);
  }

  /**
   * Unit test case for checking if the updateAvailableAreaDecrease()
   * method to see if it decreases the area of the enclosure based on the value provided.
   */
  @Test
  public void updateAvailableAreaDecrease() {
    e1.updateAvailableArea(10, true);
    assertEquals(90, e1.getAvailableArea(), 0.1);
  }

  /**
   * Unit test case for checking if the updateAvailableAreaIncrease()
   * method to see if it decreases the area of the enclosure based on the value provided.
   */
  @Test
  public void updateAvailableAreaIncrease() {
    e1.updateAvailableArea(10, false);
    assertEquals(110, e1.getAvailableArea(), 0.1);
  }

  /**
   * Method to create object of JungleSanctuary.
   *
   * @return JungleSanctuary object
   */
  protected JungleSanctuary primate() {
    return new Animal(10, 20);
  }

  /**
   * Method to create object of enclosure.
   *
   * @param area Total area of enclosure
   * @param s    specie designation of the monkey.
   * @return Housing object
   */
  protected Housing enclosure(double area, Species s) {
    return new Enclosure(area, s);
  }
}