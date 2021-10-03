package sanctuary;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Monkey class represents a single monkey with multiple attributes
 * like name,age, weight, type of species, gender and housing details.
 */
public final class Monkey extends Animal {
  private final int id;
  private final String name;
  private final Species speciesDesignation;
  private final int age;
  private final double weight;
  private final FavoriteFood favFood;
  private final Sex sex;
  private boolean medicallyHealthy;
  private final double size;
  private CurrentLocStatus location;
  private int housingId;
  private static final AtomicInteger monkeyIDGenerator = new AtomicInteger(1000);

  /**
   * Constructor to initialize the attributes of monkey objects when its first created.
   *
   * @param name               Name of the Monkey.
   * @param speciesDesignation Type of the Monkey.
   * @param age                Age of the Monkey.
   * @param weight             Weight of the Monkey.
   * @param favFood            Favorite food of the Monkey.
   * @param sex                Gender of the Monkey.
   * @param size               Size of the monkey.
   * @throws IllegalArgumentException when age/weight/size is less than
   *                                  0 or the enum values provided are null.
   */
  public Monkey(String name, Species speciesDesignation, int age, double weight,
                FavoriteFood favFood, Sex sex, double size) {
    if (age < 0 || weight < 0 || size < 0) {
      throw new IllegalArgumentException("Negative Values are not accepted as attributes");
    }
    if (speciesDesignation == null || favFood == null || sex == null) {
      throw new IllegalArgumentException("null values are not allowed in "
              + "the monkey specifications ");
    }
    if (!containsFavFood(favFood) || !containsSex(sex)
            || !containsSpecieDestination(speciesDesignation)) {
      throw new IllegalArgumentException("Arguments are invalid");
    }

    this.id = monkeyIDGenerator.getAndIncrement();
    this.name = name;
    this.speciesDesignation = speciesDesignation;
    this.age = age;
    this.weight = weight;
    this.favFood = favFood;
    this.sex = sex;
    this.medicallyHealthy = true;
    this.size = size;
    this.location = CurrentLocStatus.UNASSIGNED;
    this.housingId = 0;
  }

  /**
   * Getter method to return monkey's name.
   *
   * @return Name of the monkey.
   */
  public String getMonkeyName() {
    return this.name;
  }

  /**
   * Getter method to return monkey's type.
   *
   * @return Type of Specie monkey belongs to.
   */
  public Species getSpeciesDesignation() {
    return this.speciesDesignation;
  }

  /**
   * Getter method to return monkey's age.
   *
   * @return Age of the monkey.
   */
  public int getMonkeyAge() {
    return this.age;
  }

  /**
   * Getter method to return monkey's weight.
   *
   * @return weight of the monkey.
   */
  public double getMonkeyWeight() {
    return this.weight;
  }

  /**
   * Getter method to return monkey's favorite food.
   *
   * @return Name of the monkey.
   */
  public FavoriteFood getMonkeyFavFood() {
    return this.favFood;
  }

  /**
   * Getter method to return monkey's gender.
   *
   * @return Gender of the monkey.
   */
  public Sex getMonkeySex() {
    return this.sex;
  }

  /**
   * Getter method to return monkey's size.
   *
   * @return Size of the monkey (SMALL.MEDIUM,LARGE).
   */
  public Size getMonkeySize() {
    Size monkeySize;
    monkeySize = this.size < 10 ? Size.SMALL : (this.size >= 10 && this.size < 20)
            ? Size.MEDIUM : Size.LARGE;
    return monkeySize;
  }

  /**
   * Getter method to return monkey's medical status.
   *
   * @return Meidcal status of the monkey
   */
  public boolean getMonkeyMedicalStatus() {
    return this.medicallyHealthy;
  }

  /**
   * Getter method to return monkey's ID.
   *
   * @return ID of the monkey
   */
  public int getMonkeyId() {
    return id;
  }

  /**
   * Getter method to return monkey's current housing ID.
   *
   * @return Housing ID where monkey is currently held.
   */
  public int getMonkeyHousingId() {
    return this.housingId;
  }

  /**
   * Getter method to return monkey's favorite food.
   *
   * @return Name of the monkey
   */
  public CurrentLocStatus getMonkeyLocation() {
    return this.location;
  }

  /**
   * Method to update the location of Monkey when it is transferred from one place to another.
   *
   * @param location Location to be updated.
   */
  protected void updateMonkeyLocation(CurrentLocStatus location) {
    if ((this.location.equals(CurrentLocStatus.UNASSIGNED))
            && location.equals(CurrentLocStatus.ENCLOSURE)) {
      throw new IllegalStateException("Cant send monkey direct from UNASSIGNED TO ENCLOSURE");
    }
    this.location = location;
  }

  /**
   * Method to update the location of Monkey when it is assigned to isolation/enclosure.
   *
   * @param houseId housingId to be updated
   */
  protected void updateMonkeyHouseId(int houseId) {
    if (houseId < 0) {
      throw new IllegalArgumentException("houseID can't be negative");
    }
    this.housingId = houseId;
  }

  /**
   * Method to update the health status of Monkey as healthy or unhealthy.
   *
   * @param isHealthy the medical status of the monkey
   */
  protected void updateMonkeyHealthStatus(boolean isHealthy) {
    this.medicallyHealthy = isHealthy;
  }

}
