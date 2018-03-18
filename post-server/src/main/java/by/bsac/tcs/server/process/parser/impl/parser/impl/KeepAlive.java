package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.Method;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeepAlive implements RequestParser {

  private final Method method;

  public KeepAlive(Method method) {
    this.method = method;
  }

  @Override
  public Request parse(String requestString) {
    Pattern pattern = method.getPattern();
    Matcher matcher = pattern.matcher(requestString);

    if (!matcher.find()) {
      throw new IllegalArgumentException("Wrong requestString: " + requestString);
    }

    String id = matcher.group(1);
    String letterCount = matcher.group(2);
    String epochTime = matcher.group(3);
    return new RequestBuilder()
        .method(method)
        .id(id)
        .lettersCount(letterCount)
        .time(epochTime)
        .build();
  }
}
