package DAL.util;

import java.sql.*;

/**
 * 该类是JDBC工具类，提取dao中一些重复代码，简化代码量
 */
public class DBUtil {
    // 指定了数据库
    private static final String URL = "jdbc:postgresql://localhost:5432/jeitagrotto";
    private static final String USERNAME = "jeitagrotto";
    private static final String PASSWORD = "kt7jbehusv";
    // 指定驱动
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 连接数据库
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    // 关闭数据库连接
    public static void close(PreparedStatement preparedStatement, Connection connection) {
        close(null, preparedStatement, connection);
    }
    // 关闭数据库连接
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
