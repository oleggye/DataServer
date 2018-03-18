package by.bsac.tcs.domain.dao.util.pool;

import java.lang.reflect.Proxy;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataSourceHolder {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceHolder.class);

  private final String DEFAULT_URI = "jdbc:h2:~/test";
  private final String DEFAULT_USERNAME = "sa";
  private final String DEFAULT_PASSWORD = "";
  private final int DEFAULT_MIN_IDLE = 5;
  private final int DEFAULT_MAX_IDLE = 10;
  private final int DEFAULT_MAX_OPS = 20;

  private final DataSource dataSource;
  private DataSource proxyDataSource;

  private DataSourceHolder() {
    final ConnectionPropertiesBundle bundle = ConnectionPropertiesBundle.getInstance();
    final BasicDataSource basicDataSource = new BasicDataSource();

    basicDataSource.setUrl(bundle.getUrl().orElse(DEFAULT_URI));
    basicDataSource.setUsername(bundle.getUser().orElse(DEFAULT_USERNAME));
    basicDataSource.setPassword(bundle.getPassword().orElse(DEFAULT_PASSWORD));
    basicDataSource.setMinIdle(bundle.getMinIdle().orElse(DEFAULT_MIN_IDLE));
    basicDataSource.setMaxIdle(bundle.getMaxIdle().orElse(DEFAULT_MAX_IDLE));
    basicDataSource.setMaxOpenPreparedStatements(bundle.getMaxOPS().orElse(DEFAULT_MAX_OPS));

    this.dataSource = basicDataSource;

    makeProxy();
  }

  private void makeProxy() {
    Class<?> clazz = dataSource.getClass();

    proxyDataSource = (DataSource) Proxy
        .newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), ((proxy, method, args) -> {
          final String message = method.getName();
          LOGGER.info("Invoke method {}", message);
          return method.invoke(this.dataSource, args);
        }
        ));
  }

  public static DataSourceHolder getInstance() {
    return SingletonHolder.getInstance();
  }

  public DataSource getDataSource() {
    return proxyDataSource;
  }

  private static class SingletonHolder {

    private static final DataSourceHolder INSTANCE = new DataSourceHolder();

    private SingletonHolder() {
    }

    public static DataSourceHolder getInstance() {
      return INSTANCE;
    }
  }
}
