package by.bsac.tcs.server.util.model;

import java.util.regex.Pattern;

public final class RequestBuilder {

  private String requestData;
  private String serverAddress;
  private int port;

  public RequestBuilder requestData(String requestData) {
    this.requestData = requestData;
    return this;
  }

  public RequestBuilder serverAddress(String serverAddress) {
    this.serverAddress = serverAddress;
    return this;
  }

  public RequestBuilder serverPort(int port) {
    this.port = port;
    return this;
  }

  public Request build() {
    Validator.validatePort(port);
    Validator.validateServerAddress(serverAddress);
    Validator.validateRequestData(requestData);

    return new Request(serverAddress, port, requestData);
  }

  private static final class Validator {

    private static final String Ipv4Regexp = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b";
    private static final Pattern ipv4Patter = Pattern.compile(Ipv4Regexp);

    static void validatePort(final int port) {
      if (port == 0) {
        final String message = String.format("Port %d is incorrect", port);
        throw new IllegalStateException(message);
      }
    }

    static void validateServerAddress(final String serverAddress) {

      if (!ipv4Patter.matcher(serverAddress).find()) {
        final String message = String.format("Server address %s is incorrect", serverAddress);
        throw new IllegalStateException(message);
      }
    }

    static void validateRequestData(final String requestData) {
      if (requestData == null) {
        throw new IllegalStateException("RequestData is null");
      }
    }
  }
}