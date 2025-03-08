package muslim.dev.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class BatchTest {

  @Test
  void testStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = "INSERT INTO comments(email, comment) VALUES ('admin@test.com','hi')";

    for (int i = 0; i < 1000; i++) {
      statement.addBatch(sql);
    }

    statement.executeBatch();

    statement.close();
    connection.close();
  }

  @Test
  void testPreparedStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    for (int i = 0; i < 1000; i++) {
      preparedStatement.clearParameters();
      preparedStatement.setString(1, "admin@test.com");
      preparedStatement.setString(1, "hello");
      preparedStatement.addBatch();
    }

    preparedStatement.executeBatch();

    preparedStatement.close();
    connection.close();
  }
}
