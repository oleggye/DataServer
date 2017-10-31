package by.bsac.tcs.server.request.parser;

import by.bsac.tcs.server.request.parser.impl.CustomProtocolParser;

public class ProtocolParserDAO {

    private static final ProtocolParserDAO INSTANCE = new ProtocolParserDAO();

    private static final ProtocolParser parser = new CustomProtocolParser();

    private ProtocolParserDAO() {
    }

    public static final ProtocolParserDAO getInstance() {
        return INSTANCE;
    }

    public ProtocolParser getProtocolParser() {
        return parser;
    }
}
