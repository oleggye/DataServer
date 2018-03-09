package by.bsac.tcs.domain.dao.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;
import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import javax.sql.DataSource;
import org.junit.Test;

public class EventLogDaoImplTest {

  private final EventLogDao dao;

  public EventLogDaoImplTest() {
    final DataSourceHolder holder = DataSourceHolder.getInstance();
    final DataSource dataSource = holder.getDataSource();

    dao = new EventLogDaoImpl(dataSource);
  }

  @Test
  public void findById() throws Exception {
    final long id = 1;
    EventLog eventLog = dao.findById(id);

    System.out.println(eventLog);
  }

  @Test
  public void save() throws Exception {
    final long defaultId = 99;
    final long postBoxId = 1;
    final Event event = Event.REGISTRATION;
    final String state = "success";

    final EventLog expectedEventLog = new EventLog(defaultId, postBoxId, event, state);

    dao.save(expectedEventLog);
/*
    final EventLog actualEventLog = dao.findById(defaultId);

    Assert.assertEquals(expectedEventLog.getPostBoxId(), actualEventLog.getPostBoxId());
    Assert.assertEquals(expectedEventLog.getEvent(), actualEventLog.getEvent());
    Assert.assertEquals(expectedEventLog.getState(), actualEventLog.getState());*/
  }
}