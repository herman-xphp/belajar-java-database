package muslim.dev.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  void testCommit() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    for (int i = 0; i < 1000; i++) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "admin@test.com");
      preparedStatement.setString(2, "hello");
      preparedStatement.executeUpdate();
      preparedStatement.close();
    }

    connection.commit();
    connection.close();
  }

  @Test
  void testRollback() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    for (int i = 0; i < 1000; i++) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "admin@test.com");
      preparedStatement.setString(2, "hello");
      preparedStatement.executeUpdate();
      preparedStatement.close();
    }

    connection.commit();
    connection.close();
  }
}
