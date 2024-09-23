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
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class SalaryManagement {

    private static final Scanner sc = new Scanner(System.in);
    public static SalaryManagement instance;

    public static SalaryManagement getInstance() {
        if (instance == null) {
            instance = new SalaryManagement();
        }
        return instance;
    }

    String table = "| %-10d | %-20s | %-10s | %-10.2f |\n";

    //1.View employees with amount of salary
    public void ViewBaseOnSalary(double amount, boolean above) throws SQLException {
        try {
            Connection connection = JDBC.Connect.getConnection();
            PreparedStatement ps;
            if (above) {//true => return > amount
                ps = connection.prepareStatement("SELECT * WHERE salary > ?");
            } else {
                ps = connection.prepareStatement("SELECT * WHERE salary < ?");
            }

            ps.setDouble(1, amount);
            ResultSet rs = ps.executeQuery();
            boolean exist = false;
            while (rs.next()) {
                exist = true;
                System.out.format(table, rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("id_department"),
                        rs.getDouble("salary")
                );
            }
            if (!exist) {
                System.out.println("NOT FOUND !!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    //2.Calculate and show total of salary
    public void TotalSalaryOfDepartment() {
        try {
            Connection connection = JDBC.Connect.getConnection();
            String IdDepartment = Input.readString("Enter Department ID: ");
            PreparedStatement ps = connection.prepareStatement("SELECT SUM(salary) AS total_salary FROM employees WHERE id_department = ?");
            ps.setString(1, IdDepartment);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Total salary of Department " + IdDepartment + "= " + rs.getDouble("total_salary"));
            } else {
                System.out.println("No data found for Department " + IdDepartment);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateSalaryByIdEmployee() {
        try {
            Connection connection = JDBC.Connect.getConnection();
            int id = Input.readInt("Enter Employee ID: ");
            double salary = Input.readDouble("New salary: ");
            PreparedStatement ps = connection.prepareStatement("UPDATE employee SET salary = ? WHERE id = ?");
            ps.setDouble(1, salary);
            ps.setInt(2, id);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Update Successfully !!");
                System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");

                ps = connection.prepareStatement("SELECT * FROM employee");
                ResultSet rs = ps.executeQuery();
                printHeader();
                while (rs.next()) {
                    System.out.format(table, rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("id_department"),
                            rs.getDouble("salary")
                    );
                    printFooter();
                }
            } else {
                System.out.println("Failed !!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printHeader() {
        System.out.println("+------------+----------------------+------------+------------+");
        System.out.format("| %-10s | %-20s | %-17s | %-10s |\n", "Employee ID", "Name", "Department ID", "Salary");
        System.out.println("+------------+----------------------+------------+------------+");
    }

    private void printFooter() {
        System.out.println("+------------+----------------------+------------+------------+");
    }
}
