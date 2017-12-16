package by.bsac.tcs.domain.service.impl;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.service.EventLogService;
import by.bsac.tcs.domain.service.exception.EventLogServiceException;

public class EventLogServiceImpl implements EventLogService {

  @Override
  public void log(EventLog eventLog) throws EventLogServiceException {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
