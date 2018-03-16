package by.bsac.tcs;

import by.bsac.tcs.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
  private static final int DEFAULT_PORT = 8888;

  public static void main(String[] args) {
    LOGGER.info("Starting by.bsac.tcs.server application......");

    final int portNumber = isPortArgumentSet(args) ? parsePortNumber(args[0]) : DEFAULT_PORT;

    Server server = new Server(portNumber);
    server.start();
  }

  private static boolean isPortArgumentSet(String... portArgument) {
    return portArgument.length >= 1;
  }

  private static int parsePortNumber(String portArgument) {
    try {
      return Integer.parseInt(portArgument);
    } catch (NumberFormatException e) {
      LOGGER.error("Illegal port format: " + portArgument);
      urgentlyStopServer();
      //if exception, never be invoked
      throw e;
    }
  }

  private static void urgentlyStopServer() {
    LOGGER.error("Halt by.bsac.tcs.server!");
    System.exit(1);
  }
}
