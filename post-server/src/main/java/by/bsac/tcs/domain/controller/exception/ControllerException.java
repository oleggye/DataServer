package by.bsac.tcs.domain.controller.exception;

public class ControllerException extends Exception {

  public ControllerException(String message) {
    super(message);
  }

  public ControllerException(String message, Throwable cause) {
    super(message, cause);
  }
}
