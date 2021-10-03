package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.Animal;
import sanctuary.CurrentLocStatus;
import sanctuary.FavoriteFood;
import sanctuary.Housing;
import sanctuary.HousingUnit;
import sanctuary.JungleSanctuary;
import sanctuary.Monkey;
import sanctuary.Sex;
import sanctuary.Size;
import sanctuary.Species;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class for testing the all the functionality of Monkey class.
 */
public class AnimalTest {
  private static JungleSanctuary primateSanctuary;
  private static Housing house;

  /**
   * Method for initializing object of Monkey class to be used
   * in multiple test cases.
   */
  @Before
  public void setup() {
    primateSanctuary = primate();
    house = house(3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sanctuaryCreationNegEnclosuresTest() {
    JungleSanctuary primate = new Animal(10, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sanctuaryCreationNegIsolationsTest() {
    JungleSanctuary primate = new Animal(-10, 1);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when a specieDesignation is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNullSpeciesTest() {
    primateSanctuary.addNewMonkey("Alice", null, 10, 12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when age is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNegAgeTest() {
    primateSanctuary.addNewMonkey("Alice", Species.DRILL, -1, 12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when weight is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNegWeightTest() {
    primateSanctuary.addNewMonkey("Alice", Species.DRILL, 1, -12, FavoriteFood.EGGS, Sex.MALE, 21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when size is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNegSizeTest() {
    primateSanctuary.addNewMonkey("Alice", Species.DRILL, 1, 12, FavoriteFood.EGGS, Sex.MALE, -21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when Favorite food is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNullFoodTest() {
    primateSanctuary.addNewMonkey("Alice", Species.DRILL, 1, 12, null, Sex.MALE, -21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when Favorite food is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyNullSexTest() {
    primateSanctuary.addNewMonkey("Alice", Species.DRILL, 1, 12, null, Sex.MALE, -21);
  }

  /**
   * Unit test case for checking if the IllegalArgumentException is
   * thrown when invalid monkey is added to the sanctuary.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNewMonkeyTest() {
    primateSanctuary.addNewMonkey("Alice", null, 10, 12, FavoriteFood.EGGS, null, 21);
  }

  /**
   * Unit test case for checking if the addNewMonkey()
   * sends monkey to the isolation succesfully.
   */
  @Test
  public void addedMonkeyTransferToIsolation() {
    boolean isMickPresent = false;
    for (Monkey monkey : primateSanctuary.getAllMonkeys()) {
      if (monkey.getMonkeyName().equals("Mick")) {
        isMickPresent = true;
      }
    }
    CurrentLocStatus actualLocation = CurrentLocStatus.UNASSIGNED;
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    for (Monkey monkey : primateSanctuary.getAllMonkeys()) {
      if (monkey.getMonkeyName().equals("Mick")) {
        actualLocation = monkey.getMonkeyLocation();
      }
    }
    assertEquals(false, isMickPresent);
    assertEquals(CurrentLocStatus.ISOLATION, actualLocation);
  }

  /**
   * Unit test case for checking if the addNewMonkey()
   * sends monkey to the list of monkeys that is maintained.
   */
  @Test
  public void addNewMonkeyToSanctuaryTest() {
    int monkeyInSanctuaryBefore = primateSanctuary.getAllMonkeys().size();
    System.out.println("monkeyInSanctuaryBefore : " + monkeyInSanctuaryBefore);
    primateSanctuary.addNewMonkey("Olive", Species.GUEREZA, 15, 60,
            FavoriteFood.SEEDS, Sex.FEMALE, 25);
    assertEquals(monkeyInSanctuaryBefore + 1, primateSanctuary.getAllMonkeys().size());
  }

  protected JungleSanctuary primate() {
    return new Animal(10, 20);
  }

  protected Housing house(int isolations, int enclosures) {
    return new HousingUnit(isolations, enclosures);
  }

  /**
   * Unit test case for checking if the getAllMonkeys()
   * produces list of monkeys correctly.
   */
  @Test
  public void getAllMonkeysTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    assertEquals(2, primateSanctuary.getAllMonkeys().size());
    assertEquals("Zeke", primateSanctuary.getAllMonkeys().get(0).getMonkeyName());
    assertEquals("Mick", primateSanctuary.getAllMonkeys().get(1).getMonkeyName());
    assertEquals(Species.HOWLER, primateSanctuary.getAllMonkeys().get(0).getSpeciesDesignation());
    assertEquals(Species.HOWLER, primateSanctuary.getAllMonkeys().get(1).getSpeciesDesignation());
    assertEquals(11, primateSanctuary.getAllMonkeys().get(0).getMonkeyAge());
    assertEquals(12, primateSanctuary.getAllMonkeys().get(1).getMonkeyAge());
    assertEquals(Size.MEDIUM, primateSanctuary.getAllMonkeys().get(0).getMonkeySize());
    assertEquals(Size.LARGE, primateSanctuary.getAllMonkeys().get(1).getMonkeySize());
  }

  /**
   * Unit test case for checking if the getAllMonkeys()
   * displays list of monkeys correctly.
   */
  @Test
  public void getAllMonkeysHoused() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    String expected = "Monkey ID: 1002, Name: Ben, SpecieDesignation: HOWLER, currentLocation: "
            + "ISOLATION, housingID: 516, age4, Favorite Food: INSECTS, weight: 35.0, sex: MALE, "
            + "sizeMEDIUM, medicallyHealthy: true\n"
            + "Monkey ID: 1001, Name: Mick, SpecieDesignation: HOWLER, currentLocation: ISOLATION, "
            + "housingID: 515, age12, Favorite Food: LEAVES, weight: 23.0, sex: FEMALE, sizeLARGE, "
            + "medicallyHealthy: true\n"
            + "Monkey ID: 1000, Name: Zeke, SpecieDesignation: HOWLER, currentLocation: ISOLATION,"
            + " housingID: 514, age11, Favorite Food: EGGS, weight: 20.0, sex: MALE, "
            + "sizeMEDIUM, medicallyHealthy: true";
    assertEquals(expected, primateSanctuary.getAllMonkeysHoused().trim());
  }

  /**
   * Unit test case for checking if the produceSign()
   * displays sign correctly for a given enclosure.
   */
  @Test
  public void produceSignTest() {
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0), true);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    String expected = "---> Monkey Name : Ben ,Sex : MALE ,Favorite Food : INSECTS";
    assertEquals(expected, primateSanctuary.produceSign(100).trim());
  }

  /**
   * Unit test case for checking if the updateMedicalHealthOfMonkey()
   * updates the medical status of the monkey correctly.
   */
  @Test
  public void updateMedicalHealthOfMonkeyTest() {
    primateSanctuary.addNewMonkey("Olive", Species.GUEREZA, 15, 60,
            FavoriteFood.SEEDS, Sex.FEMALE, 25);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0), false);
    assertEquals(false, primateSanctuary.getAllMonkeys().get(0).getMonkeyMedicalStatus());
  }

  /**
   * Unit test case for checking if the sendMonkeyToEnclosure()
   * sends the monkey to enclosure.
   */
  @Test
  public void sendMonkeyToEnclosure() {
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0), true);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(CurrentLocStatus.ENCLOSURE,
            primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  /**
   * Unit test case for checking if the sendMonkeyToEnclosure()
   * sends multiple monkeys to enclosure.
   */
  @Test
  public void sendMonkeyToEnclosureTwoSpecies() {
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35, FavoriteFood.INSECTS,
            Sex.MALE, 16);
    primateSanctuary.addNewMonkey("Benny", Species.DRILL, 4, 35, FavoriteFood.INSECTS,
            Sex.MALE, 5);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0), true);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertNotEquals(primateSanctuary.getAllMonkeys().get(0).getMonkeyHousingId(),
            primateSanctuary.getAllMonkeys().get(1).getMonkeyHousingId());
  }

  /**
   * Unit test case for checking if the getShoppingList()
   * produces shopping list accurately.
   */
  @Test
  public void getShoppingList() {
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    String expected = "TREESAP : 0 gr\n"
            + "INSECTS : 250 gr\n"
            + "LEAVES : 0 gr\n"
            + "EGGS : 0 gr\n"
            + "NUTS : 0 gr\n"
            + "SEEDS : 0 gr\n"
            + "FRUITS : 0 gr";
    assertEquals(expected, primateSanctuary.getShoppingList().trim());
  }

  /**
   * Unit test case for checking if the getShoppingList()
   * produces shopping list accurately when there ate multiple species.
   */
  @Test
  public void getShoppingListMultiplePrimates() {
    primateSanctuary.addNewMonkey("Ben", Species.HOWLER, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    primateSanctuary.addNewMonkey("Benny", Species.HOWLER, 4, 35,
            FavoriteFood.TREESAP, Sex.MALE, 7);
    primateSanctuary.addNewMonkey("Sel", Species.HOWLER, 4, 35,
            FavoriteFood.EGGS, Sex.MALE, 27);

    String expected = "TREESAP : 100 gr\n"
            + "INSECTS : 250 gr\n"
            + "LEAVES : 0 gr\n"
            + "EGGS : 500 gr\n"
            + "NUTS : 0 gr\n"
            + "SEEDS : 0 gr\n"
            + "FRUITS : 0 gr";
    assertEquals(expected, primateSanctuary.getShoppingList().trim());
  }

  /**
   * Unit test case for checking if the increaseIsolations()
   * throws IllegalArgumentException when neg parameter is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void increaseIsolationsNegTest() {
    primateSanctuary.increaseIsolations(-10);
  }

  /**
   * Unit test case for checking if the increaseEnclosures()
   * throws IllegalArgumentException when neg parameter is provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void increaseEnclosuresNegTest() {
    primateSanctuary.increaseEnclosures(-10);
  }

  /**
   * Unit test case for checking if the lookUpSpeciesTest()
   * produces list of monkeys belonging to particular specie
   * along with the location.
   */
  @Test
  public void lookUpSpeciesTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.TAMARIN, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    String expected = "Monkey of type : HOWLER and name Zeke is housed "
            + "in ISOLATION with house ID : 514";
    assertEquals(expected, primateSanctuary.lookUpSpecies(Species.HOWLER).trim());
  }

  /**
   * Unit test case for checking if the lookUpSpeciesTest()
   * produces list of monkeys belonging to particular specie
   * along with the location. (with multiple species).
   */
  @Test
  public void lookUpSpeciesMultiplePrimateTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("JAred", Species.HOWLER, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 5);
    primateSanctuary.addNewMonkey("Mick", Species.TAMARIN, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    String expected = "Monkey of type : HOWLER and name Zeke is housed "
            + "in ISOLATION with house ID : 514\n"
            + "Monkey of type : HOWLER and name JAred is housed in ISOLATION with house ID : 515";
    assertEquals(expected, primateSanctuary.lookUpSpecies(Species.HOWLER).trim());
  }

  /**
   * Unit test case for checking if the lookUpSpeciesTest()
   * throws exception when specie is not found.
   */
  @Test(expected = IllegalArgumentException.class)
  public void lookUpSpeciesNotPresentTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.TAMARIN, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    primateSanctuary.lookUpSpecies(Species.DRILL);
  }

}
