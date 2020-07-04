package DAL.acoount.dao;

import DAL.acoount.User;

public interface IUserDao {
    void insertUser(User user);
    User selectRepeat(String username);
    User selectLogin(String username);
    User selectClassification(String username);
}
