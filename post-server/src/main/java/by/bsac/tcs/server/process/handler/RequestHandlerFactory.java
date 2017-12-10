package by.bsac.tcs.server.process.handler;

import by.bsac.tcs.server.process.handler.impl.RequestHandlerImpl;
import java.net.Socket;

public class RequestHandlerFactory {

  private static final RequestHandlerFactory INSTANCE = new RequestHandlerFactory();

  private RequestHandlerFactory() {
  }

  public static RequestHandlerFactory getInstance() {
    return INSTANCE;
  }

  public RequestHandler getRequestProcessor(Socket requestSocket) {
    return new RequestHandlerImpl(requestSocket);
  }

}
