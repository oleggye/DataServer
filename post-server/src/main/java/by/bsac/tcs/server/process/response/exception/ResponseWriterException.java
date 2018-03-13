package by.bsac.tcs.server.process.response.exception;

public class ResponseWriterException extends Exception {

  public ResponseWriterException(String message) {
    super(message);
  }

  public ResponseWriterException(String message, Throwable cause) {
    super(message, cause);
  }
}
