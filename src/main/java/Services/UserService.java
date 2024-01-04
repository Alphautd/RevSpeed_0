package Services;

import DOAS.UserDao;
import DOASImplemtationLayer.UserDaoImpl;
import Entities.User;

import java.sql.SQLException;

public class UserService implements UserDao {
    static UserDaoImpl userDoaRef =new UserDaoImpl();
    @Override
    public User createUser(User user) throws SQLException {
        user=userDoaRef.createUser(user);
        return user;
    }

    @Override
    public boolean loginUser(String username, String password) {
        boolean valid_user= userDoaRef.loginUser(username,password);
        return valid_user;
    }

    public User getUserByEmail(String email){
        User user=userDoaRef.getUserByEmail(email);
        return user;
    }

    @Override
    public boolean updateUserName(User user) {
        boolean updateAction=userDoaRef.updateUserName(user);
        return updateAction;
    }

    @Override
    public boolean updateUserAddress(User user) {
        boolean updateAction=userDoaRef.updateUserAddress(user);
        return updateAction;
    }

    @Override
    public boolean updateUserPhoneNumber(User user) {
        boolean updateAction=userDoaRef.updateUserPhoneNumber(user);
        return updateAction;
    }


}
