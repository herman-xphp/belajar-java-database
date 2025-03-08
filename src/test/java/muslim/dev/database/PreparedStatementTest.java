package muslim.dev.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class PreparedStatementTest {

  @Test
  void testPreparedStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String username = "admin'; #";
    String password = "admin";

    String sql = """
        SELECT * FROM admin WHERE username = ? AND password = ?
        """;
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, username);
    prepareStatement.setString(2, password);

    ResultSet executeQuery = prepareStatement.executeQuery();

    if (executeQuery.next()) {
      System.out.println("Sukses login : " + executeQuery.getString("username"));
    } else {
      System.out.println("Gagal login");
    }

    prepareStatement.close();
    connection.close();
  }
}
