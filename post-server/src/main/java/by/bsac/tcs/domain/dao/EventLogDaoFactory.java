package by.bsac.tcs.domain.dao;

import by.bsac.tcs.domain.dao.impl.EventLogDaoImpl;
import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;

public class EventLogDaoFactory {

  private EventLogDaoFactory() {
  }

  private static final EventLogDao eventLogDao = new EventLogDaoImpl(
      DataSourceHolder.getInstance().getDataSource());

  public static EventLogDaoFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public EventLogDao getDao() {
    return eventLogDao;
  }

  private static class SingletonHolder {

    private static final EventLogDaoFactory INSTANCE = new EventLogDaoFactory();

    public static EventLogDaoFactory getInstance() {
      return INSTANCE;
    }
  }
}
