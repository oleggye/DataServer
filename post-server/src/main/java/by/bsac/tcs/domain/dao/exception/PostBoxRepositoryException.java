package by.bsac.tcs.domain.dao.exception;

public class PostBoxRepositoryException extends Exception {

  public PostBoxRepositoryException(String message) {
    super(message);
  }

  public PostBoxRepositoryException(String message, Throwable cause) {
    super(message, cause);
  }
}
