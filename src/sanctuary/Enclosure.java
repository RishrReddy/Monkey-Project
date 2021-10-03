package sanctuary;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Enclosure class represents an enclosure in the Jungle Sanctuary and
 * has attributes like
 * enclosureid, totalArea, specieHoused, availableArea.
 */
final class Enclosure extends HousingUnit {

  private final int enclosureid;
  private final double totalArea;
  private final Species specieHoused;
  private double availableArea;
  private static final AtomicInteger enclosureIDGenerator = new AtomicInteger(100);

  /**
   * Constructor for creating an object of Enclosure class.
   *
   * @param totalArea    Total Area of the enclosure.
   * @param specieHoused specie that will be present in Enclosure.
   */
  public Enclosure(double totalArea, Species specieHoused) {
    if (totalArea < 0) {
      throw new IllegalArgumentException("Total Area of enclosure can't be negative");
    }
    if (specieHoused == null) {
      throw new IllegalArgumentException("specie Housed can't be negative");
    }
    this.enclosureid = enclosureIDGenerator.getAndIncrement();
    this.totalArea = totalArea;
    this.specieHoused = specieHoused;
    this.availableArea = totalArea;
  }

  /**
   * Getter method to return enclosure's id.
   *
   * @return enclsoure ID.
   */
  public int getEnclosureid() {
    return this.enclosureid;
  }

  /**
   * Getter method to return specie type housed in enclosure.
   *
   * @return Specie housed in enclosure.
   */
  public Species getSpecieHoused() {
    return this.specieHoused;
  }

  /**
   * Getter method to return available area in the enclosure.
   *
   * @return Available area in the enclosure.
   */
  public double getAvailableArea() {
    return this.availableArea;
  }

  /**
   * Getter method to return total area of the enclosure.
   *
   * @return Total area of the enclosure.
   */
  public double getTotalArea() {
    return this.totalArea;
  }

  /**
   * Update the area after a monkey moves in and out of the enclosure.
   *
   * @param areaToChange Area to be added or removed
   * @param toReduce     if true area will be reduces, if false area will be added.
   */
  public void updateAvailableArea(int areaToChange, boolean toReduce) {
    this.availableArea = toReduce ? this.availableArea - areaToChange :
            this.availableArea + areaToChange;
  }

}
