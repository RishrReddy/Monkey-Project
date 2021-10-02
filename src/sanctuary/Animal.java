package sanctuary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * Animal class implements the methods in interface JungleSanctuary.
 * The functionality to be performed on monkeys in sanctuary are implemented here.
 */
public class Animal implements JungleSanctuary {
  private ArrayList<Monkey> allMonkeyList;
  public static Housing housing = new HousingUnits(14, 20);

  /**
   * Default Constructor for Animal class
   * where list of monkeys is initiated.
   */
  public Animal() {
    if (this.allMonkeyList == null) {
      this.allMonkeyList = new ArrayList<>();
    }
  }

  /**
   * Creates a new monkey object and brings in it to the sanctuary.
   * Checks if there is space available in isolation and transfers monkey to isolation.
   * Updates the current location of monkey from UNASSIGNED to ISOLATION.
   *
   * @param name               Name of the Monkey.
   * @param speciesDesignation Type of the Monkey.
   * @param age                Age of the Monkey.
   * @param weight             Weight of the Monkey.
   * @param favFood            Favorite food of the Monkey.
   * @param sex                Gender of the Monkey.
   * @param size               Size of the monkey.
   */
  public void addNewMonkey(String name, Species speciesDesignation, int age, double weight,
                           FavoriteFood favFood, Sex sex, double size) {
    if (age < 0 || weight < 0 || size < 0) {
      throw new IllegalArgumentException("Negative Values are not accepted as attributes");
    }
    if (speciesDesignation == null || favFood == null || sex == null) {
      throw new IllegalArgumentException("null values are not allowed in "
              + "the monkey specifications ");
    }
    Monkey monkey = new Monkey(name, speciesDesignation, age, weight, favFood, sex, size);
    addNewMonkeyToSanctuary(monkey);
    if (housing.checkAvailabilityInIsolation()) {
      housing.sendMonkeyToIsolation(monkey);
      monkey.updateMonkeyLocation(CurrentLocStatus.ISOLATION);
    } else {
      System.out.println("Isolation Units are to full capacity. Please Wait");
    }
    //this.houseUnit = housing;
  }

  /**
   * Add a new monkey to glabal list of monkeys already present in Sanctuary.
   *
   * @param monkey Monkey to be added to the list of monkeys.
   */
  private void addNewMonkeyToSanctuary(Monkey monkey) {
    allMonkeyList.add(monkey);
  }

