package sanctuary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonkeyTest {
  Monkey m1;
  JungleSanctuary primateSanctuary;
  @Before
  public void setup(){
    primateSanctuary = primate();
    m1= new Monkey("Jessie", Species.GUEREZA, 9, 6, FavoriteFood.SEEDS, Sex.FEMALE, 15);
  }


  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNullSpeciesTest(){
    new Monkey("Alice",null,10,12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNegAgeTest(){
    new Monkey("Alice", Species.DRILL,-1,12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNegWeightTest(){
    new Monkey("Alice", Species.DRILL,1,-12, FavoriteFood.EGGS, Sex.MALE,21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNegSizeTest(){
    new Monkey("Alice", Species.DRILL,1,12, FavoriteFood.EGGS, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNullFoodTest(){
    new Monkey("Alice", Species.DRILL,1,12, null, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorNullSexTest(){
    new Monkey("Alice", Species.DRILL,1,12, null, Sex.MALE,-21);
  }

  @Test(expected=IllegalArgumentException.class)
  public void monkeyConstructorTest(){
    new Monkey("Alice",null,10,12, FavoriteFood.EGGS,null,21);
  }

  @Test
  public void getMonkeyName() {
    assertEquals("Jessie",m1.getMonkeyName());
  }

  @Test
  public void getSpeciesDesignation() {
    assertEquals(Species.GUEREZA,m1.getSpeciesDesignation());
  }

  @Test
  public void getMonkeyAge() {
    assertEquals(9,m1.getMonkeyAge());
  }

  @Test
  public void getMonkeyWeight() {
    assertEquals(6,m1.getMonkeyWeight(),0.01);
  }

  @Test
  public void getMonkeyFavFood() {
    assertEquals(FavoriteFood.SEEDS,m1.getMonkeyFavFood());
  }

  @Test
  public void getMonkeySex() {
    assertEquals(Sex.FEMALE,m1.getMonkeySex());
  }

  @Test
  public void getMonkeySize() {
    assertEquals(Size.MEDIUM,m1.getMonkeySize());
  }

  @Test
  public void getMonkeyMedicalStatus() {
    Monkey m4 = new Monkey("Mickeala", Species.HOWLER, 19, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    assertEquals(true,m4.getMonkeyMedicalStatus());
    m4.updateMonkeyHealthStatus(false);
    assertEquals(false,m4.getMonkeyMedicalStatus());
  }

  @Test
  public void getMonkeyID() {
    assertEquals(1024,m1.getMonkeyID());
  }

  @Test
  public void getMonkeyHousingID() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(100,primateSanctuary.getAllMonkeys().get(0).getMonkeyHousingID());
  }

  @Test
  public void getMonkeyLocation() {
    primateSanctuary.addNewMonkey("Zeke", Species.HOWLER, 11, 20, FavoriteFood.EGGS, Sex.MALE, 17.9);
    primateSanctuary.sendMonkeyToEnclosure(primateSanctuary.getAllMonkeys().get(0));
    assertEquals(CurrentLocStatus.ENCLOSURE,primateSanctuary.getAllMonkeys().get(0).getMonkeyLocation());
  }

  @Test(expected=IllegalStateException.class)
  public void updateMonkeyLocation() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyLocation(CurrentLocStatus.ENCLOSURE);
    assertEquals(200,m2.getMonkeyLocation());
  }

  @Test(expected=IllegalArgumentException.class)
  public void updateMonkeyNegHouseID() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyHouseID(-200);
  }

  @Test
  public void updateMonkeyHealthStatus() {
    Monkey m2 = new Monkey("Mick", Species.HOWLER, 12, 23, FavoriteFood.LEAVES, Sex.FEMALE, 43);
    m2.updateMonkeyHealthStatus(false);
    assertEquals(false,m2.getMonkeyMedicalStatus());
  }

  protected JungleSanctuary primate(){
    return new Animal();
  }

}