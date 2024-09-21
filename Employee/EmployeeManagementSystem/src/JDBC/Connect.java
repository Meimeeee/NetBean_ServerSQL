/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ngoct
 */
public class Connect {
    public static Connection connection;
    
    public static void init() throws SQLException{
        String hostName = "localhost";
        String SQL = "Employee";
        String user = "sa";
        String password = "12345";
        String url = "jdbc:sqlserver://" + hostName
                + ";databaseName=" + SQL
                + ";trustServerCertificate=true";
        
        Connection connection = DriverManager.getConnection(url, user, password);
        Connect.connection = connection;
        System.out.println("Connect successfully !!");
    }
    
    public static Connection getConnection(){
        return connection;
    }
}
