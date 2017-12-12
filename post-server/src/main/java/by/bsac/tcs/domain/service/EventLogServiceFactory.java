package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.service.impl.EventLogServiceImpl;

public class EventLogServiceFactory {

  private EventLogServiceFactory() {
  }

  private static final EventLogServiceFactory INSTANCE = new EventLogServiceFactory();
  private static final EventLogService service = new EventLogServiceImpl();

  public static EventLogServiceFactory getInstance() {
    return INSTANCE;
  }

  public EventLogService getEventLogService() {
    return service;
  }
}
