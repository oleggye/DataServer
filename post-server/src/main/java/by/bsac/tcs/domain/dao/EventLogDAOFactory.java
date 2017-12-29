package by.bsac.tcs.domain.dao;

import by.bsac.tcs.domain.dao.impl.EventLogDAOImpl;
import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;

public class EventLogDAOFactory {

  private EventLogDAOFactory() {
  }

  private static final EventLogDAO eventLogDao = new EventLogDAOImpl(
      DataSourceHolder.getInstance().getDataSource());

  public static EventLogDAOFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public EventLogDAO getDAO() {
    return eventLogDao;
  }

  private static class SingletonHolder {

    private static final EventLogDAOFactory INSTANCE = new EventLogDAOFactory();

    public static EventLogDAOFactory getInstance() {
      return INSTANCE;
    }
  }
}
