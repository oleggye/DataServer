package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GenericRequestParser implements RequestParser {

  private final Method method;

  protected GenericRequestParser(final Method method) {
    this.method = method;
  }

  public Request parse(final String requestString) {
    Pattern pattern = method.getPattern();

    Matcher matcher = pattern.matcher(requestString);

    if (!matcher.find()) {
      throw new IllegalArgumentException("Wrong requestString: " + requestString);
    }

    final RequestBuilder requestBuilder = new RequestBuilder().method(method);

    return populateRequestBuilder(requestBuilder, matcher).build();
  }

  protected abstract RequestBuilder populateRequestBuilder(
      final RequestBuilder requestBuilder,
      final Matcher matcher);
}
