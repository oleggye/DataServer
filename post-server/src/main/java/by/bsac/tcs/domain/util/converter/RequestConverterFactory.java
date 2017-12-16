package by.bsac.tcs.domain.util.converter;

import by.bsac.tcs.domain.model.EventLog;
import by.bsac.tcs.domain.util.converter.impl.RequestToEventLogConverter;

public class RequestConverterFactory {

  private static final RequestToEventLogConverter requestToEventLogConverter = new RequestToEventLogConverter();

  private RequestConverterFactory() {
  }

  public static RequestConverterFactory getInstance() {
    return SingletoneHolder.getInstance();
  }

  public <T> RequestConverter<T> getConverter(Class<T> clazz) {
    if (EventLog.class.equals(clazz)) {
      return requestToEventLogConverter;
    }
    final String message = String.format("No converter for class %s", clazz);
    throw new IllegalArgumentException(message);
  }

  private static class SingletoneHolder {

    private static final RequestConverterFactory INSTANCE = new RequestConverterFactory();

    public static RequestConverterFactory getInstance() {
      return INSTANCE;
    }
  }
}
