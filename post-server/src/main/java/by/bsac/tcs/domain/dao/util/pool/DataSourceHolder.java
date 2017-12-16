package by.bsac.tcs.domain.dao.util.pool;

import java.lang.reflect.Proxy;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceHolder {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceHolder.class);

  private final DataSource dataSource;
  private final DataSource proxyDataSource;

  private DataSourceHolder() {
    final ConnectionPropertiesBundle bundle = ConnectionPropertiesBundle.getInstance();
    final BasicDataSource dataSource = new BasicDataSource();

    dataSource.setUrl(bundle.getUrl());
    dataSource.setUsername(bundle.getUser());
    dataSource.setPassword(bundle.getPassword());
    dataSource.setMinIdle(5);
    dataSource.setMaxIdle(10);
    dataSource.setMaxOpenPreparedStatements(100);

    this.dataSource = dataSource;

    Class<?> clazz = dataSource.getClass();

    proxyDataSource = (DataSource) Proxy
        .newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), ((proxy, method, args) ->
        {
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
