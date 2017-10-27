package by.bsac.tcs.server.request.parser;

import by.bsac.tcs.server.request.parser.impl.CustomProtocolParser;

public class ParserDAO {

    private static final ParserDAO INSTANCE = new ParserDAO();

    private static final ProtocolParser parser = new CustomProtocolParser();

    private ParserDAO() {
    }

    public static final ParserDAO getInstance() {
        return INSTANCE;
    }

    public ProtocolParser getProtocolParser() {
        return parser;
    }
}
