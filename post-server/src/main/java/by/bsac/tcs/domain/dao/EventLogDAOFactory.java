package by.bsac.tcs.domain.dao;

import by.bsac.tcs.domain.dao.impl.EventLogDAOImpl;
import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;

public class EventLogDAOFactory {

  private EventLogDAOFactory() {
  }

  private static final EventLogDAOFactory INSTANCE = new EventLogDAOFactory();

  private static final EventLogDAO eventLogDao = new EventLogDAOImpl(DataSourceHolder.getInstance().getDataSource());

  public EventLogDAOFactory getInstance() {
    return INSTANCE;
  }

  public EventLogDAO getDAO() {
    return eventLogDao;
  }
}
