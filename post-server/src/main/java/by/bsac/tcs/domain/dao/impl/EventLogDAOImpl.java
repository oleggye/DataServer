package by.bsac.tcs.domain.dao.impl;

import by.bsac.tcs.domain.dao.EventLogDAO;
import by.bsac.tcs.domain.dao.exception.DAOException;
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

public class EventLogDAOImpl implements EventLogDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventLogDAOImpl.class);

  private static final String SELECT_BY_ID_SQL = "select id, id_post_box, id_event, state  from event_log where id = ?";
  private static final String INSERT_SQL = "insert into event_log (id_post_box, id_event, state) values (?,?,?)";

  private DataSource dataSource;
  private static final ResultSetHandlerFactory HANDLER_FACTORY = ResultSetHandlerFactory
      .getInstance();

  public EventLogDAOImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public EventLog findById(long id) throws DAOException {
    LOGGER.info("findById: {}", id);

    try (Connection connection = dataSource
        .getConnection(); PreparedStatement preparedStatement = connection
        .prepareStatement(SELECT_BY_ID_SQL)) {

      preparedStatement.setLong(1, id);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        ResultSetHandler handler = HANDLER_FACTORY.getHandler(EventLog.class);
        return (EventLog) handler.handle(resultSet);
      }
    } catch (SQLException e) {
      final String message = "An exception occurred while findById";
      LOGGER.error(message, e);
      throw new DAOException(message, e);
    }
  }

  @Override
  public void save(EventLog eventLog) throws DAOException {
    LOGGER.info("save: {}", eventLog);

    try (Connection connection = dataSource
        .getConnection(); PreparedStatement preparedStatement = connection
        .prepareStatement(INSERT_SQL)) {

      preparedStatement.setInt(1, (int) eventLog.getPostBoxId());
      preparedStatement.setInt(2, eventLog.getEvent().getEventId());
      preparedStatement.setString(3, eventLog.getState());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      final String message = "An exception occurred while save";
      LOGGER.error(message, e);
      throw new DAOException(message, e);
    }
  }
}
