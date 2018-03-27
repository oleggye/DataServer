package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.RequestBuilder;
import java.util.regex.Matcher;

public class RegParser extends GenericRequestParser {

  public RegParser(Method method) {
    super(method);
  }

  @Override
  protected RequestBuilder populateRequestBuilder(
      RequestBuilder requestBuilder,
      Matcher matcher) {
    String id = matcher.group(1);
    return requestBuilder
        .id(id);
  }
}
