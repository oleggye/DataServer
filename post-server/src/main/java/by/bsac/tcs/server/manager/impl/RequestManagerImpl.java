package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerFactory;
import by.bsac.tcs.server.process.handler.impl.RequestHandlerImpl;
import by.bsac.tcs.server.util.LogMessageSharper;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestManagerImpl extends AbstractRequestManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerImpl.class);

  private final RequestHandlerFactory handlerFactory;

  public RequestManagerImpl() {
    handlerFactory = RequestHandlerFactory.getInstance();
  }

  public RequestManagerImpl(RequestHandlerFactory handlerFactory) {
    this.handlerFactory = handlerFactory;
  }

  public void manage(final Socket socket) {
    String infoMessage = LogMessageSharper.formIncomingUserLogMessage(socket);
    LOGGER.info(infoMessage);
    final RequestHandler requestHandler = handlerFactory.getRequestHandler(socket);
    pool.submit(requestHandler);
  }
}
