package by.bsac.tcs.server.model;

import static java.util.Objects.isNull;

public enum RequestType {
  REGISTRATION(0), KEEP_ALIVE(1), HAS_OPENED(2), HAS_CLOSED(3), STATE_CHANGED(4);

  private int typeCode;


  RequestType(int eventCode) {
    this.typeCode = eventCode;
  }

  /**
   * Converts to {@link RequestType} instance according its requestCode value
   *
   * @param requestCode protocol representation of {@link RequestType}
   * @return instance of {@link RequestType}
   */
  public static RequestType getRequestType(int requestCode) {
    RequestType requestType = null;

    for (RequestType element : RequestType.values()) {
      if (element.typeCode == requestCode) {
        requestType = element;
      }
    }

    if (isNull(requestType)) {
      throw new IllegalArgumentException(
          String.format("No such requestType constant for requestCode = %d", requestCode));
    }
    return requestType;
  }
}
