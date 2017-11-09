package by.bsac.tcs.server.process.parser;

import by.bsac.tcs.server.process.parser.impl.CustomProtocolParser;

public class ProtocolParserDAO {

  private static final ProtocolParserDAO INSTANCE = new ProtocolParserDAO();

  private static final ProtocolParser parser = new CustomProtocolParser();

  private ProtocolParserDAO() {
  }

  public static ProtocolParserDAO getInstance() {
    return INSTANCE;
  }

  public ProtocolParser getProtocolParser() {
    return parser;
  }
}
