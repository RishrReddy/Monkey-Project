package sanctuary;

import java.util.List;

/**
 * JungleSanctuary represents a Sanctuary which houses multiple
 * species and provides them shelter and medical support.
 */
public interface JungleSanctuary {
  /**
   * Produces list of all the species entering the
   * Sanctuary.
   *
   * @return List of all Monkeys currently in Sanctuary.
   */
  List<Monkey> getAllMonkeys();

  /**
   * Welcomes a new monkey with the specifications provided
   * to the Sanctuary.
   *
   * @param name               Name of the Monkey.
   * @param speciesDesignation Type of the Monkey.
   * @param age                Age of the Monkey.
   * @param weight             Weight of the Monkey.
   * @param favFood            Favorite food of the Monkey.
   * @param sex                Gender of the Monkey.
   * @param size               Size of the monkey.
   */
  void addNewMonkey(String name, Species speciesDesignation, int age, double weight,
                    FavoriteFood favFood, Sex sex, double size);

  /**
   * Produces the list of monkeys housed in Sanctuary either in
   * isolation or enclosure.
   *
   * @return All the monkeys with their current location in Sanctuary.
   */
  String getAllMonkeysHoused();

  /**
   * Produces sign for a particular enclosure
   * providing details like Name, Sex and Favorite Food
   * of monkey stored in that particular Enclosure.
   *
   * @param enclosureId enclosure ID of the enclosure for which the
   *                    sign has to be produced.
   * @return String of details like Name,sex,and favorite food of the
   *         monkeys housed in a particular enclosure.
   */
  String produceSign(int enclosureId);

  /**
   * Updates the medical status of a monkey to
   * healthy or unhealthy after the medical
   * checkup in isolation.
   *
   * @param monkey    Monkey whose medical health status is to be updated.
   * @param isHealthy Takes value true is monkey is healthy, else its false.
   */
  void updateMedicalHealthOfMonkey(Monkey monkey, boolean isHealthy);

  /**
   * Sends a particular monkey to enclosure after being housed in
   * an isolation and medical check is finished.
   *
   * @param monkey Monkey to be sent to enclosure.
   * @throws IllegalStateException if unhealthy monkey is transferred to enclosure
   */
  void sendMonkeyToEnclosure(Monkey monkey);

  /**
   * Produces the shopping list with each of the favorite
   * food time of the monkeys housed along with the quantity
   * required.
   *
   * @return List of food item and quantity to be shopped for
   *         monkeys in sanctuary.
   */
  String getShoppingList();

  /**
   * Increases the number of current isolation units
   * by the number of isolation units provided by the
   * admin.
   *
   * @param isolations Number of isolation units to be increased.
   */
  void increaseIsolations(int isolations);

  /**
   * Increases the number of current enclosure units
   * by the number of enclosure units provided by the
   * admin.
   *
   * @param enclosures Number of isolation units to be increased.
   */
  void increaseEnclosures(int enclosures);

  /**
   * Produces the list of monkeys along with their location
   * belonging to a particular species provided by the user.
   *
   * @param specieDesignation type of Specie to be searched in the
   *                          sanctuary.
   * @return List of monkeys with specie designation is same as the
   *         one provided by user.
   */
  String lookUpSpecies(Species specieDesignation);

  /**
   * Produces the list of monkeys filtered based on specie type
   * and ordered alphabetically.
   *
   * @return List of monkeys for every
   *         specie designation.
   */
  String getSpeciesList();
}
