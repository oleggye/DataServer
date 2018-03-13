package by.bsac.tcs.server.process.response;

import by.bsac.tcs.server.process.response.impl.SimpleResponseWriter;
import java.net.Socket;

public class ResponseWriterFactory {

  private static final ResponseWriter responseWriter = new SimpleResponseWriter();

  private ResponseWriterFactory() {
  }

  public static ResponseWriterFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public ResponseWriter getResponseWriter() {
    return responseWriter;
  }

  private static class SingletonHolder {

    private static final ResponseWriterFactory INSTANCE = new ResponseWriterFactory();

    public static ResponseWriterFactory getInstance() {
      return INSTANCE;
    }
  }
}
