package DAL.acoount;

import DAL.acoount.dao.IUserDao;
import DAL.acoount.dao.impl.UserDaoImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 账户操作的具体实现类
 */
public class AccountImpl implements IAccount {
    private IUserDao userDaoImpl = new UserDaoImpl();

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public synchronized int logIn(User user) {
        return isMatch(user);
    }

    /**
     * 用于给新注册用户入库，
     * 涉及到用户输入的用户名是否已存在，以及用户名与密码是否输入合法的检测
     * 如果用户名输入不合法 return 1
     * 如果用户输入密码不合法 return 2
     * 如果新注册用户输入用户名已存在，return 3
     * 如果都通过检测，return 0
     *
     * @param user
     * @return
     */
    @Override
    public synchronized int signUp(User user) {
        if (!isLigal(user.getUsername())) {
            return 1;
        } else if (!isLigal(user.getPassword())) {
            return 2;
        } else if (isExists(user.getUsername())) {
            return 3;
        } else {
            registerAccount(user);
            return 0;
        }
    }

    @Override
    public String query(User user) {
        return String.valueOf(userDaoImpl.selectClassification(user.getUsername()).getClassification());
    }

    /**
     * 用于检测用户登录状态，会检索数据库，
     * 如果用户输入的用户名数据库不存在 return 1
     * 如果用户名输入密码与数据库不匹配，return 2
     * 用户名密码都匹配，return 0 表示登陆成功
     *
     * @param user
     * @return
     */
    @Override
    public int isMatch(User user) {
        User selectLogin = userDaoImpl.selectLogin(user.getUsername());
        if (selectLogin == null) {
            return 1;
        } else if (!user.getPassword().equals(selectLogin.getPassword())) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * 只匹配26个字母和数字的组合
     *
     * @param string
     * @return
     */
    @Override
    public boolean isLigal(String string) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * 向数据库里添加用户注册信息
     *
     * @return
     */
    @Override
    public boolean registerAccount(User user) {
        userDaoImpl.insertUser(user);
        return true;
    }

    /**
     * 用于检测用户输入的用户名是否已经存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean isExists(String username) {
        if (userDaoImpl.selectRepeat(username) == null) return false;
        return userDaoImpl.selectRepeat(username).getUsername().equals(username);
    }

}
