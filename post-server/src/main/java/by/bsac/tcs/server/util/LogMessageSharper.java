package by.bsac.tcs.server.util;

import java.net.InetAddress;
import java.net.Socket;

public final class LogMessageSharper {

  private static final String INCOMING_REQUEST_MESSAGE_PATTERN
      = "new incoming request from %s:%d";

  private LogMessageSharper() {
  }

  public static String formIncommingUserLogMessage(final Socket socket) {
    InetAddress address = socket.getLocalAddress();
    String hostAddress = address.getHostAddress();
    int port = socket.getLocalPort();
    return String.format(INCOMING_REQUEST_MESSAGE_PATTERN, hostAddress, port);
  }
}
