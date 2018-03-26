package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmptyParser implements RequestParser {

  private final Method empty;

  public EmptyParser(Method empty) {
    this.empty = empty;
  }

  @Override
  public Request parse(String requestString) {
    Pattern pattern = empty.getPattern();
    Matcher matcher = pattern.matcher(requestString);

    if (!matcher.find()) {
      throw new IllegalArgumentException("Wrong requestString: " + requestString);
    }

    String id = matcher.group(1);
    String epochTime = matcher.group(2);
    return new RequestBuilder()
        .method(empty)
        .id(id)
        .time(epochTime)
        .build();
  }
}
