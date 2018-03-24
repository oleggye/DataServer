package by.bsac.tcs.server.process.parser.impl;

import by.bsac.tcs.server.util.loader.ProtocolPropertiesLoader;
import java.util.regex.Pattern;

public enum Method {
  REG, LETTER, WITHDRAWN, EMPTY, KEEP_ALIVE;

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
