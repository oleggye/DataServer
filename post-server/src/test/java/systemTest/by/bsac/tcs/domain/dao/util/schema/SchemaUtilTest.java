package by.bsac.tcs.domain.dao.util.schema;

import by.bsac.tcs.domain.dao.util.pool.DataSourceHolder;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;

public class SchemaUtilTest {

  private static final SchemaUtil SCHEMA_UTIL = SchemaUtil.getInstance();
  private static final DataSourceHolder HOLDER = DataSourceHolder.getInstance();

  private DataSource dataSource;

  @Before
  public void setUp() {
    this.dataSource = HOLDER.getDataSource();
  }

  @Test
  public void prepareSchema() {
    SCHEMA_UTIL.prepareSchema(dataSource);
  }

  @Test
  public void createSchema() throws SQLException {
    SCHEMA_UTIL.createSchema(dataSource.getConnection());
  }

  @Test
  public void insertData() throws SQLException {
    SCHEMA_UTIL.insertData(dataSource.getConnection());
  }
}