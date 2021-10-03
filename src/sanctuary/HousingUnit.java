package sanctuary;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Housing Unit class implements Housing interface.
 * It deals with all the functions related to isolations and enclosures.
 */
public class HousingUnit implements Housing {
  private static int totalIsolationUnits;
  private static int totalEnclosureUnits;
  private static ArrayList<Integer> availableIsolationIDList;
  private static ArrayList<Enclosure> availableEnclosureList;


  /**
   * Default Constructor for HousingUnits Class.
   */
  public HousingUnit() {
    //Default construct doesn't implement anything,
    //only kept since there are subclasses.
  }


  /**
   * Constructor to create new Housing unit
   * with certain isolation and enclosure units.
   *
   * @param isolationUnits Number of initials isolation units.
   * @param enclosures     Number of initial enclosure units.
   */
  public HousingUnit(int isolationUnits, int enclosures) {
    totalEnclosureUnits = enclosures;
    totalIsolationUnits = isolationUnits;
    availableIsolationIDList = new ArrayList<>();
    for (int i = 0; i < totalIsolationUnits; i++) {
      Isolation isolationNew = new Isolation();
      availableIsolationIDList.add(isolationNew.getIsolationId());
    }
  }

  @Override
  public void increaseIsolations(int newIsolations) {
    if (newIsolations < 0) {
      throw new IllegalArgumentException("Negative Values not allowed");
    }
    totalIsolationUnits = totalIsolationUnits + newIsolations;
    for (int i = 0; i < newIsolations; i++) {
      if (availableIsolationIDList == null) {
        availableIsolationIDList = new ArrayList<>();
      }
      Isolation isolationNew = new Isolation();
      availableIsolationIDList.add(isolationNew.getIsolationId());
    }
    System.out.println("Isolations successfully increased to " + totalIsolationUnits);
    System.out.println("Currently there are " + availableIsolationIDList.size()
            + " isolations available.");
  }

  @Override
  public void increaseEnclosures(int newEnclosures) {
    if (newEnclosures < 0) {
      throw new IllegalArgumentException("Negative Values not allowed");
    }
    totalEnclosureUnits = totalEnclosureUnits + newEnclosures;
    System.out.println("Enclosures successfully increased to " + totalEnclosureUnits);
  }

  @Override
  public boolean checkAvailabilityInIsolation() {
    return availableIsolationIDList.size() > 0;
  }

  @Override
  public void sendMonkeyToIsolation(Monkey monkey) {

    if (availableIsolationIDList.size() > 0) {
      if (monkey.getMonkeyLocation().equals(CurrentLocStatus.ENCLOSURE)) {
        int enclosureId = monkey.getMonkeyHousingId();
        for (Iterator<Enclosure> iterator = availableEnclosureList.iterator();
             iterator.hasNext(); ) {
          Enclosure enclosure = iterator.next();
          if (enclosureId == enclosure.getEnclosureid()) {
            enclosure.updateAvailableArea(getSizeRequired(monkey), false);
            if (enclosure.getAvailableArea() == enclosure.getTotalArea()) {
              System.out.println("Enclosure " + enclosureId
                      + " is completely empty hence deleting the enclosure");
              iterator.remove();
            }
          }
        }
      }
      monkey.updateMonkeyHouseId((availableIsolationIDList.get(0)));
    }
    availableIsolationIDList.remove(0);
  }

  @Override
  public boolean checkAvailabilityInEnclosures(Monkey monkey) {
    if (availableEnclosureList == null) { //When there are no enclosures, creating a new enclosures.
      availableEnclosureList = new ArrayList<>();
      Enclosure enclosure = new Enclosure(30, monkey.getSpeciesDesignation());
      if (enclosure.getAvailableArea() >= getSizeRequired(monkey)) {
        sendMonkeyToEnclosure(enclosure, monkey);
        return true;
      }
    } else if (totalEnclosureUnits - availableEnclosureList.size() > 0) {
      //when there are enclosures already available
      for (Enclosure enclosure : availableEnclosureList) {
        //iterating over the available enclosures to find one with same species.
        if (enclosure.getSpecieHoused().equals(monkey.getSpeciesDesignation())) {
          if (enclosure.getAvailableArea() >= getSizeRequired(monkey)) {
            sendMonkeyToEnclosure(enclosure, monkey);
            return true;
          }
        }
      } //when same species enclosure was not found and there is space to create new enclosure.
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
    addToAvailableIsolationList(monkey.getMonkeyHousingId());
    monkey.updateMonkeyHouseId(enclosure.getEnclosureid());
    monkey.updateMonkeyLocation(CurrentLocStatus.ENCLOSURE);
    availableEnclosureList.add(enclosure);
    enclosure.updateAvailableArea(getSizeRequired(monkey), true);
  }


  /**
   * Helper method to add a particular housing ID to available isolation list,
   * when monkey is moved to isolations.
   *
   * @param monkeyHousingId - housing ID of the occupied isolation unit.
   */
  private void addToAvailableIsolationList(int monkeyHousingId) {
    if (availableIsolationIDList == null) {
      availableIsolationIDList = new ArrayList<>();
    }
    availableIsolationIDList.add(monkeyHousingId);
  }

  /**
   * Helper method to calculate area required by a monkey,
   * based on its size.
   *
   * @param monkey - Monkey object
   * @return area required by a monkey in enclosure.
   */
  private int getSizeRequired(Monkey monkey) {
    return monkey.getMonkeySize() == Size.SMALL ? 1 : monkey.getMonkeySize()
            == Size.MEDIUM ? 5 : 10;
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
    monkey.updateMonkeyHouseId(0);
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