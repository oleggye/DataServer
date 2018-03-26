package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegParser implements RequestParser {

  private final Method reg;

  public RegParser(Method reg) {
    this.reg = reg;
  }

  @Override
  public Request parse(String requestString) {
    Pattern pattern = reg.getPattern();
    Matcher matcher = pattern.matcher(requestString);

    if (!matcher.find()) {
      throw new IllegalArgumentException("Wrong requestString: " + requestString);
    }

    String id = matcher.group(1);
    return new RequestBuilder()
        .method(reg)
        .id(id)
        .build();
  }
}
