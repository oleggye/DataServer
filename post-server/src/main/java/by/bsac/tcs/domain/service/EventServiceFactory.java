package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.service.impl.EventServiceImpl;

public class EventServiceFactory {

  private EventServiceFactory() {
  }

  private static final EventService service = new EventServiceImpl();

  public static EventServiceFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public EventService getEventService() {
    return service;
  }

  private static class SingletonHolder {

    private static final EventServiceFactory INSTANCE = new EventServiceFactory();

    public static EventServiceFactory getInstance() {
      return INSTANCE;
    }
  }
}
