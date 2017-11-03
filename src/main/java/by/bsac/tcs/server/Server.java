package by.bsac.tcs.server;

import by.bsac.tcs.server.manager.RequestManager;
import by.bsac.tcs.server.manager.RequestManagerDao;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

  private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

  private static final RequestManagerDao DAO = RequestManagerDao.getInstance();

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

  public void start() {
    //init manager
    LOGGER.info("Starting server......");

    LOGGER.info("Initialize manager......");
    manager.init();

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      LOGGER.info(String.format("Server is listening on %d port", port));

      Thread currentThread = Thread.currentThread();
//TODO: don't know if this is ok
      while (!currentThread.isInterrupted()) {
        Socket clientSocket = serverSocket.accept();

        LOGGER.info("Processing new request....");
        manager.manage(clientSocket);
      }
    } catch (IOException e) {
      LOGGER.error("An Exception occurred when trying to manage incoming request");
      LOGGER.error(e.getMessage());
    } finally {
      manager.shutdown();
    }
  }
}
