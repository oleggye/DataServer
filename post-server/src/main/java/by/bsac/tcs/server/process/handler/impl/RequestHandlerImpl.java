package by.bsac.tcs.server.process.handler.impl;

import by.bsac.tcs.domain.controller.Controller;
import by.bsac.tcs.domain.controller.ControllerFactory;
import by.bsac.tcs.domain.controller.exception.ControllerException;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.handler.RequestHandler;
import by.bsac.tcs.server.process.handler.exception.RequestHandlerException;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.ProtocolParserFactory;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandlerImpl implements RequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerImpl.class);

  private static final ProtocolParserFactory DAO = ProtocolParserFactory.getInstance();
  private static final ControllerFactory CONTROLLER_FACTORY = ControllerFactory.getInstance();

  private final Socket clientSocket;
  private final ProtocolParser parser;
  private final Controller controller;

  public RequestHandlerImpl(Socket clientSocket) {
    this.clientSocket = clientSocket;
    this.parser = DAO.getProtocolParser();
    this.controller = CONTROLLER_FACTORY.getController();
  }

  public RequestHandlerImpl(Socket clientSocket, ProtocolParser parser, Controller controller) {
    this.clientSocket = clientSocket;
    this.parser = parser;
    this.controller = controller;
  }

  @Override
  public void run() {
    try {
      Request request = parser.parse(clientSocket);
      controller.process(request);
      LOGGER.info("Request was processed");
    } catch (ProtocolParseException | ControllerException e) {
      String message = "Can't manage client process";
      LOGGER.error(message, e);
      throw new RequestHandlerException(message, e);
    }
  }
}
