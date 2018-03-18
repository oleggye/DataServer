package by.bsac.tcs.domain.dao;

import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.model.EventLog;

public interface EventLogDao {

  EventLog findById(long id) throws DaoException;

  long save(EventLog eventLog) throws DaoException;

}
