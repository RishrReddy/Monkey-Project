package sanctuary;

import java.util.concurrent.atomic.AtomicInteger;

class Isolation extends HousingUnits{
  private final int isolationID;
  private static final AtomicInteger isolationIDGenerator = new AtomicInteger(500);

  public Isolation(){
    this.isolationID=isolationIDGenerator.getAndIncrement();
  }
  public int getIsolationID(){
    return this.isolationID;
  }
}
