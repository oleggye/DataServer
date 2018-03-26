package by.bsac.tcs.server.util.config;

import by.bsac.tcs.server.util.config.impl.SocketConfigurerImpl;

public final class SocketConfigurerFactory {

  private static final by.bsac.tcs.server.util.config.SocketConfigurer SOCKET_CONFIGURER =
      new SocketConfigurerImpl();

  private SocketConfigurerFactory() {
  }

  public static SocketConfigurerFactory getInstance() {
    return SingletonHolder.getInstance();
  }

  public by.bsac.tcs.server.util.config.SocketConfigurer getSocketConfigurer() {
    return SOCKET_CONFIGURER;
  }

  private static class SingletonHolder {

    private static final SocketConfigurerFactory INSTANCE =
        new SocketConfigurerFactory();

    static SocketConfigurerFactory getInstance() {
      return INSTANCE;
    }
  }

}
