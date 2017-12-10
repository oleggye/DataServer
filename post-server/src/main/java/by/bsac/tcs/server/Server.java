package by.bsac.tcs.server;

import by.bsac.tcs.server.manager.RequestManager;
import by.bsac.tcs.server.manager.RequestManagerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom TCP server
 */
public class Server {

  private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

  private static final RequestManagerFactory DAO = RequestManagerFactory.getInstance();

  private final int port;
  private final RequestManager manager;

  public Server(int port) {
    this.port = port;
    manager = DAO.getManager();
  }

  public Server(int port, RequestManager manager) {
    this.port = port;
    this.manager = manager;
  }

  /**
   * Start server
   */
  public void start() {
    //init manager
    LOGGER.info("Starting by.bsac.tcs.server......");

    LOGGER.info("Initialize manager......");
    manager.init();

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      LOGGER.info("Server is listening on {} port", port);

      Thread currentThread = Thread.currentThread();
      //TODO: don't know if this is ok
      while (!currentThread.isInterrupted()) {
        Socket clientSocket = serverSocket.accept();

        LOGGER.info("Processing new process....");
        manager.manage(clientSocket);
      }
    } catch (IOException e) {
      LOGGER.error("An Exception occurred when trying to manage incoming process");
      LOGGER.error(e.getMessage());
    } finally {
      manager.shutdown();
    }
  }
}
