package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.RequestBuilder;
import java.util.regex.Matcher;

public class WithdrawnParser extends GenericRequestParser {

  public WithdrawnParser(Method method) {
    super(method);
  }

  @Override
  protected RequestBuilder populateRequestBuilder(
      final RequestBuilder requestBuilder,
      final Matcher matcher) {
    String id = matcher.group(1);
    String epochTime = matcher.group(2);
    return requestBuilder
        .id(id)
        .time(epochTime);
  }
}
