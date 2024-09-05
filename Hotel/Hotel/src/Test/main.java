/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Management.RoomManagement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class main {
    public static void main(String[] args) throws SQLException {
        JDBC.Connect.init();
        Scanner sc = new Scanner(System.in);
        
//        int choose = 0;
//        do{
//            System.out.println("+--------------------------------------------------+");
//            System.out.println("|             HOTEL MAANAGEMENT SYSTEM             |");
//            System.out.println("+--------------------------------------------------+");
//            System.out.println("| 1. Room Management                               |");
//            System.out.println("| 2. Guest Management                              |");
//            System.out.println("| 3. Booking Management                            |");
//            System.out.println("| 0. Exit                                          |");
//            System.out.println("+--------------------------------------------------+");
//            System.out.print("CHOOSE AN OPTION: "); 
//            choose = sc.nextInt(); 
//            sc.nextLine();
//            
//            switch(choose){
//                case 1:
//                case 2:
//                case 3:
//                case 0:
//                    System.out.println("See you !!");
//                    System.exit(0);
//                    break;
//                    
//            }
//        }while(choose != 0);
        

        RoomManagement rm = Management.RoomManagement.getInstance();
        rm.showAllRoom();

    }
}


