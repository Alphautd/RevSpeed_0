package DOAS;

import java.sql.SQLException;
import java.util.List;

import Entities.User;

public interface UserDao {
    User createUser(User user) throws SQLException;
    boolean loginUser(String username, String password);

    User getUserByEmail(String email);
    boolean updateUserName(User user);
    // Add other user-related methods as needed
    boolean updateUserAddress(User user);
    boolean updateUserPhoneNumber(User user);
}
