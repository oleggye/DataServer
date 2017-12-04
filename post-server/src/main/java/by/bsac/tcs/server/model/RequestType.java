package by.bsac.tcs.server.model;

import java.util.Objects;

public enum RequestType {
  REGISTRATION(0), KEEP_ALIVE(1), HAS_OPENED(2), HAS_CLOSED(3), STATE_CHANGED(4);

  private int typeCode;


  RequestType(int typeCode) {
    this.typeCode = typeCode;
  }

  /**
   * Converts to {@link RequestType} instance according its typeCode value
   *
   * @param typeCode protocol representation of {@link RequestType}
   * @return instance of {@link RequestType}
   */
  public static RequestType getRequestType(int typeCode) {
    RequestType requestType = null;

    for (RequestType element : RequestType.values()) {
      if (element.typeCode == typeCode) {
        requestType = element;
      }
    }

    if (Objects.isNull(requestType)) {
      throw new IllegalArgumentException(
          String.format("No such requestType constant for typeCode = %d", typeCode));
    }
    return requestType;
  }
}
