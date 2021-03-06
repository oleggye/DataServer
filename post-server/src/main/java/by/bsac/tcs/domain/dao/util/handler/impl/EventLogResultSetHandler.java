package by.bsac.tcs.domain.dao.util.handler.impl;

import static org.apache.commons.lang3.StringUtils.isBlank;

import by.bsac.tcs.domain.dao.util.handler.ResultSetHandler;
import by.bsac.tcs.domain.model.Event;
import by.bsac.tcs.domain.model.EventLog;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventLogResultSetHandler implements ResultSetHandler<EventLog> {

  private static final String ID = "id";
  private static final String ID_POST_BOX = "id_post_box";
  private static final String ID_EVENT = "id_event";
  private static final String QUANTITY = "quantity";
  private static final String TIME = "time";

  @Override
  public EventLog handle(ResultSet resultSet) throws SQLException {
    EventLog eventLog = null;

    if (resultSet.next()) {
      final long id = resultSet.getLong(ID);
      final long idPostBox = resultSet.getLong(ID_POST_BOX);

      final int idEvent = resultSet.getInt(ID_EVENT);
      final Event event = Event.getEvent(idEvent);

      final String quantityString = resultSet.getString(QUANTITY);
      final int quantity = parseQuantity(quantityString);

      long epochTime = resultSet.getLong(TIME);

      eventLog = new EventLog(id, idPostBox, event, quantity, epochTime);
    }
    return eventLog;
  }

  private int parseQuantity(final String quantityString) {
    if (isBlank(quantityString)) {
      return 0;
    } else {
      return Integer.parseInt(quantityString);
    }
  }
}
