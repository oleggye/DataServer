package by.bsac.tcs.server.request.parser;

import by.bsac.tcs.server.request.Request;
import java.net.Socket;

public interface ProtocolParser {

  Request parse(Socket clientSocket) throws ProtocolParseException;
}
