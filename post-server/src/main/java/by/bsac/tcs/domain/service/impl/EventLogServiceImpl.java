package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.EventLogDaoFactory;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.exception.EventLogServiceException;

public class EventLogServiceImpl implements EventLogService {

  private EventLogDao eventLogDAO;

  public EventLogServiceImpl() {
    final EventLogDaoFactory daoFactory = EventLogDaoFactory.getInstance();
    this.eventLogDAO = daoFactory.getDao();
  }

  public EventLogServiceImpl(EventLogDao eventLogDAO) {
    this.eventLogDAO = eventLogDAO;
  }

  @Override
  public void log(EventLog eventLog) throws EventLogServiceException {
    try {
      eventLogDAO.save(eventLog);
    } catch (DaoException e) {
      throw new EventLogServiceException("An exception occurred while log()", e);
    }
  }
}
