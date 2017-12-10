package by.bsac.tcs.server.manager;

import by.bsac.tcs.server.manager.impl.RequestManagerImpl;

public class RequestManagerFactory {

  private static final RequestManagerFactory INSTANCE = new RequestManagerFactory();
  private static final RequestManager REQUEST_MANAGER = new RequestManagerImpl();

  private RequestManagerFactory() {
  }

  public static RequestManagerFactory getInstance() {
    return INSTANCE;
  }

  public RequestManager getManager() {
    return REQUEST_MANAGER;
  }
}
