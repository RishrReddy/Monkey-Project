package sanctuary;


import java.util.concurrent.atomic.AtomicInteger;

class Enclosure extends HousingUnits{

  private final int enclosureID;
  private final double totalArea;
  private final Species specieHoused;
  private double availableArea;
  private static final AtomicInteger enclosureIDGenerator = new AtomicInteger(100);

  public Enclosure( double totalArea, Species specieHoused){
    if(totalArea<0){
      throw new IllegalArgumentException("Total Area of enclosure can't be negative");
    }
    if(specieHoused==null){
      throw new IllegalArgumentException("specie Housed can't be negative");
    }
    this.enclosureID=enclosureIDGenerator.getAndIncrement();
    this.totalArea=totalArea;
    this.specieHoused=specieHoused;
    this.availableArea=totalArea;
  }

  public int getEnclosureID(){
    return this.enclosureID;
  }
  public Species getSpecieHoused(){
    return this.specieHoused;
  }
  public double getAvailableArea(){
    return this.availableArea;
  }
  public double getTotalArea(){
    return this.totalArea;
  }

  public void updateAvailableArea(int areaToChange,boolean toReduce){
    this.availableArea = toReduce? this.availableArea-areaToChange : this.availableArea+areaToChange;
  }

}
