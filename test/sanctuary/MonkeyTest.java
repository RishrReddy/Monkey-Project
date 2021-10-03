package sanctuary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the all the functionality of Monkey class.
 */
public class MonkeyTest {
  Monkey m1;
  JungleSanctuary primateSanctuary;

  /**
   * Method for initializing object of Monkey class to be used
   * in multiple test cases.
   */
  @Before
  public void setup() {
    primateSanctuary = primate();
    m1 = new Monkey("Jessie", Species.GUEREZA, 9, 6, FavoriteFood.SEEDS, Sex.FEMALE, 15);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when a specieDesignation is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNullSpeciesTest() {
    new Monkey("Alice", null, 10, 12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when age is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNegAgeTest() {
    new Monkey("Alice", Species.DRILL, -1, 12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when weight is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNegWeightTest() {
    new Monkey("Alice", Species.DRILL, 1, -12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when size is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNegSizeTest() {
    new Monkey("Alice", Species.DRILL, 1, 12, FavoriteFood.EGGS, Sex.MALE, -21);
  }


  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNullFoodTest() {
    new Monkey("Alice", Species.DRILL, 1, 12, null, Sex.MALE, -21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when Favorite food is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorNullSexTest() {
    new Monkey("Alice", Species.DRILL, 1, 12, null, Sex.MALE, -21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when gender parameter is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void monkeyConstructorTest() {
    new Monkey("Alice", null, 10, 12, FavoriteFood.EGGS, null, 21);
  }

  /**
   * Unit test case for checking if the getMonkeyName()
   * returns accurate name.
   */
  @Test
  public void getMonkeyName() {
    assertEquals("Jessie", m1.getMonkeyName());
  }

  /**
   * Unit test case for checking if the getMonkeyName()
   * returns accurate name.
   */
  @Test
  public void getSpeciesDesignation() {
    assertEquals(Species.GUEREZA, m1.getSpeciesDesignation());
  }

  /**
   * Unit test case for checking if the getMonkeyAge()
   * returns accurate age.
   */
  @Test
  public void getMonkeyAge() {
    assertEquals(9, m1.getMonkeyAge());
  }

  /**
   * Unit test case for checking if the getMonkeyWeight()
   * returns accurate weight.
   */
  @Test
  public void getMonkeyWeight() {
    assertEquals(6, m1.getMonkeyWeight(), 0.01);
  }

  /**
   * Unit test case for checking if the getMonkeyFavFood()
   * returns accurate favorite food.
   */
  @Test
  public void getMonkeyFavFood() {
    assertEquals(FavoriteFood.SEEDS, m1.getMonkeyFavFood());
  }

  /**
   * Unit test case for checking if the getMonkeySex()
   * returns accurate gender.
   */
  @Test
  public void getMonkeySex() {
    assertEquals(Sex.FEMALE, m1.getMonkeySex());
  }

  /**
   * Unit test case for checking if the getMonkeySize()
   * returns accurate size.
   */
  @Test
  public void getMonkeySize() {
    assertEquals(Size.MEDIUM, m1.getMonkeySize());
  }

  /**
   * Unit test case for checking if the getMonkeyMedicalStatus()
   * returns accurate medical status.
   */
  @Test
  public void getMonkeyMedicalStatus() {
    Monkey m4 = new Monkey("Mickeala", Species.HOWLER, 19, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    assertEquals(true, m4.getMonkeyMedicalStatus());
    m4.updateMonkeyHealthStatus(false);
    assertEquals(false, m4.getMonkeyMedicalStatus());
  }

  /**
   * Unit test case for checking if the getMonkeyID()
   * returns accurate ID.
   */
  @Test
  public void getMonkeyID() {
    assertEquals(1024, m1.getMonkeyId());
  }

  /**
   * Unit test case for checking if the getMonkeyHousingID()
   * returns accurate housing ID.
   */
  @Test
  public void getMonkeyHousingID() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS,
            Sex.MALE, 17.9);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(100, primateSanctuary.getAllMonkeys().get(0).getMonkeyHousingId());
  }

  /**
   * Unit test case for checking if the getMonkeyLocation()
   * returns accurate housing location.
   */
  @Test
  public void getMonkeyLocation() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS,
            Sex.MALE, 17.9);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(CurrentLocStatus.ENCLOSURE,
            primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  /**
   * Unit test case for checking if the getMonkeyLocation() method throws
   * IllegalStateException when monkey is directly sent to enclosure.
   */
  @Test(expected = IllegalStateException.class)
  public void updateMonkeyLocation() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyLocation(CurrentLocStatus.ENCLOSURE);
  }

  /**
   * Unit test case for checking if the getMonkeyLocation() method throws
   * IllegalArgumentException when neg integer is provided as houseID.
   */
  @Test(expected = IllegalArgumentException.class)
  public void updateMonkeyNegHouseID() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyHouseId(-200);
  }

  /**
   * Unit test case for checking if the updateMonkeyHealthStatus() method
   * updates healthStatus of the monkey as provided.
   */
  @Test
  public void updateMonkeyHealthStatus() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyHealthStatus(false);
    assertEquals(false, m2.getMonkeyMedicalStatus());
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