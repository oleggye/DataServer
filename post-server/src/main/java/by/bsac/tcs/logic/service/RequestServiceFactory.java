package by.bsac.tcs.logic.service;

import by.bsac.tcs.logic.service.impl.RequestServiceImpl;

public class RequestServiceFactory {
  private RequestServiceFactory(){

  }
  private static final RequestServiceFactory INSTANCE = new RequestServiceFactory();
  private static final RequestService service = new RequestServiceImpl();

  public static RequestServiceFactory getInstance(){
    return INSTANCE;
  }

  public static RequestService getRequestService() {
    return service;
  }
}
