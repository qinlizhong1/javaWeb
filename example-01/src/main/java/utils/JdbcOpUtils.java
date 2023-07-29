package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcOpUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/qin/Desktop/develop/javaWeb/example-01/src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("mysql.url");
        driver=properties.getProperty("mysql.driver");
        username=properties.getProperty("mysql.username");
        password=properties.getProperty("mysql.password");

        //注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 工具类构造函数私有，防止被实例化
     */
    private JdbcOpUtils(){

    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public  static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接资源
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
