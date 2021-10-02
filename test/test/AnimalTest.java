package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import sanctuary.Animal;
import sanctuary.CurrentLocStatus;
import sanctuary.FavoriteFood;
import sanctuary.Housing;
import sanctuary.HousingUnits;
import sanctuary.JungleSanctuary;
import sanctuary.Monkey;
import sanctuary.Sex;
import sanctuary.Size;
import sanctuary.Species;
import static org.junit.Assert.assertEquals;

public class AnimalTest {
  private JungleSanctuary primateSanctuary;
  private Housing house;

  @Before
  public void setup(){
    primateSanctuary = primate();
    house =  house(2,5);
//    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
//    primateSanctuary.addNewMonkey("Grace",Species.SAKI,17,21,FavoriteFood.TREESAP,Sex.FEMALE,14);
//    primateSanctuary.addNewMonkey("Saanvi",Species.HOWLER,14,19,FavoriteFood.LEAVES,Sex.FEMALE,2.1);
//    primateSanctuary.addNewMonkey("Elena", Species.DRILL, 3, 15, FavoriteFood.TREESAP, Sex.FEMALE,30);
//    primateSanctuary.addNewMonkey("Stefan",Species.SAKI,14,23,FavoriteFood.INSECTS,Sex.MALE,10);
//    primateSanctuary.addNewMonkey("Carlo", Species.SPIDER, 21, 12, FavoriteFood.LEAVES, Sex.FEMALE, 24);
//    primateSanctuary.addNewMonkey("Rio",Species.MANGABEU,17,31,FavoriteFood.NUTS,Sex.MALE,12);
//    primateSanctuary.addNewMonkey("Jessie", Species.GUEREZA, 9, 6, FavoriteFood.SEEDS, Sex.FEMALE, 15);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNullSpeciesTest(){
    primateSanctuary.addNewMonkey("Alice",null,10,12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNegAgeTest(){
    primateSanctuary.addNewMonkey("Alice", Species.DRILL,-1,12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNegWeightTest(){
    primateSanctuary.addNewMonkey("Alice", Species.DRILL,1,-12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNegSizeTest(){
    primateSanctuary.addNewMonkey("Alice", Species.DRILL,1,12, FavoriteFood.EGGS, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNullFoodTest(){
    primateSanctuary.addNewMonkey("Alice", Species.DRILL,1,12, null, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyNullSexTest(){
    primateSanctuary.addNewMonkey("Alice", Species.DRILL,1,12, null, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void addNewMonkeyTest(){
    primateSanctuary.addNewMonkey("Alice",null,10,12, FavoriteFood.EGGS,null,21);
  }

  @Test
  public void addedMonkeyTransferToIsolation(){
    boolean isMickPresent=false;
    for(Monkey monkey : primateSanctuary.getAllMonkeys()){
      if(monkey.getMonkeyName() == "Mick"){
        isMickPresent = true;
      }
    }
    CurrentLocStatus actualLocation=CurrentLocStatus.UNASSIGNED;
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    for(Monkey monkey : primateSanctuary.getAllMonkeys()){
      if(monkey.getMonkeyName().equals("Mick")){
        actualLocation = monkey.getMonkeyLocation();
      }
    }
    assertEquals(false,isMickPresent);
    assertEquals(CurrentLocStatus.ISOLATION, actualLocation);
  }

  @Test
  public void addNewMonkeyToSanctuaryTest(){
    int monkeyInSanctuaryBefore = primateSanctuary.getAllMonkeys().size();
    System.out.println("monkeyInSanctuaryBefore : " + monkeyInSanctuaryBefore);
    primateSanctuary.addNewMonkey("Olive", Species.GUEREZA, 15, 60, FavoriteFood.SEEDS, Sex.FEMALE, 25);
    assertEquals(monkeyInSanctuaryBefore+1,primateSanctuary.getAllMonkeys().size());
  }

//  @Test
//  public void monkeyTransferToFullIsolation(){
//     primateSanctuary.addNewMonkey("Ben",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
//     primateSanctuary.addNewMonkey("Benny",Species.HOWLER,5, 35,FavoriteFood.INSECTS,Sex.MALE,16);
//    int monkeyBeforeAddingNew = primateSanctuary.getAllMonkeys().size();
//
//    System.out.println(monkeyBeforeAddingNew);
//    primateSanctuary.addNewMonkey("chase",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
//    // primateSanctuary.addNewMonkey("Benny",Species.HOWLER,5, 35,FavoriteFood.INSECTS,Sex.MALE,16);
//    System.out.println(monkeyBeforeAddingNew);
//    System.out.println(primateSanctuary.getAllMonkeys().size());
//    assertEquals(monkeyBeforeAddingNew,primateSanctuary.getAllMonkeys().size());
//  }

  protected JungleSanctuary primate(){
    return new Animal();
  }

  protected Housing house(int isolations, int enclosures){
    return new HousingUnits(isolations,enclosures);
  }

  @Test
  public void getAllMonkeysTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    assertEquals(2,primateSanctuary.getAllMonkeys().size());
    assertEquals("Zeke",primateSanctuary.getAllMonkeys().get(0).getMonkeyName());
    assertEquals("Mick",primateSanctuary.getAllMonkeys().get(1).getMonkeyName());
    assertEquals(Species.HOWLER,primateSanctuary.getAllMonkeys().get(0).getSpeciesDesignation());
    assertEquals(Species.HOWLER,primateSanctuary.getAllMonkeys().get(1).getSpeciesDesignation());
    assertEquals(11,primateSanctuary.getAllMonkeys().get(0).getMonkeyAge());
    assertEquals(12,primateSanctuary.getAllMonkeys().get(1).getMonkeyAge());
    assertEquals(Size.MEDIUM,primateSanctuary.getAllMonkeys().get(0).getMonkeySize());
    assertEquals(Size.LARGE,primateSanctuary.getAllMonkeys().get(1).getMonkeySize());
  }

  @Test
  public void getAllMonkeysHoused() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    primateSanctuary.addNewMonkey("Ben",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
    assertEquals("Zeke",primateSanctuary.getAllMonkeys().get(0).getMonkeyName());
    assertEquals("Mick",primateSanctuary.getAllMonkeys().get(1).getMonkeyName());
    assertEquals("Ben",primateSanctuary.getAllMonkeys().get(2).getMonkeyName());
    assertEquals(Species.HOWLER,primateSanctuary.getAllMonkeys().get(0).getSpeciesDesignation());
    assertEquals(Species.HOWLER,primateSanctuary.getAllMonkeys().get(1).getSpeciesDesignation());
    assertEquals(11,primateSanctuary.getAllMonkeys().get(0).getMonkeyAge());
    assertEquals(12,primateSanctuary.getAllMonkeys().get(1).getMonkeyAge());
    assertEquals(Size.MEDIUM,primateSanctuary.getAllMonkeys().get(0).getMonkeySize());
    assertEquals(Size.LARGE,primateSanctuary.getAllMonkeys().get(1).getMonkeySize());
  }

  @Test
  public void produceSignTest() {
    primateSanctuary.addNewMonkey("Ben",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0),true);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    String expected = "---> Monkey Name : Ben ,Sex : MALE ,Favorite Food : INSECTS" ;
    assertEquals(expected,primateSanctuary.produceSign(100).trim());
  }

  @Test
  public void updateMedicalHealthOfMonkeyTest() {
    primateSanctuary.addNewMonkey("Olive", Species.GUEREZA, 15, 60, FavoriteFood.SEEDS, Sex.FEMALE, 25);
    primateSanctuary.updateMedicalHealthOfMonkey( primateSanctuary.getAllMonkeys().get(0),false);
    assertEquals(false,primateSanctuary.getAllMonkeys().get(0).getMonkeyMedicalStatus());
  }

  @Test
  public void sendMonkeyToEnclosure() {
    primateSanctuary.addNewMonkey("Ben",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
    primateSanctuary.updateMedicalHealthOfMonkey(primateSanctuary.getAllMonkeys().get(0),true);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(CurrentLocStatus.ENCLOSURE,primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  @Test
  public void getShoppingList() {
    primateSanctuary.addNewMonkey("Ben",Species.HOWLER,4,35,FavoriteFood.INSECTS,Sex.MALE,16);
    String expected = "TREESAP : 0 gr\n" +
            "INSECTS : 250 gr\n" +
            "LEAVES : 0 gr\n" +
            "EGGS : 0 gr\n" +
            "NUTS : 0 gr\n" +
            "SEEDS : 0 gr\n" +
            "FRUITS : 0 gr" ;
    assertEquals(expected,primateSanctuary.getShoppingList().trim());
  }

  @Test(expected=IllegalArgumentException.class)
  public void increaseIsolationsNegTest() {
    primateSanctuary.increaseIsolations(-10);
  }

  @Test(expected=IllegalArgumentException.class)
  public void increaseEnclosuresNegTest() {
    primateSanctuary.increaseEnclosures(-10);
  }

  @Test
  public void lookUpSpeciesTest() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.addNewMonkey("Mick", Species.TAMARIN, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    String expected = "Monkey of type : HOWLER and name Zeke is housed in ISOLATION with house ID : 500";
    assertEquals(expected,primateSanctuary.lookUpSpecies(Species.HOWLER).trim());

  }
}
