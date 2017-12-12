package by.bsac.tcs.domain.service.exception;

public class PostBoxServiceException extends Exception {

  public PostBoxServiceException(String message) {
    super(message);
  }

  public PostBoxServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
