package by.bsac.tcs.domain.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.dao.util.handler.ResultSetHandler;
import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventLogDaoImplTest {

  @InjectMocks
  private EventLogDaoImpl dao;

  @Mock
  private DataSource dataSource;

  @Mock
  private ResultSetHandler handler;

  @Mock
  private Connection connection;

  @Mock
  private PreparedStatement preparedStatement;

  @Mock
  private ResultSet resultSet;

  @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
  @Before
  public void setUp() throws SQLException {
    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    doNothing().when(preparedStatement).close();
  }

  @SuppressFBWarnings("ODR_OPEN_DATABASE_RESOURCE")
  @After
  public void tearDown() throws SQLException {
    verify(dataSource).getConnection();
    verify(preparedStatement).close();
  }

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED")
  @Test
  public void findById() throws DaoException, SQLException {
    EventLog expectedEventLog = new EventLog(0, null, 0,0);

    doNothing().when(preparedStatement).setLong(any(Integer.class), any(Long.class));
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(handler.handle(any(ResultSet.class))).thenReturn(expectedEventLog);

    final long id = 222850;
    EventLog actualEventLog = dao.findById(id);

    assertEquals(expectedEventLog, actualEventLog);
    verify(preparedStatement).executeQuery();
    verify(preparedStatement).setLong(1, id);
    verify(handler).handle(resultSet);
  }

  @Test
  public void save() throws DaoException, SQLException {
    long expectedId = 99;
    final long postBoxId = 222850;
    final Event event = Event.REGISTRATION;
    final int quantity = 7;
    final long epochTime = 1234567894;

    final EventLog expectedEventLog = new EventLog(postBoxId, event, quantity, epochTime);

    prepareForSave(expectedId);

    long actualIndex = dao.save(expectedEventLog);

    assertEquals(expectedId, actualIndex);
    verifyForSave(expectedEventLog);
  }

  private void prepareForSave(long expectedId) throws SQLException {
    doNothing().when(preparedStatement).setInt(any(Integer.class), any(Integer.class));
    doNothing().when(preparedStatement).setLong(any(Integer.class), any(Long.class));
    //this methods are invoked behind the scene, but I need to verify all interactions with the mock

    when(preparedStatement.executeUpdate()).thenReturn(1);
    when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(resultSet.getLong(any(Integer.class))).thenReturn(expectedId);
  }

  private void verifyForSave(final EventLog actualEventLog) throws SQLException {
    verify(preparedStatement).setLong(1, actualEventLog.getPostBoxId());
    verify(preparedStatement).setInt(2, actualEventLog.getEvent().getEventId());
    verify(preparedStatement).setInt(3, actualEventLog.getQuantity());
    verify(preparedStatement).setLong(4, actualEventLog.getEpochTime());

    verify(preparedStatement).executeUpdate();
    verify(preparedStatement).getGeneratedKeys();

    verify(resultSet).next();
    verify(resultSet).getLong(1);
  }
}