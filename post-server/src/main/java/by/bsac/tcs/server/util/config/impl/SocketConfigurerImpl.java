package by.bsac.tcs.server.util.config.impl;

import by.bsac.tcs.server.util.config.SocketConfigurer;
import by.bsac.tcs.server.util.loader.ApplicationPropertiesLoader;
import java.net.Socket;
import java.net.SocketException;

public class SocketConfigurerImpl implements SocketConfigurer {

  private static final ApplicationPropertiesLoader APPLICATION_PROPERTIES_LOADER =
      ApplicationPropertiesLoader.getInstance();

  private final int socketTimeoutMilliseconds;
  private final boolean keepAlive;
  private final boolean reuseAddress;

  public SocketConfigurerImpl() {
    socketTimeoutMilliseconds = APPLICATION_PROPERTIES_LOADER.getSocketTimeout();
    keepAlive = APPLICATION_PROPERTIES_LOADER.isKeepAlive();
    reuseAddress = APPLICATION_PROPERTIES_LOADER.isReuseAddress();
  }

  public SocketConfigurerImpl(
      final int socketTimeoutMilliseconds,
      final boolean keepAlive,
      final boolean reuseAddress) {
    this.socketTimeoutMilliseconds = socketTimeoutMilliseconds;
    this.keepAlive = keepAlive;
    this.reuseAddress = reuseAddress;
  }

  public void configure(final Socket socket) throws SocketException {
    socket.setSoTimeout(socketTimeoutMilliseconds);
    socket.setKeepAlive(keepAlive);
    socket.setReuseAddress(reuseAddress);
  }
}
