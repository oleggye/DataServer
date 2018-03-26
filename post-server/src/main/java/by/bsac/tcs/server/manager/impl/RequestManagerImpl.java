package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerFactory;
import java.net.Socket;

public class RequestManagerImpl extends AbstractRequestManager {

  private final RequestHandlerFactory handlerFactory;

  public RequestManagerImpl() {
    handlerFactory = RequestHandlerFactory.getInstance();
  }

  public RequestManagerImpl(RequestHandlerFactory handlerFactory) {
    this.handlerFactory = handlerFactory;
  }

  public void manage(final Socket socket) {
    final RequestHandler requestHandler = handlerFactory.getRequestHandler(socket);
    pool.submit(requestHandler);
  }
}
