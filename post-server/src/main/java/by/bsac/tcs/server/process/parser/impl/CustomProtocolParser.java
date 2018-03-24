package by.bsac.tcs.server.process.parser.impl;

import static java.util.Objects.isNull;

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
  private static final int MAX_REQUEST_LENGTH = 30;

  @Override
  public Request parse(final Socket clientSocket) throws ProtocolParseException {
    LOGGER.info("Parse the client request....");
    String userInput = readUserInput(clientSocket);

    LOGGER.info("User input: {}", userInput);

    return parseUserInput(userInput);
  }

  /**
   * It's important that protocol sends \n as end of line flag , but BufferedReader uses it for its
   * purpose and then removes!!!
   *
   * Important: we don't need to close the socket here, because it'll closed after response is
   * written
   */
  private String readUserInput(final Socket clientSocket) throws ProtocolParseException {
    try {
      BufferedReader input = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream(),
              StandardCharsets.UTF_8));

      final String userInput = input.readLine();
      checkForExcessLength(userInput);

      return userInput;
    } catch (IOException e) {
      final String errorMessage = "Can't read client's data";
      LOGGER.error(errorMessage, e);
      throw new ProtocolParseException(errorMessage, e);
    }
  }

  private void checkForExcessLength(final String userInput) {
    if (isNull(userInput) ||
        userInput.length() > MAX_REQUEST_LENGTH) {
      final String errorMessage = "Request is null or its length exceeded!";
      LOGGER.error(errorMessage);
      throw new IllegalArgumentException(errorMessage);
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
