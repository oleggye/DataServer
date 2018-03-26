package by.bsac.tcs.domain.dao.util.pool;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectionPropertiesBundle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPropertiesBundle.class);

  private static final String FILE_NAME = "db";
  private static final String URL_KEY = "db.url";
  private static final String USER_KEY = "db.user";
  private static final String PASS_KEY = "db.password";
  private static final String MIN_IDLE_KEY = "db.minIdle";
  private static final String MAX_IDLE_KEY = "db.minIdle";
  private static final String MAX_OPS_KEY = "db.maxOPS";

  private final ResourceBundle bundle;

  private ConnectionPropertiesBundle() {
    bundle = ResourceBundle.getBundle(FILE_NAME);
  }

  public static ConnectionPropertiesBundle getInstance() {
    return SingletonHolder.getInstance();
  }

  /**
   * Return an Optional String property by key
   */
  public Optional<String> getProperty(String key) {
    try {
      return Optional.of(bundle.getString(key));
    } catch (MissingResourceException e) {
      LOGGER.warn("No value for key {}, so default value is using", key);
    }
    return Optional.empty();
  }

  /**
   * Return an Optional Integer property by key
   */
  public Optional<Integer> getIntProperty(String key) {
    try {
      String stringValue = bundle.getString(key);
      Integer intValue = Integer.parseInt(stringValue);
      return Optional.of(intValue);

    } catch (MissingResourceException e) {
      LOGGER.warn("No value for key {}, so default value is using", key);
    } catch (NumberFormatException e) {
      LOGGER.warn("Illegal numeric key format {}, so default value is using", key);
    }
    return Optional.empty();
  }

  public Optional<String> getUrl() {
    return getProperty(URL_KEY);
  }

  public Optional<String> getUser() {
    return getProperty(USER_KEY);
  }

  public Optional<String> getPassword() {
    return getProperty(PASS_KEY);
  }

  public Optional<Integer> getMinIdle() {
    return getIntProperty(MIN_IDLE_KEY);
  }

  public Optional<Integer> getMaxIdle() {
    return getIntProperty(MAX_IDLE_KEY);
  }

  public Optional<Integer> getMaxOps() {
    return getIntProperty(MAX_OPS_KEY);
  }

  private static class SingletonHolder {

    private static final ConnectionPropertiesBundle INSTANCE = new ConnectionPropertiesBundle();

    private SingletonHolder() {
    }

    public static ConnectionPropertiesBundle getInstance() {
      return INSTANCE;
    }
  }

}
