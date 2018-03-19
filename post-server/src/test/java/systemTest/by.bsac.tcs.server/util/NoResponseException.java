package by.bsac.tcs.server.util;

public class NoResponseException extends RuntimeException {

  public NoResponseException(String message) {
    super(message);
  }

  public NoResponseException(String message, Throwable cause) {
    super(message, cause);
  }
}
