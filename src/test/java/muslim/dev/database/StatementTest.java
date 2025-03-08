package muslim.dev.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class StatementTest {

  @Test
  void testCreateStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    Statement statement = connection.createStatement();

    statement.close();

    connection.close();
  }

  @Test
  void testExecuteUpdate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        INSERT INTO customers(id, name, email)
        VALUES('herman', 'Herman', 'herman@test.com')
        """;

    int executeUpdate = statement.executeUpdate(sql);
    System.out.println(executeUpdate);

    statement.close();
    connection.close();
  }

  @Test
  void testExecuteDelete() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        DELETE FROM customers
        """;

    int executeUpdate = statement.executeUpdate(sql);
    System.out.println(executeUpdate);

    statement.close();
    connection.close();
  }

  @Test
  void testExecuteQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        SELECT * FROM customers
        """;

    ResultSet resultSet = statement.executeQuery(sql);

    resultSet.close();
    statement.close();
    connection.close();
  }

}
