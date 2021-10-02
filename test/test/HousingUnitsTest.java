package test;


import org.junit.Before;
import org.junit.Test;

import sanctuary.Animal;
import sanctuary.CurrentLocStatus;
import sanctuary.FavoriteFood;
import sanctuary.Housing;
import sanctuary.HousingUnits;
import sanctuary.JungleSanctuary;
import sanctuary.Sex;
import sanctuary.Species;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HousingUnitsTest {
  Housing h1;
  JungleSanctuary primateSanctuary;
  @Before
  public void setUp(){
    h1=new HousingUnits(10,1);
    primateSanctuary = primate();
  }

  @Test
  public void testIncreaseIsolations() {
    h1.increaseIsolations(10);
    assertEquals(20,h1.getTotalIsolationUnits());
  }

  @Test
  public void testIncreaseEnclosures() {
    h1.increaseEnclosures(10);
    assertEquals(30,h1.getTotalEnclosureUnits());
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIncreaseIsolationsNeg() {
    h1.increaseIsolations(-10);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIncreaseEnclosuresNeg() {
    h1.increaseIsolations(-10);
  }

  @Test
  public void testCheckAvailabilityInIsolation() {
    Housing h2 = new HousingUnits(10,2);
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertTrue(h2.checkAvailabilityInIsolation());
  }

  @Test
  public void testSendMonkeyToIsolation() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertEquals(CurrentLocStatus.ISOLATION,primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  @Test
  public void testCheckAvailabilityInEnclosuresNullCase() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertTrue(h1.checkAvailabilityInEnclosures(primateSanctuary.getAllMonkeys().get(0)));
  }

  @Test
  public void testCheckAvailabilityInEnclosuresPosCase() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Zen", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    assertTrue(h1.checkAvailabilityInEnclosures(primateSanctuary.getAllMonkeys().get(0)));
  }

  protected JungleSanctuary primate(){
    return new Animal();
  }

}