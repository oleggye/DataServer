package by.bsac.tcs.server.process.parser.impl;

import by.bsac.tcs.server.process.parser.impl.util.ProtocolPropertiesLoader;
import java.util.regex.Pattern;

public enum Method {
  REG, LIST, WITHDRAWN, EMPTY, KEEP_ALIVE;

  private final Pattern pattern;

  Method() {
    String name = this.name();

    ProtocolPropertiesLoader loader = ProtocolPropertiesLoader.getInstance();
    String regexp = loader.getMethodsRegexpProperty(name);
    pattern = Pattern.compile(regexp);
  }

  public Pattern getPattern(){
    return pattern;
  }
}
