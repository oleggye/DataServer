package by.bsac.tcs.server.process.handler;

import by.bsac.tcs.server.process.handler.impl.RequestHandlerImpl;
import java.net.Socket;

public class RequestHandlerFactory {

  private RequestHandlerFactory() {
  }

  public static RequestHandlerFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public RequestHandler getRequestHandler(Socket requestSocket) {
    return new RequestHandlerImpl(requestSocket);
  }

  private static class SingletonHolder {

    private static final RequestHandlerFactory INSTANCE = new RequestHandlerFactory();

    public static RequestHandlerFactory getInstance() {
      return INSTANCE;
    }
  }
}
