package databaseConnection;

import java.sql.*;

public class DatabaseConnection {
//    private static final String username = "sql6633511";
//    private static final String passwd = "BZ1gJ2I3Gi";
//    private static final String url = "sql6.freemysqlhosting.net;databaseName=sql6633511;user=sql6633511;password=BZ1gJ2I3Gi;encrypt=true;trustServerCertificate=true;";
//
//
//    public static Connection connect() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(url, username, passwd);
//    }

    private static final String username = "LocalThang";
    private static final String passwd = "123456";
    private static final String url = "jdbc:sqlserver://THANG\\SQLEXPRESS;databaseName=ManagementSupermarket;user=LocalThang;password=123456;encrypt=true;trustServerCertificate=true;";


    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Connect successfully!");
        return DriverManager.getConnection(url, username, passwd);
    }


    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
