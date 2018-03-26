package by.bsac.tcs.server.process.parser.impl;

import static org.apache.commons.lang3.StringUtils.isBlank;

import by.bsac.tcs.server.model.Method;
import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import by.bsac.tcs.server.process.parser.impl.parser.ParserFactory;
import by.bsac.tcs.server.process.parser.impl.parser.RequestParser;
import by.bsac.tcs.server.util.loader.ApplicationPropertiesLoader;
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
  private static final ApplicationPropertiesLoader APPLICATION_PROPERTIES_LOADER =
      ApplicationPropertiesLoader.getInstance();

  private static final ParserFactory factory = ParserFactory.getInstance();

  private final int requestMaxLength;

  public CustomProtocolParser() {
    requestMaxLength = APPLICATION_PROPERTIES_LOADER.getRequestMaxLength();
  }

  @Override
  public Request parse(final Socket clientSocket) throws ProtocolParseException {
    LOGGER.info("Parse the client request....");
    String userInput = readUserInput(clientSocket);

    LOGGER.info("User input: {}", userInput);

    return parseUserInput(userInput);
  }

  /**
   * It's important that protocol sends <strong>\n</strong> as end of line flag , but BufferedReader
   * uses it for its purpose and then removes!!! Important: we don't need to close the socket here,
   * because it'll closed after response is written
   */
  private String readUserInput(final Socket clientSocket) throws ProtocolParseException {
    try {
      BufferedReader input = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream(),
              StandardCharsets.UTF_8));

      final String userInput = input.readLine();
      checkUserInput(userInput);

      return userInput;
    } catch (IOException e) {
      final String errorMessage = "Can't read client's data";
      LOGGER.error(errorMessage, e);
      throw new ProtocolParseException(errorMessage, e);
    }
  }

  private void checkUserInput(final String userInput) throws ProtocolParseException {
    if (isBlank(userInput)
        || userInput.length() > requestMaxLength) {
      final String errorMessage = "Request is incorrect!";
      LOGGER.error(errorMessage);
      throw new ProtocolParseException(errorMessage);
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
