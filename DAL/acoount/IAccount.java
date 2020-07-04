package DAL.acoount;

public interface IAccount {

    int logIn(User user);

    int isMatch(User user);

    int signUp(User user);

    boolean isLigal(String string);

    boolean registerAccount(User user);

    boolean isExists(String username);

    public String query(User user);

}
