package DAL.acoount;

import java.io.Serializable;

/**
 * 该类描述了Database的user_account表
 * 其中 classification 表示两种账号类型，医生不需要注册默认classification为0，注册用户为病人 classification为1
 * 实现了serializable 用于将对象序列化，在通信时可以直接读写对象。
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private int id;
    private int classification;
    //用于标识对用户的具体操作
    //1代表注册
    //2代表登陆
    //需要被前端修改
    private int mode;
    private static final long serialVersionUID = 1L;

    public User() {
        super();
    }

    public User(String username, String password, int mode) {
        this.username = username;
        this.password = password;
        this.name = "default";
        this.classification = 1;
        this.mode = mode;
    }
    public User(String username, int mode) {
        this.username = username;
        this.name = "default";
        this.classification = 1;
        this.mode = mode;
    }

    public User(String username, String password, String name, int mode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.classification = 1; //1 代表普通用户
        this.mode = mode;
    }

    public User(String username, String password,int classification, int mode) {
        this.username = username;
        this.password = password;
        this.classification = classification;
        this.mode = mode;
        this.name = "default";

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", classification=" + classification +
                '}';
    }
}
