package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.service.impl.EventLogServiceImpl;

public class EventLogServiceFactory {

  private EventLogServiceFactory() {
  }

  private static final EventLogService service = new EventLogServiceImpl();

  public static EventLogServiceFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public EventLogService getEventLogService() {
    return service;
  }

  private static class SingletonHolder {

    private static final EventLogServiceFactory INSTANCE = new EventLogServiceFactory();

    public static EventLogServiceFactory getInstance() {
      return INSTANCE;
    }
  }
}
