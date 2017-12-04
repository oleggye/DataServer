package by.bsac.tcs.server.process.handler.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.RequestHandlerException;
import by.bsac.tcs.server.process.parser.ProtocolParseException;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.ProtocolParserDAO;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandlerImpl implements RequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerImpl.class);

  private static final ProtocolParserDAO DAO = ProtocolParserDAO.getInstance();

  private final Socket clientSocket;
  private final ProtocolParser parser;

  public RequestHandlerImpl(Socket clientSocket) {
    this.clientSocket = clientSocket;
    this.parser = DAO.getProtocolParser();
  }

  public RequestHandlerImpl(Socket clientSocket, ProtocolParser parser) {
    this.clientSocket = clientSocket;
    this.parser = parser;
  }

  @Override
  public void run() {
    try {
      Request request = parser.parse(clientSocket);
      LOGGER.info("Request was processed");
    } catch (ProtocolParseException e) {
      String message = "Can't manage client process";
      LOGGER.error(message, e);
      throw new RequestHandlerException(message, e);
    }
  }
}
