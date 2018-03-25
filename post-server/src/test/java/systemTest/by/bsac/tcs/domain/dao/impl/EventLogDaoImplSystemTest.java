package by.bsac.tcs.domain.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.dao.util.pool.DataSourceProducer;
import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import javax.sql.DataSource;
import org.junit.Test;

public class EventLogDaoImplSystemTest {

  private final EventLogDao dao;
  private final DataSource dataSource;

  public EventLogDaoImplSystemTest() {
    final DataSourceProducer holder = DataSourceProducer.getInstance();
    dataSource = holder.getDataSource();

    dao = new EventLogDaoImpl(dataSource);
  }

  @Test
  public void findById() throws DaoException {
    final long id = 1;
    final long postBoxId = 222850;
    final Event event = Event.REGISTRATION;
    final int quantity = 0;
    final long epochTime = 0;

    EventLog eventLog = dao.findById(id);

    assertNotNull(eventLog);
    assertEquals(id, eventLog.getId());
    assertEquals(postBoxId, eventLog.getPostBoxId());
    assertEquals(event, eventLog.getEvent());
    assertEquals(quantity, eventLog.getQuantity());
    assertEquals(epochTime, eventLog.getEpochTime());
  }

  @Test
  public void save() throws DaoException {
    final long postBoxId = 222850;
    final Event event = Event.REGISTRATION;
    final int quantity = 7;
    final long epochTime = 1234567894;

    final EventLog expectedEventLog = new EventLog(postBoxId, event, quantity, epochTime);

    long expectedIndex = dao.save(expectedEventLog);

    final EventLog actualEventLog = dao.findById(expectedIndex);

    assertNotNull(actualEventLog);
    assertEquals(expectedIndex, actualEventLog.getId());
    assertEquals(expectedEventLog.getPostBoxId(), actualEventLog.getPostBoxId());
    assertEquals(expectedEventLog.getEvent(), actualEventLog.getEvent());
    assertEquals(expectedEventLog.getQuantity(), actualEventLog.getQuantity());
  }
}