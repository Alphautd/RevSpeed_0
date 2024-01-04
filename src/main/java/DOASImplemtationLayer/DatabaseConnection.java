package DOASImplemtationLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url="jdbc:mysql://localhost:3306/RevSpeed_db";
    private String user_name="root";
    private String password="root";
    private static Connection conn=null;
    private static DatabaseConnection connect =null;

    private DatabaseConnection() throws SQLException {
        this.conn= DriverManager.getConnection(url,user_name,password);
    }

    public Connection getConn() {
        return conn;
    }

    public static DatabaseConnection dbConnection() throws SQLException {
        connect=new DatabaseConnection();
        return connect;
    }
}
