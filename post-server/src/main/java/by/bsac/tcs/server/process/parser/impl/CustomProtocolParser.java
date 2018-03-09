package by.bsac.tcs.server.process.parser.impl;

import static java.util.Objects.isNull;

import by.bsac.tcs.server.model.Request;
import by.bsac.tcs.server.process.parser.ProtocolParser;
import by.bsac.tcs.server.process.parser.exception.ProtocolParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomProtocolParser implements ProtocolParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomProtocolParser.class);

  private static final String DEFAULT_INPUT_STREAM_CHARACTER_ENCODING = "utf-8";

  private static final String EXIT_SYMBOL = "^";
  private static final String LINE_DELIMITER = ";";
  private static final String PARAM_DELIMITER = "=";

  @Override
  public Request parse(Socket clientSocket) throws ProtocolParseException {
    String requestData = pullRequestData(clientSocket);
    return parseRequestData(requestData);
  }

  //FIXME: Pay attention that this parser can wait infinite time
  private String pullRequestData(Socket clientSocket) throws ProtocolParseException {
    LOGGER.info("Parse the client request....");

    try (BufferedReader input = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream(),
            DEFAULT_INPUT_STREAM_CHARACTER_ENCODING))) {
      StringBuilder userRequestData = null;
      String userInput;
      //while ((userInput = input.readLine()) != null) {
      userInput = input.readLine();
      userInput = replaceAllInappropriateSymbols(userInput);
      LOGGER.info("User input: {}", userInput);

      if (isNull(userRequestData)) {
        userRequestData = new StringBuilder();
      }
      if (userInput.contains(EXIT_SYMBOL)) {
        userInput = userInput.substring(0, userInput.length() - 1);
        userRequestData.append(userInput);
        //break;
      }
      //userInput += LINE_DELIMITER;

      // userRequestData.append(userInput);
      // }

      return userRequestData != null ? userRequestData.toString() : "";

    } catch (IOException e) {
      final String errorMessage = "Can't read client's data";
      LOGGER.error(errorMessage, e);
      throw new ProtocolParseException(errorMessage, e);
    }
  }

  private Request parseRequestData(String requestData) {
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

  private String replaceAllInappropriateSymbols(String input) {
    return input.replaceAll("[^A-Za-z0-9=^;]", "");
  }
}
