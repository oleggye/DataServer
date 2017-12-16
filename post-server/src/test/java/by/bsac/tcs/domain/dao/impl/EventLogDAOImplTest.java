package by.bsac.tcs.domain.dao.impl;

import by.bsac.tcs.domain.dao.EventLogDAO;
import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;
import by.bsac.tcs.domain.model.EventLog;
import javax.sql.DataSource;
import org.junit.Test;

public class EventLogDAOImplTest {

  private final EventLogDAO dao;

  public EventLogDAOImplTest() {
    final DataSourceHolder holder = DataSourceHolder.getInstance();
    final DataSource dataSource = holder.getDataSource();

    dao = new EventLogDAOImpl(dataSource);
  }

  @Test
  public void findById() throws Exception {
    final long id = 1;
    EventLog eventLog = dao.findById(id);

    System.out.println(eventLog);
  }

  @Test
  public void save() throws Exception {
  }

}