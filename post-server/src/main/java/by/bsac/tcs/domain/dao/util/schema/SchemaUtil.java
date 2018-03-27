package by.bsac.tcs.domain.dao.util.schema;

import com.google.common.annotations.VisibleForTesting;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SchemaUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchemaUtil.class);
  private static final String CREATE_SCHEMA_FILE_NAME = "create.sql";
  private static final String INSERT_SCHEMA_FILE_NAME = "insert.sql";

  private SchemaUtil() {
  }

  public static SchemaUtil getInstance() {
    return SchemaUtil.SingletonHolder.getInstance();
  }

  public void prepareSchema(DataSource dataSource) {
    try (Connection connection = dataSource.getConnection()) {

      try {
        connection.setAutoCommit(false);
        createSchema(connection);
        insertData(connection);

        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        LOGGER.info("An exception acquired while prepare schema", e);
        throw new IllegalStateException(e);
      } finally {
        connection.setAutoCommit(true);
      }
    } catch (SQLException e) {
      LOGGER.info("An exception acquired while prepare schema", e);
      throw new IllegalStateException(e);
    }
  }

  @VisibleForTesting
  void createSchema(final Connection connection) throws SQLException {
    RunScript.execute(connection, getInputStreamReader(CREATE_SCHEMA_FILE_NAME));
  }

  @VisibleForTesting
  void insertData(final Connection connection) throws SQLException {
    RunScript.execute(connection, getInputStreamReader(INSERT_SCHEMA_FILE_NAME));
  }

  private InputStreamReader getInputStreamReader(final String fileName) {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream(fileName);
    return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
  }

  private static class SingletonHolder {

    private static final SchemaUtil INSTANCE = new SchemaUtil();

    private SingletonHolder() {
    }

    public static SchemaUtil getInstance() {
      return INSTANCE;
    }
  }
}
