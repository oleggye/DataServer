package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerDao;
import java.net.Socket;

//TODO: ADD LOGGING
public class RequestManagerImpl extends AbstractRequestManager {

  private final RequestHandlerDao dao;

  public RequestManagerImpl() {
    dao = RequestHandlerDao.getInstance();
  }

  public RequestManagerImpl(RequestHandlerDao dao) {
    this.dao = dao;
  }

  public void manage(Socket socket) {
    RequestHandler requestHandler = dao.getRequestProcessor(socket);
    pool.submit(requestHandler);
  }
}
