package by.bsac.tcs.server.util;

import by.bsac.tcs.server.util.loader.ApplicationPropertiesLoader;
import java.net.Socket;
import java.net.SocketException;

public final class SocketConfigurer {

  private static final ApplicationPropertiesLoader APPLICATION_PROPERTIES_LOADER =
      ApplicationPropertiesLoader.getInstance();

  private final int socketTimeoutMilliseconds;
  private final boolean keepAlive;
  private final boolean reuseAddress;

  public static SocketConfigurer getInstance() {
    return SingletonHolder.getInstance();
  }

  private SocketConfigurer() {
    socketTimeoutMilliseconds = APPLICATION_PROPERTIES_LOADER.getSocketTimeout();
    keepAlive = APPLICATION_PROPERTIES_LOADER.isKeepAlive();
    reuseAddress = APPLICATION_PROPERTIES_LOADER.isReuseAddress();
  }

  public void configure(final Socket socket) throws SocketException {
    socket.setSoTimeout(socketTimeoutMilliseconds);
    socket.setKeepAlive(keepAlive);
    socket.setReuseAddress(reuseAddress);
  }

  private static class SingletonHolder {

    private static final SocketConfigurer INSTANCE =
        new SocketConfigurer();

    static SocketConfigurer getInstance() {
      return INSTANCE;
    }
  }
}
