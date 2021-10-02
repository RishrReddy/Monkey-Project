package sanctuary;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Housing Unit class implements Housing interface.
 * It deals with all the functions related to isolations and enclosures.
 */
public class HousingUnits implements Housing {
  private static int totalIsolationUnits;
  private static int totalEnclosureUnits;
  private ArrayList<Integer> availableIsolationIDList;
  private ArrayList<Enclosure> availableEnclosureList;


  /**
   * Default Constructor for HousingUnits Class.
   */
  public HousingUnits() {

  }


  /**
   * Constructor to create new Housing unit
   * with certain isolation and enclosure units.
   *
   * @param isolationUnits Number of initials isolation units.
   * @param enclosures     Number of initial enclosure units.
   */
  public HousingUnits(int isolationUnits, int enclosures) {
    this.totalEnclosureUnits = enclosures;
    this.totalIsolationUnits = isolationUnits;
    if (this.availableIsolationIDList == null) {
      this.availableIsolationIDList = new ArrayList<Integer>();
    }
    for (int i = 0; i < totalIsolationUnits; i++) {
      Isolation isolationNew = new Isolation();
      this.availableIsolationIDList.add(isolationNew.getIsolationID());
    }
  }

  @Override
  public void increaseIsolations(int newIsolations) {
    if (newIsolations < 0) {
      throw new IllegalArgumentException("Negative Values not allowed");
    }
    this.totalIsolationUnits = this.totalIsolationUnits + newIsolations;
    for (int i = 0; i < newIsolations; i++) {
      if (this.availableIsolationIDList == null) {
        this.availableIsolationIDList = new ArrayList<>();
      }
      Isolation isolationNew = new Isolation();
      this.availableIsolationIDList.add(isolationNew.getIsolationID());
    }
    System.out.println("Isolations successfully increased to " + this.totalIsolationUnits);
    System.out.println("Currently there are " + this.availableIsolationIDList.size() + " isolations available.");
  }

  @Override
  public void increaseEnclosures(int newEnclosures) {
    if (newEnclosures < 0) {
      throw new IllegalArgumentException("Negative Values not allowed");
    }
    this.totalEnclosureUnits = this.totalEnclosureUnits + newEnclosures;
    System.out.println("Enclosures successfully increased to " + this.totalEnclosureUnits);
  }

  @Override
  public boolean checkAvailabilityInIsolation() {
    return this.availableIsolationIDList.size() > 0 ? true : false;
  }

  @Override
  public void sendMonkeyToIsolation(Monkey monkey) {

    if (this.availableIsolationIDList.size() > 0) {
      if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        int enclosureID = monkey.getMonkeyHousingID();
        for (Iterator<Enclosure> iterator = availableEnclosureList.iterator(); iterator.hasNext(); ) {
          Enclosure enclosure = iterator.next();
          if (enclosureID == enclosure.getEnclosureID()) {
            enclosure.updateAvailableArea(getSizeRequired(monkey), false);
            if (enclosure.getAvailableArea() == enclosure.getTotalArea()) {
              System.out.println("Enclosure " + enclosureID + " is completely empty hence deleting the enclosure");
              iterator.remove();
            }
          }
        }
      }
      monkey.updateMonkeyHouseID(this.availableIsolationIDList.get(0));
    }
    this.availableIsolationIDList.remove(0);
  }

  @Override
  public boolean checkAvailabilityInEnclosures(Monkey monkey) {
    if (this.availableEnclosureList == null) { //When there are no enclosures, creating a new enclosures.
      this.availableEnclosureList = new ArrayList<>();
      Enclosure enclosure = new Enclosure(30, monkey.getSpeciesDesignation());
      if (enclosure.getAvailableArea() >= getSizeRequired(monkey)) {
        sendMonkeyToEnclosure(enclosure, monkey);
        return true;
      }
    } else if (totalEnclosureUnits - availableEnclosureList.size() > 0) { //when there are enclosures already available
      for (Enclosure enclosure : availableEnclosureList) { //iterating over the available enclosures to find one with same species.
        if (enclosure.getSpecieHoused().equals(monkey.getSpeciesDesignation())) {
          if (enclosure.getAvailableArea() >= getSizeRequired(monkey)) {
            sendMonkeyToEnclosure(enclosure, monkey);
            return true;
          }
        }
      }//when same species enclosure was not found and there is space to create new enclosure.
      Enclosure enclosure = new Enclosure(30, monkey.getSpeciesDesignation());
      if (enclosure.getAvailableArea() >= getSizeRequired(monkey)) {
        sendMonkeyToEnclosure(enclosure, monkey);
        return true;
      }
    } else {
      transferMonkeyToAnotherSanctuary(monkey);
      return false;
    }
    return false;
  }

  @Override
  public void sendMonkeyToEnclosure(Enclosure enclosure, Monkey monkey) {
    addToAvailableIsolationList(monkey.getMonkeyHousingID());
    monkey.updateMonkeyHouseID(enclosure.getEnclosureID());
    monkey.updateMonkeyLocation(CurrentLocStatus.ENCLOSURE);
    this.availableEnclosureList.add(enclosure);
    System.out.println("Enclosure ID : " + enclosure.getEnclosureID());
    System.out.println("Available Space: " + enclosure.getAvailableArea());
    System.out.println("monkey Space: " + monkey.getMonkeyName());
    enclosure.updateAvailableArea(getSizeRequired(monkey), true);
    System.out.println("Enclosure ID : " + enclosure.getEnclosureID());
    System.out.println("Available Space: " + enclosure.getAvailableArea());
  }


  /**
   * Helper method to add a particular housing ID to available isolation list,
   * when monkey is moved to isolations.
   *
   * @param monkeyHousingID - housing ID of the occupied isolation unit.
   */
  private void addToAvailableIsolationList(int monkeyHousingID) {
    if (this.availableIsolationIDList == null) {
      this.availableIsolationIDList = new ArrayList<>();
    }
    availableIsolationIDList.add(monkeyHousingID);
  }

  /**
   * Helper method to calculate area required by a monkey,
   * based on its size.
   *
   * @param monkey - Monkey object
   * @return area required by a monkey in enclosure.
   */
  private int getSizeRequired(Monkey monkey) {
    return monkey.getMonkeySize() == Size.SMALL ? 1 : monkey.getMonkeySize() == Size.MEDIUM ? 5 : 10;
  }

  /**
   * Helper method to calculate area required by a monkey,
   * based on its size.
   *
   * @param monkey - Monkey object.
   */
  private void transferMonkeyToAnotherSanctuary(Monkey monkey) {
    System.out.println("Since there is no space in enclosures and the monkey is healthy,"
            + "sending monkey to other Sanctuary");
    monkey.updateMonkeyLocation(CurrentLocStatus.TRANSFER);
    monkey.updateMonkeyHouseID(0);
  }

  @Override
  public int getTotalEnclosureUnits() {
    return totalEnclosureUnits;
  }

  @Override
  public int getTotalIsolationUnits() {
    return totalIsolationUnits;
  }
}