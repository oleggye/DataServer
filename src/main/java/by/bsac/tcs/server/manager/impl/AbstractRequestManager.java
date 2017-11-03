package by.bsac.tcs.server.manager.impl;

import by.bsac.tcs.server.manager.RequestManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRequestManager implements RequestManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestManager.class);

  private static final long TIME_OUT_SECONDS = 3;

  protected ExecutorService pool;

  /**
   * Method initializes pool which process user's requests.
   */
  public void init() {
    pool = Executors.newCachedThreadPool();
  }

  /**
   * Method closes pool which process user's requests.
   */
  public void shutdown() {
    if (pool != null && !pool.isTerminated()) {
      pool.shutdown();
      try {
        pool.awaitTermination(TIME_OUT_SECONDS, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        LOGGER.error("An error occurred while shutting down the pool!", e);
      } finally {
        pool.shutdownNow();
      }
    } else {
      throw new IllegalStateException("Illegal manager state exception");
    }
  }
}
