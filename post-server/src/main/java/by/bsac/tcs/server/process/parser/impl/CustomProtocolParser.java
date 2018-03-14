package by.bsac.tcs.server.process.parser.impl;

import static java.util.Objects.isNull;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomProtocolParser implements ProtocolParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomProtocolParser.class);

  private static final String EXIT_SYMBOL = "^";
  private static final String LINE_DELIMITER = ";";
  private static final String PARAM_DELIMITER = "=";
  private static final String INAPPROPRIATE_SYMBOLS_REGEXP = "[^A-Za-z0-9=^;]";
  private static final Pattern INAPPROPRIATE_SYMBOLS_PATTERN = Pattern
      .compile(INAPPROPRIATE_SYMBOLS_REGEXP);

  @Override
  public Request parse(final Socket clientSocket) throws ProtocolParseException {
    //"^(([A-Za-z_]{4,10}:[0-9]{6,10}:[0-9]{10}\\n) | ([A-Za-z_]{4,10}:[0-9]{6,10}:[0-9]{1,250}:[0-9]{10}\\n))";
    String requestData = pullRequestData(clientSocket);
    return parseRequestData(requestData);
  }

  private String pullRequestData(final Socket clientSocket) throws ProtocolParseException {
    LOGGER.info("Parse the client request....");

    String userInput = readUserInput(clientSocket);
    LOGGER.info("User input: {}", userInput);

    checkUserInput(userInput);

    if (userInput.contains(EXIT_SYMBOL)) {
      return userInput.substring(0, userInput.length() - 1);
    }

    return userInput;
  }

  private String readUserInput(final Socket clientSocket) throws ProtocolParseException {
    try (BufferedReader input = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream(),
            StandardCharsets.UTF_8))) {

      String userInput = input.readLine();

      checkUserInput(userInput);

      return userInput;

    } catch (IOException e) {
      final String errorMessage = "Can't read client's data";
      LOGGER.error(errorMessage, e);
      throw new ProtocolParseException(errorMessage, e);
    }
  }

  private void checkUserInput(String userInput) throws ProtocolParseException {
    if (isNull(userInput)
        || userInput.isEmpty()
        || containsInappropriateSymbols(userInput)) {
      throw new ProtocolParseException("Request data is incorrect: " + userInput);
    }
  }

  private Request parseRequestData(final String requestData) {
    String[] lines = requestData.split(LINE_DELIMITER);
    Request request = new Request();

    for (String line : lines) {
      String[] param = line.split(PARAM_DELIMITER);

      if (param.length != 2) {
        throw new IllegalArgumentException("Wrong param value: " + Arrays.deepToString(param));
      }
      String key = param[0];
      String val = param[1];
      request.setRequestParam(key, val);
    }
    return request;
  }

  private boolean containsInappropriateSymbols(final String input) {
    return INAPPROPRIATE_SYMBOLS_PATTERN.matcher(input).find();
  }
}
