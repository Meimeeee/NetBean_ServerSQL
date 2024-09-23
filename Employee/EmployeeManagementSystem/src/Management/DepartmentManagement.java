/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Run.Input;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static DepartmentManagement getInstance() {
        if (instance == null) {
            instance = new DepartmentManagement();
        }
        return instance;
    }

    String table = "| %-10s | %-20s |\n";

    //1.View all
    public List<Department> listDepartment() throws SQLException {
        List<Department> departments = new ArrayList<>();
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM department");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Department d = new Department(rs.getString("id"),
                    rs.getString("name"));
            departments.add(d);
        }
        return departments;
    }
    public void showAll() {
        List<Department> departments = new ArrayList<>();
        if (!departments.isEmpty()) {
            printHeader();
            for (Department department : departments) {
                System.out.println(department);
                printFooter();
            }
        } else {
            System.out.println("Not exist !!");
        }
    }

    //2.Add
    public Department inputDepartment() {
        String id = Input.readString("Enter Department ID: ");
        String name = Input.readString("Enter Department Name: ");
        return new Department(id, name);
    }

    public void addDepartment(Department d) throws SQLException {
        try {
            Connection connection = JDBC.Connect.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO department(id, name) VALUES (?, ?)");
            ps.setString(1, d.getDepartmentId());
            ps.setString(2, d.getDepartmentName());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("ADD SUCCESSFULLY !!");
                showAll();
            } else {
                System.out.println("Failed !!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    //3.Find
    public void findById() {
        try {
            Connection connection = JDBC.Connect.getConnection();
            String id = Input.readString("Enter Department ID: ");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM department WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                printHeader();
                System.out.format(table, rs.getString("id"),
                        rs.getString("name")
                );
                printFooter();
            } else {
                System.out.println("NOT FOUND !!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //4.Remove
    public void remvoveDepartment() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM department WHERE id = ?");
        ps.setString(1, "id");
        int count = ps.executeUpdate();
        
        if(count>0){
            System.out.println("DELETED !!");
            showAll();
        } else {
            System.out.println("FAILED !!");
        }
    }
    

    void printHeader() {
        System.out.println("+------------+----------------------+\n");
        System.out.format("| %-10s | %-20s |\n", "ID", "Name");
    }

    void printFooter() {
        System.out.println("+------------+----------------------+\n");
    }

}
