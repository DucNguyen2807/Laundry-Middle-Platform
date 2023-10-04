/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khait
 */
public class ConnectDB {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn= null;
        try {
            String hostname = "35.240.253.244";
            String sqlInstanceName = "4cf6829aa93728e";
            String sqlDatabase = "Laundry-Middle-Platform";
            String sqlUser = "sqlserver";
            String sqlPassword = "123456";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase;

           conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
    
           System.out.println("Connect to database successful!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
