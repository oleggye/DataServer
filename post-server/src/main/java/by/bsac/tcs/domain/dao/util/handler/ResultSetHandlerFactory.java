package by.bsac.tcs.domain.dao.util.handler;

import by.bsac.tcs.domain.dao.util.handler.impl.EventLogResultSetHandler;
import by.bsac.tcs.domain.model.EventLog;

public class ResultSetHandlerFactory {

  private final EventLogResultSetHandler eventLogResultSetHandler;

  private ResultSetHandlerFactory() {
    eventLogResultSetHandler = new EventLogResultSetHandler();
  }

  public static ResultSetHandlerFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public ResultSetHandler getHandler(Class<?> clazz) {

    if (EventLog.class.equals(clazz)) {
      return eventLogResultSetHandler;
    }
    throw new IllegalArgumentException(String.format("no such handler for class %s", clazz));
  }

  private static class SingletonHolder {

    private static final ResultSetHandlerFactory INSTANCE = new ResultSetHandlerFactory();

    private SingletonHolder() {
    }

    public static ResultSetHandlerFactory getInstance() {
      return INSTANCE;
    }
  }

}
