package by.bsac.tcs.domain.dao.util.pool;

import java.util.ResourceBundle;

public class ConnectionPropertiesBundle {

  private static final String FILE_NAME = "db";
  private static final String URL_KEY = "db.url";
  private static final String USER_KEY = "db.user";
  private static final String PASS_KEY = "db.password";

  private final ResourceBundle bundle;

  private ConnectionPropertiesBundle() {
    bundle = ResourceBundle.getBundle(FILE_NAME);
  }

  public static ConnectionPropertiesBundle getInstance() {
    return SingletonHolder.getInstance();
  }

  public String getProperty(String key) {
    return bundle.getString(key);
  }

  public String getUrl() {
    return getProperty(URL_KEY);
  }

  public String getUser() {
    return getProperty(USER_KEY);
  }

  public String getPassword() {
    return getProperty(PASS_KEY);
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
