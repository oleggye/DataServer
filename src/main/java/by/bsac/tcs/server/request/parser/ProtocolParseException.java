package by.bsac.tcs.server.request.parser;

public class ProtocolParseException extends Exception {

    public ProtocolParseException(String message) {
        super(message);
    }

    public ProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
