package by.bsac.tcs.server.util.model;

public final class Request {

  private String requestData;
  private String serverAddress;
  private int port;

  public Request(String serverAddress, int port, String requestData) {
    this.requestData = requestData;
    this.serverAddress = serverAddress;
    this.port = port;
  }

  public String getRequestData() {
    return requestData;
  }

  public String getServerAddress() {
    return serverAddress;
  }

  public int getPort() {
    return port;
  }
}
