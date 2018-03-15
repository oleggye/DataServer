package by.bsac.tcs.server.process.parser.impl;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import by.bsac.tcs.server.process.parser.impl.parser.ParserFactory;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomProtocolParser implements ProtocolParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomProtocolParser.class);
  private static final ParserFactory factory = ParserFactory.getInstance();

  @Override
  public Request parse(final Socket clientSocket) throws ProtocolParseException {
    LOGGER.info("Parse the client request....");
    String userInput = readUserInput(clientSocket);

    LOGGER.info("User input: {}", userInput);

    return parseUserInput(userInput);
  }

  private String readUserInput(final Socket clientSocket) throws ProtocolParseException {
    try (BufferedReader input = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream(),
            StandardCharsets.UTF_8))) {

      return input.readLine();

    } catch (IOException e) {
      final String errorMessage = "Can't read client's data";
      LOGGER.error(errorMessage, e);
      throw new ProtocolParseException(errorMessage, e);
    }
  }

  private Request parseUserInput(final String userInput) throws ProtocolParseException {
    Method method = validateUserInputAndTakeMethod(userInput);
    RequestParser parser = factory.getProtocolParser(method);
    return parser.parse(userInput);
  }

  private Method validateUserInputAndTakeMethod(String userInput) throws ProtocolParseException {
    final Optional<Method> protocolMethod = Arrays.stream(Method.values())
        .filter(e -> e.getPattern().matcher(userInput).find())
        .findFirst();

    return protocolMethod
        .orElseThrow(() -> new ProtocolParseException("Request data is incorrect: " + userInput));
  }
}
