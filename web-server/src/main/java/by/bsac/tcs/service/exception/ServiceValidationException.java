package by.bsac.tcs.service.exception;

public class ServiceValidationException extends ServiceException {

  public ServiceValidationException(String message) {
    super(message);
  }

  public ServiceValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
