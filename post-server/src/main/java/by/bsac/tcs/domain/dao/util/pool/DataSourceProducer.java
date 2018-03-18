package by.bsac.tcs.domain.dao.util.pool;

import by.bsac.tcs.domain.dao.util.schema.SchemaUtil;
import java.lang.reflect.Proxy;
import java.util.Objects;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataSourceProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceProducer.class);

  private static final String DEFAULT_URI = "jdbc:h2:~/test";
  private static final String DEFAULT_USERNAME = "sa";
  private static final String DEFAULT_PASSWORD = "";
  private static final int DEFAULT_MIN_IDLE = 5;
  private static final int DEFAULT_MAX_IDLE = 10;
  private static final int DEFAULT_MAX_OPS = 20;

  private final BasicDataSource dataSource;
  private DataSource proxyDataSource;

  private DataSourceProducer() {
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


  public static DataSourceProducer getInstance() {
    return SingletonHolder.getInstance();
  }

  public DataSource getDataSource() {
    if (Objects.equals(DEFAULT_URI, dataSource.getUrl())) {
      SchemaUtil.getInstance().prepareSchema(dataSource);
    }
    return proxyDataSource;
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

  private static class SingletonHolder {

    private static final DataSourceProducer INSTANCE = new DataSourceProducer();

    private SingletonHolder() {
    }

    public static DataSourceProducer getInstance() {
      return INSTANCE;
    }
  }
}
