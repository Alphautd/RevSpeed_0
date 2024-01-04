package DOASImplemtationLayer;

import java.sql.*;
import java.util.List;

import DOAS.UserDao;
import Entities.User;

public class UserDaoImpl implements UserDao {

     static User user=new User();
    @Override
    public User createUser(User user) throws SQLException {

        String query1="select * from user_credentials where user_email='"+user.getEmail()+"' and user_phone_no='"+user.getPhone()+"'";
        Connection connection = DatabaseConnection.dbConnection().getConn();
        Statement statement1=connection.createStatement();
        ResultSet resultSet=statement1.executeQuery(query1);
        if(!resultSet.next()){
            String query="INSERT INTO user_credentials (user_name,user_phone_no,user_email,user_password,user_address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getUsername());
            statement.setLong(2,user.getPhone());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setString(5,user.getAddress());
            int rowsAffected= statement.executeUpdate();
            if (rowsAffected > 0) {
                // Registration successful, retrieve the generated user ID
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(1));
                        return user;
                    }
                }catch (SQLException e) {
                    e.printStackTrace(); // Log or handle the exception appropriately
                }
            }
        }
        return null;
    }

    @Override
    public boolean loginUser(String username, String password) {
            String query = "SELECT COUNT(*) FROM user_credentials WHERE user_email = ? AND user_password = ?";
            try (Connection connection = DatabaseConnection.dbConnection().getConn();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() && resultSet.getInt(1) > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log or handle the exception appropriately
            }
            return false;

    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM user_credentials WHERE user_email ='"+email+"'";
        try (Connection connection = DatabaseConnection.dbConnection().getConn();
             PreparedStatement statement = connection.prepareStatement(query)) {


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setAddress(resultSet.getString("user_address"));
                    user.setPhone(resultSet.getLong("user_phone_no"));
                    user.setEmail(resultSet.getString("user_email"));
                    user.setPassword(resultSet.getString("user_password"));

                    // Set other user attributes as needed
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return null;
    }

    @Override
    public boolean updateUserName(User user) {
        String query="UPDATE user_credentials SET user_name =? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.dbConnection().getConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setInt(2,user.getUserId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return false;
    }

    @Override
    public boolean updateUserAddress(User user) {
        String query="UPDATE user_credentials SET user_address = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.dbConnection().getConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getAddress());
            statement.setInt(2,user.getUserId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return false;
    }

    @Override
    public boolean updateUserPhoneNumber(User user) {
        String query="UPDATE user_credentials SET user_phone_no = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.dbConnection().getConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getPhone());
            statement.setInt(2,user.getUserId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return false;
    }
}










