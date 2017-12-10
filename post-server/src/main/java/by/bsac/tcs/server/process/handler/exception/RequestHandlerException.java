package by.bsac.tcs.server.process.handler.exception;

public class RequestHandlerException extends RuntimeException {

  public RequestHandlerException(String message) {
    super(message);
  }

  public RequestHandlerException(String message, Throwable cause) {
    super(message, cause);
  }
}
