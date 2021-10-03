package driver;

import java.util.List;
import sanctuary.Animal;
import sanctuary.FavoriteFood;
import sanctuary.JungleSanctuary;
import sanctuary.Monkey;
import sanctuary.Sex;
import sanctuary.Species;

/**
 * Driver class to implement the functionality of the Jungle Sanctuary Project
 * with default data.
 */
public class SanctuaryDriverClass {

  /**
   * Main method to run the program with sample data.
   *
   * @param args argument for main method.
   */
  public static void main(String[] args) {
    JungleSanctuary primateSanctuary = new Animal( 15,20);
    // Assuming there are 11 incoming monkeys
    primateSanctuary.addNewMonkey("Paula", Species.HOWLER, 10, 20,
            FavoriteFood.EGGS, Sex.FEMALE, 5);
    primateSanctuary.addNewMonkey("Zen", Species.SPIDER, 7, 20,
            FavoriteFood.NUTS, Sex.FEMALE, 5);
    primateSanctuary.addNewMonkey("Alice", Species.SAKI, 8, 34,
            FavoriteFood.FRUITS, Sex.MALE, 11);
    primateSanctuary.addNewMonkey("Clara", Species.HOWLER, 18, 10,
            FavoriteFood.TREESAP, Sex.FEMALE, 30);
    primateSanctuary.addNewMonkey("Ben", Species.TAMARIN, 4, 35,
            FavoriteFood.INSECTS, Sex.MALE, 16);
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23,
            FavoriteFood.LEAVES, Sex.FEMALE, 43);
    primateSanctuary.addNewMonkey("Jared", Species.MANGABEU, 7, 13,
            FavoriteFood.NUTS, Sex.MALE, 12);
    primateSanctuary.addNewMonkey("Olive", Species.GUEREZA, 15, 60,
            FavoriteFood.SEEDS, Sex.FEMALE, 25);
    primateSanctuary.addNewMonkey("Cal", Species.DRILL, 8, 14,
            FavoriteFood.FRUITS, Sex.MALE, 4.5);
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20,
            FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Grace", Species.SAKI, 17, 21,
            FavoriteFood.TREESAP, Sex.FEMALE, 14);
    primateSanctuary.addNewMonkey("Saanvi", Species.HOWLER, 14, 19,
            FavoriteFood.LEAVES, Sex.FEMALE, 2.1);
    primateSanctuary.addNewMonkey("Elena", Species.DRILL, 3, 15,
            FavoriteFood.TREESAP, Sex.FEMALE, 30);
    primateSanctuary.addNewMonkey("Stefan", Species.SAKI, 14, 23,
            FavoriteFood.INSECTS, Sex.MALE, 10);
    primateSanctuary.addNewMonkey("Carlo", Species.SPIDER, 21, 12,
            FavoriteFood.LEAVES, Sex.FEMALE, 24);
    primateSanctuary.addNewMonkey("Rio", Species.MANGABEU, 17, 31,
            FavoriteFood.NUTS, Sex.MALE, 12);
    primateSanctuary.addNewMonkey("Jessie", Species.GUEREZA, 9, 6,
            FavoriteFood.SEEDS, Sex.FEMALE, 15);

    //After monkeys are sent to isolation, updating health status of few monkey as false(unhealthy)
    List<Monkey> allMonkeys = primateSanctuary.getAllMonkeys();
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(0), false);
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(5), false);
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(7),false);
    for (Monkey monkey : allMonkeys) {
      primateSanctuary.sendMonkeyToEnclosure(monkey);
    }

    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(2), false);
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(3), false);
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(4), false);
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(1), false);

    String monkeyList = primateSanctuary.getAllMonkeysHoused();
    System.out.println("\n **************** Species housed in Sanctuary"
            + " are stated below (Alphabetic by Name)  "
            + "****************************** \n" + monkeyList);

    //After monkey is in enclosure and it falls sick. Monkey will be sent back to isolation
    primateSanctuary.updateMedicalHealthOfMonkey(allMonkeys.get(4), false);
    primateSanctuary.getAllMonkeysHoused();
    String shoppingList = primateSanctuary.getShoppingList();
    System.out.println("\n *************** Shopping list for the species housed in Sanctuary is : "
            + "********************* \n" + shoppingList);

    primateSanctuary.increaseIsolations(10);
    primateSanctuary.increaseEnclosures(5);

    String lookUpSpeciesResult = primateSanctuary.lookUpSpecies(Species.HOWLER);
    System.out.println("\n **************** Looking up for monkeys belonging to " + Species.HOWLER
            + " type *****************************  \n" + lookUpSpeciesResult);


    String sign = primateSanctuary.produceSign(102);
    System.out.println("\n **************** Species Housed in Enclosure 102 (Sign) are "
            + ": ****************************\n" + sign);

    String sign1 = primateSanctuary.produceSign(100);
    System.out.println("\n **************** Species Housed in Enclosure 100 (Sign) are "
            + ": ****************************\n" + sign1);

    String specieList = primateSanctuary.getSpeciesList();
    System.out.println("\n **************** Different Species Housed are (alphabetic species)"
            + " : **********************\n" + specieList);
  }

}
