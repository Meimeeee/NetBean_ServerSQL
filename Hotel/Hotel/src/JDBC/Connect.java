/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ngoct
 */
public class Connect {
    private static Connect connection;
    
    public static void init() throws SQLException{
        String hostName = "localhost";
        String SQL_DB = "Hotel";
        String userName = "sa";
        String password = "12345";
        String url = "jdbc:sqlserver://" + hostName
                + ";databaseName=" + SQL_DB
                + ";trustServerCertificate=true";
        
        Connection connection = DriverManager.getConnection(url, userName, password);
        System.out.println("Connect successfully!!!");
    }
    
    public static Connect getConnection(){
        return connection;
    }

}
