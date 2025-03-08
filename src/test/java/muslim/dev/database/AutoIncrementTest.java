package muslim.dev.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class AutoIncrementTest {

  @Test
  void testAutoIncrement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments(email, comment) VALUE(?, ?)";
    PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    prepareStatement.setString(1, "test@test.com");
    prepareStatement.setString(2, "hi");

    prepareStatement.executeUpdate();

    ResultSet resultSet = prepareStatement.getGeneratedKeys();
    if (resultSet.next()) {
      System.out.println("Id Comment : " + resultSet.getInt(1));
    }

    resultSet.close();
    prepareStatement.close();
    connection.close();
  }
}
