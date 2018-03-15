package by.bsac.tcs.server.process.parser;

import by.bsac.tcs.server.process.parser.impl.CustomProtocolParser;

public class ProtocolParserFactory {

  private static final ProtocolParser PARSER = new CustomProtocolParser();

  private ProtocolParserFactory() {
  }

  public static ProtocolParserFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public ProtocolParser getProtocolParser() {
    return PARSER;
  }

  private static class SingletonHolder {

    private static final ProtocolParserFactory INSTANCE = new ProtocolParserFactory();

    public static ProtocolParserFactory getInstance() {
      return INSTANCE;
    }
  }
}
