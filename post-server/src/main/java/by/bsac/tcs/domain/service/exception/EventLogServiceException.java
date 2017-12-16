package by.bsac.tcs.domain.service.exception;

public class EventLogServiceException extends ServiceException {

  public EventLogServiceException(String message) {
    super(message);
  }

  public EventLogServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
