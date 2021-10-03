package sanctuary;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Isolation class represents an isolation in the Jungle Sanctuary and
 * has attributes like Isolationid.
 */
final class Isolation extends HousingUnit {
  private final int isolationId;
  private static final AtomicInteger isolationIDGenerator = new AtomicInteger(500);

  /**
   * Constructor to create isolation object and
   * assign an ID to isolation.
   */
  public Isolation() {
    this.isolationId = isolationIDGenerator.getAndIncrement();
  }

  /**
   * Getter method to return isolation's id.
   *
   * @return isolation ID.
   */
  public int getIsolationId() {
    return this.isolationId;
  }
}
