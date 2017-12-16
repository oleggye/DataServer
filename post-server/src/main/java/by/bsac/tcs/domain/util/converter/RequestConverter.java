package by.bsac.tcs.domain.util.converter;

import by.bsac.tcs.server.model.Request;

public interface RequestConverter<T> {

  T convert(Request request);
}
