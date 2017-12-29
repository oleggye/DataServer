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
    final BasicDataSource basicDataSource = new BasicDataSource();

    basicDataSource.setUrl(bundle.getUrl());
    basicDataSource.setUsername(bundle.getUser());
    basicDataSource.setPassword(bundle.getPassword());
    basicDataSource.setMinIdle(5);
    basicDataSource.setMaxIdle(10);
    basicDataSource.setMaxOpenPreparedStatements(100);

    this.dataSource = basicDataSource;

    Class<?> clazz = basicDataSource.getClass();

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
