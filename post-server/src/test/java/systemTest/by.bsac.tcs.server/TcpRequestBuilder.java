package by.bsac.tcs.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpRequestBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(TcpRequestBuilder.class);

  private String requestData;
  private String serverAddress;
  private int port;

  public TcpRequestBuilder requestData(String requestData) {
    this.requestData = requestData;
    return this;
  }

  public TcpRequestBuilder serverAddress(String serverAddress) {
    this.serverAddress = serverAddress;
    return this;
  }

  public TcpRequestBuilder serverPort(int port) {
    this.port = port;
    return this;
  }

  public void perform() {
    try (Socket socket = new Socket(serverAddress, port)) {
      try (OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(),
          StandardCharsets.UTF_8)) {
        out.append(requestData);
      }
    } catch (IOException e) {
      final String message = "An exception occurred while perform request";
      LOGGER.error(message, e);
      throw new RuntimeException(e);
    }
  }
}
