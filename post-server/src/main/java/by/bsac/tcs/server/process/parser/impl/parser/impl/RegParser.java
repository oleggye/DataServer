package by.bsac.tcs.server.process.parser.impl.parser.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.model.RequestBuilder;
import by.bsac.tcs.server.process.parser.impl.Method;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.util.regex.Pattern;

public class RegParser implements RequestParser {

  private final Method reg;

  public RegParser(Method reg) {
    this.reg = reg;
  }

  @Override
  public Request parse(String requestString) {
    Pattern pattern = reg.getPattern();
    String id = pattern.matcher(requestString).group(1);

    return new RequestBuilder()
        .setParam("method", reg.toString())
        .setParam("id", id)
        .build();
  }
}
