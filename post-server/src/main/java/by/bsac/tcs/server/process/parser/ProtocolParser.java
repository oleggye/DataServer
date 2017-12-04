package by.bsac.tcs.server.process.parser;

import by.bsac.tcs.server.model.Request;
import java.net.Socket;

public interface ProtocolParser {

  Request parse(Socket clientSocket) throws ProtocolParseException;
}
