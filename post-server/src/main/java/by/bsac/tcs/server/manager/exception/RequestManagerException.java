package by.bsac.tcs.server.exception;

public class RequestManagerException extends RuntimeException {

  public RequestManagerException(String message) {
    super(message);
  }

  public RequestManagerException(String message, Throwable cause) {
    super(message, cause);
  }
}
