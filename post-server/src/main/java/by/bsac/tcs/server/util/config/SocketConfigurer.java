package by.bsac.tcs.server.util.config;

import java.net.Socket;
import java.net.SocketException;

public interface SocketConfigurer {

  void configure(final Socket socket) throws SocketException;
}
