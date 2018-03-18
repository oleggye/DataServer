package by.bsac.tcs.domain.dao.impl;

import by.bsac.tcs.domain.dao.EventLogDao;
import by.bsac.tcs.domain.dao.exception.DaoException;
import by.bsac.tcs.domain.dao.util.handler.ResultSetHandler;
import by.bsac.tcs.domain.dao.util.handler.ResultSetHandlerFactory;
import by.bsac.tcs.domain.model.EventLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogDaoImpl implements EventLogDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventLogDaoImpl.class);
  private static final ResultSetHandlerFactory HANDLER_FACTORY = ResultSetHandlerFactory
      .getInstance();

  private static final String SELECT_BY_ID_SQL =
      "select id, id_post_box, id_event, quantity, time  from event_log where id = ?";
  private static final String INSERT_SQL =
      "insert into event_log (id_post_box, id_event, quantity, time) values (?,?,?,?)";

  private final DataSource dataSource;
  private final ResultSetHandler handler;


  public EventLogDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
    this.handler = HANDLER_FACTORY.getHandler(EventLog.class);
  }

  public EventLogDaoImpl(DataSource dataSource, ResultSetHandler handler) {
    this.dataSource = dataSource;
    this.handler = handler;
  }

  @Override
  public EventLog findById(long id) throws DaoException {
    LOGGER.info("findById: {}", id);

    try (Connection connection = dataSource
        .getConnection(); PreparedStatement preparedStatement = connection
        .prepareStatement(SELECT_BY_ID_SQL)) {

      preparedStatement.setLong(1, id);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return (EventLog) handler.handle(resultSet);
      }
    } catch (SQLException e) {
      final String message = "An exception occurred while findById";
      LOGGER.error(message, e);
      throw new DaoException(message, e);
    }
  }

  @Override
  public long save(EventLog eventLog) throws DaoException {
    LOGGER.info("save: {}", eventLog);

    try (Connection connection = dataSource
        .getConnection(); PreparedStatement preparedStatement = connection
        .prepareStatement(INSERT_SQL)) {

      preparedStatement.setLong(1, (int) eventLog.getPostBoxId());
      preparedStatement.setInt(2, eventLog.getEvent().getEventId());
      preparedStatement.setInt(3, eventLog.getQuantity());
      preparedStatement.setLong(4, eventLog.getEpochTime());

      return getInsertedIndex(preparedStatement);

    } catch (SQLException e) {
      final String message = "An exception occurred while save";
      LOGGER.error(message, e);
      throw new DaoException(message, e);
    }
  }

  private long getInsertedIndex(final PreparedStatement preparedStatement) throws SQLException {
    int affectedRows = preparedStatement.executeUpdate();
    if (affectedRows == 0) {
      throw new SQLException("Save failed, no rows affected.");
    }
    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
      if (generatedKeys.next()) {
        return generatedKeys.getLong(1);
      } else {
        throw new SQLException("No ID obtained.");
      }
    }
  }
}
