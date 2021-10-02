package sanctuary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class EnclosureTest {
  Enclosure e1;
  Enclosure e2 ;
  JungleSanctuary primateSanctuary = primate();
  @Before
  public void setup(){
    e1= new Enclosure(100,Species.DRILL);
    e2= new Enclosure(50,Species.HOWLER);
  }
   @Test
  public void getEnclosureIDTest(){
    assertEquals(102,e1.getEnclosureID());
    assertEquals(103,e2.getEnclosureID());
   }

  @Test(expected=IllegalArgumentException.class)
  public void EnclosureNegAreaTest(){
    Enclosure e = new Enclosure(-100,Species.DRILL);
  }

  @Test(expected=IllegalArgumentException.class)
  public void EnclosureSpecieNullTest(){
    Enclosure e = new Enclosure(100,null);
  }

  @Test
  public void getSpecieHousedTest(){
    assertEquals(Species.DRILL,e1.getSpecieHoused());
    assertEquals(Species.HOWLER,e2.getSpecieHoused());
  }

  @Test
  public void getAvailableAreaTest(){
    assertEquals(100,e1.getAvailableArea(),0.001);
  }

  @Test
  public void updateAvailableAreaDecrease(){
    e1.updateAvailableArea(10,true);
    assertEquals(90,e1.getAvailableArea(),0.1);
  }

  @Test
  public void updateAvailableAreaIncrease(){
    e1.updateAvailableArea(10,false);
    assertEquals(110,e1.getAvailableArea(),0.1);
  }

  protected JungleSanctuary primate(){
    return new Animal();
  }
  protected Housing enclosure(double area,Species s){
    return new Enclosure(area,s);
  }
}