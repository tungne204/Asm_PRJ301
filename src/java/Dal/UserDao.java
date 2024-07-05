package Dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.user;

public class UserDao extends DBContext {

    public user findByUsernameAndPassword(String username, String password) {
        user user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            String sql = "SELECT [username], [password], [role] FROM [dbo].[users] WHERE [username] = ? AND [password] = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new user();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
