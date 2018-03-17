package by.bsac.tcs.domain.service;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.exception.ServiceException;

public interface EventService {

  void log(EventLog eventLog) throws ServiceException;
}
