/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import JDBC.Connect;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class DepartmentManagement {
    public static Scanner sc = new Scanner(System.in);
    
    public static DepartmentManagement instance;
    public static DepartmentManagement getInstance(){
        if(instance == null){
            instance = new DepartmentManagement();
        }
        return instance;
    }
    
    String table = "| %-10s | %-20s |";
    
    public List<Department> listDepartment(){
        List<DepartmentManagement> department = new ArrayList<>();
        Connect connection =  JDBC.Connect.getConnect();
        PreparedStatement ps = connection.prepareStatement("SELECT")
    }
    
    
}
