/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

/**
 *
 * @author ngoct
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private String departmentId;
    private double salary;

    public Employee(int employeeId, String employeeName, String departmentId, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("| %-10d | %-20s | %-10s | %-10.2f |", employeeId,employeeName,departmentId,salary);
    }
    
    
}
