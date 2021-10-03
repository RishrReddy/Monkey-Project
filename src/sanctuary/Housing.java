package sanctuary;

/**
 * Housing interface represents the housing units present in Jungle Sanctuary.
 * Jungle Sanctuary consists of multiple isolations and enclosures.
 * Different monkeys are assigned housing IDs and sometimes are transferred based
 * on multiple factors.
 */
public interface Housing {
  /**
   * Increases the number of current isolation units
   * by the number of isolation units provided by the
   * admin.
   *
   * @param newIsolations Number of isolation units to be increased.
   */
  void increaseIsolations(int newIsolations);

  /**
   * Increases the number of current enclosures units
   * by the number of enclosures units provided by the
   * admin.
   *
   * @param newEnclosures Number of enclosure units to be increased.
   */
  void increaseEnclosures(int newEnclosures);

  /**
   * Checks if there is space in isolation for incoming
   * monkey.
   *
   * @return true is place is available for an incoming monkey
   *         else will return false
   */
  boolean checkAvailabilityInIsolation();

  /**
   * Checks if there is space in enclosure units for incoming
   * monkey. If there is space it also sends monkey to enclosure.
   *
   * @param monkey incoming monkey object
   * @return true when monkey is successfully sent to one of the
   *         enclosure.
   */
  boolean checkAvailabilityInEnclosures(Monkey monkey);

  /**
   * Transfers monkey to isolation unit.
   *
   * @param monkey incoming monkey object.
   */
  void sendMonkeyToIsolation(Monkey monkey);

  /**
   * Transfers monkey to enclosure unit.
   *
   * @param enclosure Enclosure where monkey is to be sent.
   * @param monkey    incoming monkey object.
   */
  void sendMonkeyToEnclosure(Enclosure enclosure, Monkey monkey);

  /**
   * Returns the total number of isolation Units.
   *
   * @return total number of isolation Units
   */
  int getTotalIsolationUnits();

  /**
   * Returns the total number of enclosure Units.
   *
   * @return total number of enclosure Units
   */
  int getTotalEnclosureUnits();
}
