package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.RequestBuilder;
import java.util.regex.Matcher;

public class KeepAliveParser extends GenericRequestParser {

  public KeepAliveParser(Method method) {
    super(method);
  }

  @Override
  protected RequestBuilder populateRequestBuilder(
      RequestBuilder requestBuilder,
      Matcher matcher) {
    String id = matcher.group(1);
    String letterCount = matcher.group(2);
    String epochTime = matcher.group(3);
    return requestBuilder
        .id(id)
        .lettersCount(letterCount)
        .time(epochTime);
  }
}
