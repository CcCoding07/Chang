package DAL.acoount.dao.impl;

import DAL.acoount.dao.IUserDao;
import DAL.util.DBUtil;
import DAL.acoount.User;

import java.sql.*;

/**
 * Account的持久层方法
 */
public class UserDaoImpl implements IUserDao {
    //建立ssh链接 该方法已经移到服务器中
//    static {
//        /**
//         * 建立ssh连接
//         */
//        String url = "mod-msc-sw1.cs.bham.ac.uk"; //远程PostgreSQL服务器
//        String sshurl = "tinky-winky.cs.bham.ac.uk"; //SSH服务器
//        String sshuser = "yxm810"; //SSH连接用户名
//        String sshpassword = "672832006Myf"; //SSH连接密码
//        try {
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(sshuser, sshurl, 22);
//            session.setPassword(sshpassword);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
//
//            int assinged_port = session.setPortForwardingL(5432, url, 5432);//端口映射 转发  数据库服务器地址url
//
//            System.out.println("localhost:" + assinged_port);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * 完成用户添加数据库持久化方法
     *
     * @param user 需要被添加到数据库中的用户对象
     */
@Override
    public void insertUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "insert into user_account(username, password, name,mode,classification) values(?,?,?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4,user.getMode());
            ps.setInt(5,user.getClassification());

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }


    /**
     * 查询数据库 是否重复
     *
     * @return
     */
    @Override
    public User selectRepeat(String username) {
        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;

        // 结果集对象 用于封装数据库的查询结果
        ResultSet rs = null;
        String sql = "select username from user_account where username = ?";
        try {

            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            // 执行数据库的查询语句，并返回查询结果
            rs = ps.executeQuery();
            conn.commit();
            // 光标向下移动一次并判断下一个元素是否有值。
            while (rs.next()) {
                user = new User();
                //将结果集中当前元素的显示列名为id的数据获取出来并设置道user的id上
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }

    /**
     * 查询账号名和密码是否匹配
     * @param username
     * @return
     */
    @Override
    public User selectLogin(String username) {
        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "select username, password from user_account where username = ?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }
    /**
     * 查询账号名和密码是否匹配
     * @param username
     * @return
     */
    @Override
    public User selectClassification(String username) {
        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "select classification from user_account where username = ?";
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                user = new User();
                user.setClassification(rs.getInt("classification"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }
//    public void updateUser(User user) {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection conn = null;
//        PreparedStatement ps = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
//            String sql = "update t_user set username = ?, name = ?, password = ? where id = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getName());
//            ps.setString(3, user.getPassword());
//            ps.setLong(4, user.getId());
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void deleteUser(Long id) {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jeitagrotto","jeitagrotto","kt7jbehusv");
//            String sql = "delete from t_user where id = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, id);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
}