package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.EventLogDaoFactory;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventService;
import by.bsac.tcs.domain.service.exception.EventServiceException;
import by.bsac.tcs.domain.service.exception.ServiceException;

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
  public void log(EventLog eventLog) throws ServiceException {
    try {
      eventLogDao.save(eventLog);
    } catch (DaoException e) {
      throw new EventServiceException("An exception occurred while log()", e);
    }
  }

  @Override
  //TODO: add session
  public void register(EventLog eventLog) throws ServiceException {
    log(eventLog);
  }

  @Override
  public void changed(EventLog eventLog) throws ServiceException {
    log(eventLog);
  }

  @Override
  public void opened(EventLog eventLog) throws ServiceException {
    log(eventLog);
  }

  @Override
  public void closed(EventLog eventLog) throws ServiceException {
    log(eventLog);
  }

  @Override
  public void keepAlive(EventLog eventLog) throws ServiceException {
    log(eventLog);
  }
}
