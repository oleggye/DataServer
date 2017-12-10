package by.bsac.tcs.logic.service.exception;

public class RequestServiceException extends Exception {

  public RequestServiceException(String message) {
    super(message);
  }

  public RequestServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