  /**
   * Provides the list of all monkeys present in Sanctuary currently.
   *
   * @return List of all monkeys housed in Sanctuary.
   */
  public List<Monkey> getAllMonkeys() {
    List<Monkey> monkey = new ArrayList<>();
    for (Monkey m : this.allMonkeyList) {
      if (m.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)
              || m.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)) {
        monkey.add(m);
      }
    }
    return monkey;
  }

  /**
   * Produces list of all the species entering the
   * Sanctuary.
   *
   * @return List of all Monkeys currently in Sanctuary.
   */
  @Override
  public String getAllMonkeysHoused() {
    List<Monkey> monkeys = sortMonkeyListAlphabetically();
    StringBuilder sb = new StringBuilder();
    for (Monkey m : monkeys) {
      if (m.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)
              || m.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        sb.append("Monkey ID: " + m.getMonkeyID() + ", Name: " + m.getMonkeyName()
                + ", SpecieDesignation: " + m.getSpeciesDesignation()
                + ", currentLocation: " + m.getMonkeyLocation() + ", housingID: "
                + m.getMonkeyHousingID() + ", age" + m.getMonkeyAge() + ", Favorite Food: "
                + m.getMonkeyFavFood() + ", weight: " + m.getMonkeyWeight() + ", sex: "
                + m.getMonkeySex() + ", size" + m.getMonkeySize()
                + ", medicallyHealthy: " + m.getMonkeyMedicalStatus() + "\n");
      }
    }
    return sb.toString();
  }

  /**
   * Produces sign for a particular enclosure
   * providing details like Name, Sex and Favorite Food
   * of monkey stored in that particular Enclosure.
   *
   * @param enclosureId enclosure ID of the enclosure for which the
   *                    sign has to be produced.
   * @return String of details like Name,sex,and favorite food of the
   * monkeys housed in a particular enclosure.
  */
  @Override
  public String produceSign(int enclosureId) {
    ArrayList<Monkey> monkeyInEnclosure = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (Monkey mon : this.getAllMonkeys()) {
      if (mon.getMonkeyHousingID() == (enclosureId)
              && mon.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        monkeyInEnclosure.add(mon);
      }
    }
    for (Monkey mon : monkeyInEnclosure) {
      sb.append(" ---> Monkey Name : " + mon.getMonkeyName() + " ,Sex : " + mon.getMonkeySex()
              + " ,Favorite Food : " + mon.getMonkeyFavFood() + "\n");
    }
    return sb.toString();
  }

  /**
   * Updates the medical status of a monkey to
   * healthy or unhealthy after the medical
   * checkup in isolation.
   *
   * @param monkey    Monkey whose medical health status is to be updated.
   * @param isHealthy Takes value true is monkey is healthy, else its false.
   */
  @Override
  public void updateMedicalHealthOfMonkey(Monkey monkey, boolean isHealthy) {
    if (monkey.getMonkeyHousingID() > 0) {
      monkey.updateMonkeyHealthStatus(isHealthy);
      if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        housing.sendMonkeyToIsolation(monkey);
        monkey.updateMonkeyLocation(CurrentLocStatus.ISOLATION);
      }
    }
  }

  /**
   * Sends a particular monkey to enclosure after being housed in
   * an isolation and medical check is finished.
   *
   * @param monkey Monkey to be sent to enclosure.
   * @throws RuntimeException if unhealthy monkey is transferred to enclosure
   */
  @Override
  public void sendMonkeyToEnclosure(Monkey monkey) throws EnclosureTransferException {
    if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)
            || !monkey.getMonkeyMedicalStatus()) {
      if (monkey.getMonkeyMedicalStatus() == true) {
        boolean sentMonkeytoEnclosure = this.housing.checkAvailabilityInEnclosures(monkey);
      }
    } else {
      throw new EnclosureTransferException(monkey.getMonkeyName()
              + "is not eligible for being in enclosure!!");
    }
  }

  /**
   * Produces the shopping list with each of the favorite
   * food time of the monkeys housed along with the quantity
   * required.
   *
   * @return List of food item and quantity to be shopped for
   * monkeys in sanctuary.
  */
  @Override
  public String getShoppingList() {
    HashMap<FavoriteFood, Integer> shoppingList = obtainShoppingList();
    StringBuilder sb = new StringBuilder();
    for (HashMap.Entry<FavoriteFood, Integer> item : shoppingList.entrySet()) {
      sb.append(item.getKey() + " : " + item.getValue() + " gr\n");
    }
    return sb.toString();
  }


  /**
   * Helper method obtain the shopping list
   * based on the monkeys currently housed in Sanctuary and their sizes.
   *
   * @return Map of Favorite food and Quantity to be bought.
   */
  private HashMap<FavoriteFood, Integer> obtainShoppingList() {
    List<Monkey> allMonkeys = getAllMonkeys();
    HashMap<FavoriteFood, Integer> shoppingList = new HashMap<>();
    for (FavoriteFood favFood : FavoriteFood.values()) {
      shoppingList.put(favFood, 0);
    }
    for (Monkey monkey : allMonkeys) {
      FavoriteFood favfood = monkey.getMonkeyFavFood();
      Size monkeySize = monkey.getMonkeySize();
      int quantity = 0;
      if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)
              || monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        quantity = monkeySize.equals(Size.SMALL) ? 100 : monkeySize.equals(Size.MEDIUM) ? 250 : 500;
      }
      shoppingList.put(favfood, shoppingList.get(favfood) + quantity);
    }
    return shoppingList;
  }

  /**
   * Increases the number of current isolation units
   * by the number of isolation units provided by the
   * admin.
   *
   * @param isolations Number of isolation units to be increased.
   */
  @Override
  public void increaseIsolations(int isolations) {
    if (isolations < 0) {
      throw new IllegalArgumentException("Negative values not allowed");
    }
    housing.increaseIsolations(10);
  }

  /**
   * Increases the number of current enclosure units
   * by the number of enclosure units provided by the
   * admin.
   *
   * @param enclosures Number of isolation units to be increased.
   */
  @Override
  public void increaseEnclosures(int enclosures) {
    if (enclosures < 0) {
      throw new IllegalArgumentException("Negative values not allowed");
    }
    housing.increaseEnclosures(enclosures);
  }

  /**
   * Produces the list of monkeys along with their location
   * belonging to a particular species provided by the user.
   *
   * @param specieDesignation type of Specie to be searched in the
   *                          sanctuary.
   * @return List of monkeys with specie designation is same as the
   * one provided by user. The list also provides the
  */
  @Override
  public String lookUpSpecies(Species specieDesignation) {
    boolean foundSpecie = false;
    StringBuilder sb = new StringBuilder();
    for (Monkey monkey : allMonkeyList) {
      if (specieDesignation.equals(monkey.getSpeciesDesignation())) {
        if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)
                || monkey.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)) {
          sb.append("Monkey of type : " + specieDesignation + " and name " + monkey.getMonkeyName()
                  + " is housed in " + monkey.getMonkeyLocation() + " with house ID : "
                  + monkey.getMonkeyHousingID() + "\n");
          foundSpecie = true;
        }
      }
    }
    if (!foundSpecie) {
      sb.append("None of the monkey of type " + specieDesignation + " are housed in the sanctuary");
    }
    return sb.toString();
  }


  /**
   * Helper method to sort the Monkey objects
   * in alphabetical order based on name.
   *
   * @return List of sorted Monkeys based on the monkey name.
   */
  private List<Monkey> sortMonkeyListAlphabetically() {
    List<Monkey> monkeyList = this.getAllMonkeys();
    monkeyList.sort(MonkeyNameComparator);
    return monkeyList;
  }

  /**
   * Helper method for implementing comparison between 2 monkey objects.
   *
   * @return Result of comparison between 2 monkey names.
   */
  private static final Comparator<Monkey> MonkeyNameComparator = (m1, m2) -> {
    String monkeyName1 = m1.getMonkeyName().toUpperCase();
    String monkeyName2 = m2.getMonkeyName().toUpperCase();
    return monkeyName1.compareTo(monkeyName2);
  };

  /**
  * Produces the list of monkeys filtered based on specie type
  * and ordered alphabetically.
  *
  * @return List of monkeys for every
  * specie designation.
  */
  public String getSpeciesList() {
    boolean foundSpecie = false;
    StringBuilder sb = new StringBuilder();
    for (Species s : Species.values()) {
      for (Monkey monkey : allMonkeyList) {
        if (s.equals(monkey.getSpeciesDesignation())) {
          if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)
                  || monkey.getMonkeyLocation().equals(CurrentLocStatus.ISOLATION)) {
            sb.append("Monkey of type : " + s + " and name " + monkey.getMonkeyName()
                    + " is housed in " + monkey.getMonkeyLocation() + " with house ID : "
                    + monkey.getMonkeyHousingID() + "\n");
          }
        }
      }
    }
    return sb.toString();
  }

  /**
   * This method compares the equality of the current object
   * with the object of same type.
   *
   * @return true/false depending on equality of the object.
   */
  @Override
  public boolean equals(Object ob) {
    // Fast path for pointer equality:
    if (this == ob) { // backward compatibility with default equals
      return true;
    }
    // If o isn't the right class then it can't be equal:
    if (!(ob instanceof JungleSanctuary)) {
      return false;
    }
    JungleSanctuary that = (JungleSanctuary) ob;
    return this.getAllMonkeys().equals(that.getAllMonkeys());
  }

  /**
   * This method id to generate hash code value for the object.
   *
   * @return hash value.
   */
  @Override
  public int hashCode() {
    return this.getAllMonkeys().hashCode();
  }

}
