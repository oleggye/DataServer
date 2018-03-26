package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.manager.RequestManager;
import by.bsac.tcs.server.manager.exception.RequestManagerException;
import by.bsac.tcs.server.util.loader.ApplicationPropertiesLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRequestManager implements RequestManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestManager.class);
  private static final ApplicationPropertiesLoader APPLICATION_PROPERTIES_LOADER =
      ApplicationPropertiesLoader.getInstance();

  private final long poolTimeoutSeconds;
  private final int threadCount;

  protected ExecutorService pool;

  protected AbstractRequestManager() {
    poolTimeoutSeconds = APPLICATION_PROPERTIES_LOADER.getPoolShutDownTimeout();
    threadCount = APPLICATION_PROPERTIES_LOADER.getPoolThreadCount();
  }

  /**
   * Method initializes pool which process user's requests.
   */
  public void init() {
    pool = Executors.newFixedThreadPool(threadCount);
  }

  /**
   * Method closes pool which process user's requests.
   */
  public void shutdown() {
    LOGGER.info("Try to shutdown request manager....");
    if (pool != null && !pool.isTerminated()) {
      pool.shutdown();

      try {
        pool.awaitTermination(poolTimeoutSeconds, TimeUnit.SECONDS);

      } catch (InterruptedException e) {

        final String errorMessage = "An error occurred while shutting down the pool!";
        LOGGER.error(errorMessage, e);
        throw new RequestManagerException(errorMessage, e);

      } finally {
        pool.shutdownNow();
      }
    } else {
      throw new IllegalStateException("Illegal manager quantity exception");
    }
    LOGGER.info("Request manager is successfully shutdown....");
  }
}
