package by.bsac.tcs.server.request.parser.impl;

import by.bsac.tcs.server.request.Request;
import by.bsac.tcs.server.request.parser.ProtocolParseException;
import by.bsac.tcs.server.request.parser.ProtocolParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

public class CustomProtocolParser implements ProtocolParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomProtocolParser.class);

    private static final String EXIT_SYMBOL = "^";
    private static final String LINE_DELIMITER = ";";
    private static final String PARAM_DELIMITER = "=";

    @Override
    public Request parse(Socket clientSocket) throws ProtocolParseException {
        String requestData = pullRequestData(clientSocket);
        return parseRequestData(requestData);
    }

    private String pullRequestData(Socket clientSocket) throws ProtocolParseException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            StringBuilder userRequestData = null;
            String userInput;
            while ((userInput = input.readLine()) != null) {
                userInput = replaceAllInappropriateSymbols(userInput);
                LOGGER.info("User input: " + userInput);

                if (Objects.isNull(userRequestData)) {
                    userRequestData = new StringBuilder();
                }
                if (userInput.contains(EXIT_SYMBOL)) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                    userRequestData.append(userInput);
                    break;
                }
                userInput += LINE_DELIMITER;

                userRequestData.append(userInput);
            }

            return userRequestData != null ? userRequestData.toString() : "";

        } catch (IOException e) {
            String errorMessage = "Can't read client's data";
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
                throw new IllegalArgumentException("Wrong param value: " + param);
            }
            String key = param[0];
            String val = param[1];
            request.setRequestParam(key, val);
        }
        return request;
    }

    private String replaceAllInappropriateSymbols(String input) {
        return input.replaceAll("[^A-Za-z0-9^]", "");
    }
}
