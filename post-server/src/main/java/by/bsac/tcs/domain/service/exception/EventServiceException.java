package by.bsac.tcs.domain.service.exception;

public class EventServiceException extends ServiceException {

  public EventServiceException(String message) {
    super(message);
  }

  public EventServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
