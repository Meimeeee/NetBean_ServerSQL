/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import Management.Department;
import Management.DepartmentManagement;
import Management.Employee;
import Management.EmployeeManagement;
import Management.SalaryManagement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Menu {

    public static Scanner sc = new Scanner(System.in);
    EmployeeManagement em = EmployeeManagement.getInstance();
    DepartmentManagement dm = DepartmentManagement.getInstance();
    SalaryManagement sm = SalaryManagement.getInstance();

    public void E_menu() {
        int employee = 0;
        do {
            try {
                System.out.println("====================================");
                System.out.println("           Employee Menu            ");
                System.out.println("====================================");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Find Employee by ID");
                System.out.println("4. Update Employee's Department");
                System.out.println("5. Remove Employee");
                System.out.println("0. Exit");
                System.out.println("====================================");
                employee = Input.readInt("Enter your choice: ");
                switch (employee) {
                    case 1: //add
                        Employee e = em.inputEmployee();
                        em.addEmployee(e);
                        break;

                    case 2://2.view all
                        em.ShowAll();
                        break;

                    case 3://find
                        int find = 0;
                        do {
                            System.out.println("====================================");
                            System.out.println("           Find Employee            ");
                            System.out.println("====================================");
                            System.out.println("1. Find By Employee ID");
                            System.out.println("2. Find By Name");
                            System.out.println("3. Find By Department ID");
                            System.out.println("0. Exit");
                            System.out.println("====================================");
                            employee = Input.readInt("Enter your choice: ");
                            switch (find) {
                                case 1:
                                    em.findById();
                                    break;

                                case 2:
                                    em.findEmployee("name");
                                    break;

                                case 3:
                                    em.findEmployee("id_department");
                                    break;

                                default:
                                    System.out.println("Invalid. Try again 1..3 and 0 to exit");
                                    break;
                            }
                        } while (find != 0);
                        break;

                    case 4://update in4
                        em.updateEmployee();
                        break;

                    case 5://remove
                        em.removeEmployee();
                        break;

                    case 0:
                        System.out.println("Baibaii");
                        return;

                    default:
                        System.out.println("Invalid. Try again 1..5 and 0 to exit");
                        break;
                }

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (employee != 0);
    }

    public void D_menu() {
        int deparment = 0;
        do {
            try {
                System.out.println("====================================");
                System.out.println("          Department Menu           ");
                System.out.println("====================================");
                System.out.println("1. Add Department");
                System.out.println("2. View All Department");
                System.out.println("3. Find Department by ID");
                System.out.println("4. Remove Department");
                System.out.println("0. Exit");
                System.out.println("====================================");
                deparment = Input.readInt("Enter your choice: ");
                switch (deparment) {
                    case 1://add
                        Department d = dm.inputDepartment();
                        dm.addDepartment(d);
                        break;

                    case 2://view
                        dm.showAll();
                        break;

                    case 3://find
                        dm.findById();
                        break;

                    case 4://remove
                        dm.remvoveDepartment();
                        break;

                    case 0://exit
                        System.out.println("Bai baii");
                        return;

                    default:
                        System.out.println("Invalid. Try again 1..4 and 0 to exit");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (deparment != 0);
    }

    public void S_menu() throws SQLException {
        int salary = 0;
        do {
            System.out.println("======================================");
            System.out.println("             Salary Menu              ");
            System.out.println("======================================");
            System.out.println("1. View employees by amount of salary");
            System.out.println("2. Total salary of Department");
            System.out.println("3. Update employees salary");
            System.out.println("0. Exit");
            System.out.println("======================================");
            salary = Input.readInt("Enter your choice: ");
            switch(salary){
                case 1:
                    double money = Input.readDouble("Enter amount of salary: ");
                    String amount = Input.readString("View employees with salaries above " + money + "(yes/no): ");
                    sm.ViewBaseOnSalary(money,(boolean) amount.equalsIgnoreCase("yes"));
                    break;
                    
                case 2:
                    sm.TotalSalaryOfDepartment();
                    break;
                    
                case 3:
                    sm.updateSalaryByIdEmployee();
                    break;
                    
                case 0:
                    System.out.println("Baii");
                    return;
                    
                default:
                    System.out.println("Invalid. Try 1..3");
                    break;
            }
            
        } while (salary != 0);
    }
}
