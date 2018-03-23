package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;

public interface EventService {

  void log(EventLog eventLog) throws ServiceException;

  String register(EventLog eventLog) throws ServiceException;

  String changed(EventLog eventLog) throws ServiceException;

  String opened(EventLog eventLog) throws ServiceException;

  String closed(EventLog eventLog) throws ServiceException;

  String keepAlive(EventLog eventLog) throws ServiceException;
}
