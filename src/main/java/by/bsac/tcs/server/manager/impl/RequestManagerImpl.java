package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.request.handler.RequestHandler;
import by.bsac.tcs.server.request.handler.RequestHandlerDao;
import by.bsac.tcs.server.request.handler.impl.RequestHandlerImpl;
import by.bsac.tcs.server.util.LogMessageSharper;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestManagerImpl extends AbstractRequestManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerImpl.class);

  private final RequestHandlerDao dao;

  public RequestManagerImpl() {
    dao = RequestHandlerDao.getInstance();
  }

  public RequestManagerImpl(RequestHandlerDao dao) {
    this.dao = dao;
  }

  public void manage(final Socket socket) {
    LOGGER.info(LogMessageSharper.formIncommingUserLogMessage(socket));
    RequestHandler requestHandler = dao.getRequestProcessor(socket);
    pool.submit(requestHandler);
  }
}
