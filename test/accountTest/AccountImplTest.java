package test.accountTest;

import BLL.Client;
import DAL.acoount.AccountImpl;
import DAL.acoount.IAccount;
import DAL.acoount.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {
    private Client client = new Client("172.22.231.47", 7780);
    private IAccount account = new AccountImpl();

    @Test
        // When the registered account enters illegal characters in username
        // the system should return a prompt string. The user entered illegal characters.
    void testSignUp1() {
        User user = new User("yair&", "123", 1);
        String actual = client.sendData(user);
        String expect = "Invalid username input";
        assertEquals(expect, actual);

    }

    @Test
        // When the registered account enters illegal characters in password
        // the system should return a prompt string. The user entered illegal characters.
    void testSignUp2() {
        User user = new User("yair", "123&", 1);
        String actual = client.sendData(user);
        String expect = "Invalid password input";
        assertEquals(expect, actual);

    }
    @Test
        //registration success
    void testSignUp3() {
        User user = new User("yairuuuu", "123", 1);
        String actual = client.sendData(user);
        String expect = "registration success";
        assertEquals(expect, actual);

    }
    @Test
        //Register again with the same username, and you will get the prompt "Duplicate username, please re-enter"
    void testSignUp4() {
        User user = new User("yair", "123", 1);
        String actual = client.sendData(user);
        String expect = "Duplicate username, please re-enter";
        assertEquals(expect, actual);

    }
// For a record that already exists in the database
    //username = YIFAN password = 123
    @Test
        //When the username in database entered by the user does not exist,
        // the prompt "User name input error" will be returned
    void testLogIn1() {
        User user = new User("YIFAN1", "123", 2);
        String actual = client.sendData(user);
        String expect = "Username input error";
        assertEquals(expect, actual);

    }
    @Test
        //When the password in database entered by the user does not match,
        // the prompt "Password input error" will be returned
    void testLogIn2() {
        User user = new User("YIFAN", "123M", 2);
        String actual = client.sendData(user);
        String expect = "Password input error";
        assertEquals(expect, actual);

    }
    @Test
        //login successful
    void testLogIn3() {
        User user = new User("YIFAN", "123", 2);
        String actual = client.sendData(user);
        String expect = "login successful";
        assertEquals(expect, actual);

    }

}