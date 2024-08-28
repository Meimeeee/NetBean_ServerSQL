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
public class JDBC_connection {
    private static Connection connection;
    
    public static void init() throws SQLException{
        String hostname = "localhost";
        String SQL_DB = "BookManager";
        String userName = "sa";
        String password = "12345";
        String url = "jdbc:sqlserver://" + hostname 
                + ";databaseName=" + SQL_DB 
                + ";trustServerCertificate=true";
        
        Connection connection = DriverManager.getConnection(url, userName, password);
        JDBC_connection.connection = connection;
        
        System.out.println("Connect successfully!");
    }
    
    public static Connection getConnection(){
        return connection;
    }
}


