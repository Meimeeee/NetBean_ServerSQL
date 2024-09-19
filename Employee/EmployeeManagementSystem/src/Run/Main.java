/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import Management.Department;
import Management.Employee;
import java.sql.SQLException;

/**
 *
 * @author ngoct
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        JDBC.Connect.init();
        Management.Employee e = new Employee(1, "bdd", "SD1", 23);
        System.out.println(e);
        Management.Department d = new Department("saf1", "DFBSSbtesbsz");
        System.out.println(d);
    }
}
