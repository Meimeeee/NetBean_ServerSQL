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
    public static Connect connection;
    public static void init() throws SQLException{
        String hostname = "localhost";
        String SQLname = "Employee";
        String user = "sa";
        String password = "12345";
        String url = "jdbc:sqlserver://" + hostname
                + ";databaseName=" + SQLname
                + ";trustServerCertificate=true";
        
        Connection connection = DriverManager.getConnection(url, user, password);
        Connect.connection = Connect.connection;
        System.out.println("Connec successfully !!!!!! >__<");
    }
    
    public static Connect getConnect(){
        return connection;
    }
}
