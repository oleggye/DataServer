package by.bsac.tcs.server.model;

import static java.util.Objects.isNull;

public enum Event {
  REGISTRATION(0), KEEP_ALIVE(1), HAS_OPENED(2), HAS_CLOSED(3), STATE_CHANGED(4);

  private int typeCode;


  Event(int eventCode) {
    this.typeCode = eventCode;
  }

  /**
   * Converts to {@link Event} instance according its eventCode value
   *
   * @param eventCode protocol representation of {@link Event}
   * @return instance of {@link Event}
   */
  public static Event getEvent(int eventCode) {
    Event event = null;

    for (Event element : Event.values()) {
      if (element.typeCode == eventCode) {
        event = element;
      }
    }

    if (isNull(event)) {
      throw new IllegalArgumentException(
          String.format("No such event constant for eventCode = %d", eventCode));
    }
    return event;
  }
}
