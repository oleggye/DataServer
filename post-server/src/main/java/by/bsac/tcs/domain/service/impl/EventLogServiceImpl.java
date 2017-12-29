package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.dao.EventLogDAO;
import by.bsac.tcs.domain.dao.EventLogDAOFactory;
import by.bsac.tcs.domain.dao.exception.DAOException;
import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.exception.EventLogServiceException;

public class EventLogServiceImpl implements EventLogService {

  private EventLogDAO eventLogDAO;

  public EventLogServiceImpl() {
    final EventLogDAOFactory daoFactory = EventLogDAOFactory.getInstance();
    this.eventLogDAO = daoFactory.getDAO();
  }

  public EventLogServiceImpl(EventLogDAO eventLogDAO) {
    this.eventLogDAO = eventLogDAO;
  }

  @Override
  public void log(EventLog eventLog) throws EventLogServiceException {
    try {
      eventLogDAO.save(eventLog);
    } catch (DAOException e) {
      throw new EventLogServiceException("An exception occurred while log()", e);
    }
  }
}
