package by.bsac.tcs.domain.dao;

import by.bsac.tcs.domain.dao.exception.DAOException;
import by.bsac.tcs.domain.model.EventLog;

public interface EventLogDAO {

  EventLog findById(long id) throws DAOException;

  void save(EventLog eventLog) throws DAOException;

}
