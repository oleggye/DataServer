package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;

public interface EventService {

  void log(EventLog eventLog) throws ServiceException;

  void register(EventLog eventLog) throws ServiceException;

  void changed(EventLog eventLog) throws ServiceException;

  void opened(EventLog eventLog) throws ServiceException;

  void closed(EventLog eventLog) throws ServiceException;

  void keepAlive(EventLog eventLog) throws ServiceException;
}
