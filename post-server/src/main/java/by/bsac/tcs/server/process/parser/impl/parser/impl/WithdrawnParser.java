package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.Method;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WithdrawnParser implements RequestParser {

  private final Method withdrawn;

  public WithdrawnParser(Method withdrawn) {
    this.withdrawn = withdrawn;
  }

  @Override
  public Request parse(String requestString) {
    Pattern pattern = withdrawn.getPattern();
    Matcher matcher = pattern.matcher(requestString);

    String id = matcher.group(1);
    String epochTime = matcher.group(2);
    return new RequestBuilder()
        .setParam("method", withdrawn.toString())
        .setParam("id", id)
        .setParam("time", epochTime)
        .build();
  }
}
