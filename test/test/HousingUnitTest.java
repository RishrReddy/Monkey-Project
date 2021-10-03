package test;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import sanctuary.Animal;
import sanctuary.CurrentLocStatus;
import sanctuary.FavoriteFood;
import sanctuary.Housing;
import sanctuary.HousingUnit;
import sanctuary.JungleSanctuary;
import sanctuary.Sex;
import sanctuary.Species;

/**
 * Class for testing the all the functionality of Housing Units class.
 */
public class HousingUnitTest {
  static Housing h1;
  JungleSanctuary primateSanctuary;

  /**
   * Method for initializing object of HousingUnit and JungleSanctuary class to be used
   * in multiple test cases.
   */
  @Before
  public void setUp() {
    h1 = new HousingUnit(10, 20);
    primateSanctuary = primate();
  }

  /**
   * Unit test case for checking if the increaseIsolations()
   * increases the number of isolations.
   */
  @Test
  public void increaseIsolationsTest() {
    h1.increaseIsolations(10);
    assertEquals(20, h1.getTotalIsolationUnits());
  }

  /**
   * Unit test case for checking if the increaseEnclosures()
   * increases the number of enclosures.
   */
  @Test
  public void increaseEnclosuresTest() {
    h1.increaseEnclosures(10);
    assertEquals(30, h1.getTotalEnclosureUnits());
  }

  /**
   * Unit test case for checking if increaseIsolations() throw the IllegalArgumentException
   * when the number of isolations provided is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void increaseIsolationsNegTest() {
    h1.increaseIsolations(-10);
  }

  /**
   * Unit test case for checking if increaseIsolations() throw the IllegalArgumentException
   * when the number of enclosures provided is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void increaseEnclosuresNeg() {
    h1.increaseIsolations(-10);
  }

  /**
   * Unit test case for checking if checkAvailabilityInIsolation() returns true when
   * there is space in isolation.
   */
  @Test
  public void testCheckAvailabilityInIsolation() {
    Housing h2 = new HousingUnit(10, 2);
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertTrue(h2.checkAvailabilityInIsolation());
  }

  /**
   * Unit test case for checking if checkAvailabilityInIsolation() returns false when
   * there is no space in isolation.
   */
  @Test
  public void testCheckAvailabilityInIsolationNeg() {
    Housing h2 = new HousingUnit(1, 2);
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertFalse(h2.checkAvailabilityInIsolation());
  }

  /**
   * Unit test case for checking if sendMonkeyToIsolation() send monkey
   * to isolation.
   */
  @Test
  public void testSendMonkeyToIsolation() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertEquals(CurrentLocStatus.ISOLATION,
            primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  @Test
  public void testSendMonkeyToIsolationFromEnc() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertEquals(CurrentLocStatus.ISOLATION,
            primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0), false);
  }

  /**
   * Unit test case for checking if checkAvailabilityInEnclosures() returns true
   * when there is space in enclosure.
   */
  @Test
  public void testCheckAvailabilityInEnclosuresNullCase() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertTrue(h1.checkAvailabilityInEnclosures(primateSanctuary.getAllMonkeys().get(0)));
  }

  /**
   * Unit test case for checking if checkAvailabilityInEnclosures() returns false
   * when there is no space in enclosure.
   */
  @Test
  public void testCheckAvailabilityInEnclosuresNegCase() {
    Housing h2 = new HousingUnit(2, 1);
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Zen", Species.DRILL, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertFalse(h2.checkAvailabilityInEnclosures(primateSanctuary.getAllMonkeys().get(0)));
  }

  /**
   * Method to create object of JungleSanctuary.
   *
   * @return JungleSanctuary object
   */
  protected JungleSanctuary primate() {
    return new Animal(10, 20);
  }

}