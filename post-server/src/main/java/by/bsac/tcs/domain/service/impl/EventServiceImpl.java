package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.EventLogDaoFactory;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.exception.EventLogServiceException;

public class EventServiceImpl implements EventService {

  private EventLogDao eventLogDao;

  public EventServiceImpl() {
    final EventLogDaoFactory daoFactory = EventLogDaoFactory.getInstance();
    this.eventLogDao = daoFactory.getDao();
  }

  public EventServiceImpl(EventLogDao eventLogDao) {
    this.eventLogDao = eventLogDao;
  }

  @Override
  public void log(EventLog eventLog) throws EventLogServiceException {
    try {
      eventLogDao.save(eventLog);
    } catch (DaoException e) {
      throw new EventLogServiceException("An exception occurred while log()", e);
    }
  }
}
