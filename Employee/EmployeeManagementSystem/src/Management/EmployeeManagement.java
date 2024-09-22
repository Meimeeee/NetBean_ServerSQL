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
public class EmployeeManagement {

    private static final Scanner sc = new Scanner(System.in);

    public static EmployeeManagement instance;

    public static EmployeeManagement getInstance() {
        if (instance == null) {
            instance = new EmployeeManagement();
        }
        return instance;
    }

    String table = "| %-10d | %-20s | %-10s | %-10.2f |\n";

    //1.View all
    public List<Employee> listEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Employee e = new Employee(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("id_department"),
                    rs.getDouble("salary")
            );
            employees.add(e);
        }
        return employees;
    }

    public void ShowAll() throws SQLException {
        List<Employee> employees = listEmployee();
        if (!employees.isEmpty()) {
            printHeader();
            for (Employee employee : employees) {
                System.out.println(employee);
                printFooter();
            }
        } else {
            System.out.println("EMPTY !!");
        }
    }

    //2.add
    public Employee inputEmployee() throws SQLException {
        int id;
        do {
            id = Input.readInt("Enter Employee ID: ");
            if (!isEmployeeExist(id)) {
            }
        } while (isEmployeeExist(id));
        String name = Input.readString("Enter Employee name: ");
        String DepartmentID = Input.readString("Enter Department ID: ");
        double salary = Input.readDouble("Enter salary: ");

        return new Employee(id, name, DepartmentID, salary);

    }

    public void addEmployee(Employee e) throws SQLException {
        try {
            Connection connection = JDBC.Connect.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT from employee(id, name, id_department, salary) VALUES (?, ?, ?, ?)");
            ps.setInt(1, e.getEmployeeId());
            ps.setString(2, e.getEmployeeName());
            ps.setString(3, e.getDepartmentId());
            ps.setDouble(4, e.getSalary());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Add Successfully !!");
                ShowAll();
            } else {
                System.out.println("Failed !!");
            }
        } catch (SQLException s) {
            System.out.println("Error: " + s.getMessage());
        }
    }

    //3.find
    public void findById() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int id = Input.readInt("Enter Employee ID: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        boolean exist = false;
        while (rs.next()) {
            exist = true;
            printHeader();
            System.out.format(table, rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("id_department"),
                    rs.getDouble("salary")
            );
            printFooter();
        }

        if (!exist) {
            System.out.println("NOT FOUND !!");
        }

    }

    //4.update
    public void updateEmployee() throws SQLException {
        try {
            Connection connection = JDBC.Connect.getConnection();
            int employeeId = Input.readInt("Enter id: ");
            String departmentId = Input.readString("Enter new Department ID: ");
            PreparedStatement ps = connection.prepareStatement("UPDATE employee SET id_department = ? WHERE id = ?");
            ps.setInt(1, employeeId);
            ps.setString(2, departmentId);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Update Successfully !!");
                ShowAll();
            } else {
                System.out.println("failed");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //5.remove
    public void removeEmployee() throws SQLException {
        try {
            Connection connection = JDBC.Connect.getConnection();
            int id = Input.readInt("Enter Employee ID: ");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            ps.setInt(1, id);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Delated !!");
                ShowAll();
            } else {
                System.out.println("failed !!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printHeader() {
        System.out.println("+------------+----------------------+------------+------------+\\;");
        System.out.format("| %-10s | %-20s | %-17s | %-10s |\n", "Employee ID", "Name", "Department ID", "Salary");
        System.out.println("+------------+----------------------+------------+------------+\\;");
    }

    private void printFooter() {
        System.out.println("+------------+----------------------+------------+------------+\\;");
    }

    private boolean isEmployeeExist(int id) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM employee WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        } else {
            return false;
        }
    }

}
