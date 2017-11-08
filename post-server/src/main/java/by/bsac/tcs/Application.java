package by.bsac.tcs;

import by.bsac.tcs.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    LOGGER.info("Starting by.bsac.tcs.server application......");
    testInArguments(args);

    final int portNumber = parsePortNumber(args[0]);

    Server server = new Server(portNumber);
    server.start();
  }

  private static void testInArguments(String[] args) {
    if (args.length < 1) {
      LOGGER.error("Wrong argument for port number");
      urgentlyStopServer();
    }
  }

  private static int parsePortNumber(String portArgument) {
    try {
      return Integer.parseInt(portArgument);
    } catch (NumberFormatException e) {
      LOGGER.error("Illegal port format: " + portArgument);
      urgentlyStopServer();
      //if exception, never be invoke
      return 0;
    }
  }

  private static void urgentlyStopServer() {
    LOGGER.error("Halt by.bsac.tcs.server!");
    System.exit(1);
  }
}
