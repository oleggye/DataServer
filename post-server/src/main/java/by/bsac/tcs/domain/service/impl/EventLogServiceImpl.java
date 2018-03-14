package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.EventLogDaoFactory;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.exception.EventLogServiceException;

public class EventLogServiceImpl implements EventLogService {

  private EventLogDao eventLogDao;

  public EventLogServiceImpl() {
    final EventLogDaoFactory daoFactory = EventLogDaoFactory.getInstance();
    this.eventLogDao = daoFactory.getDao();
  }

  public EventLogServiceImpl(EventLogDao eventLogDao) {
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
