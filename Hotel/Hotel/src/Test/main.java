/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import JDBC.Connect;
import Management.Booking;
import Management.BookingManagement;
import Management.Input;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class main {

    public static void main(String[] args) throws SQLException {
        Connect.init();
        Menu menu = Menu.getinstance();
        Scanner sc = new Scanner(System.in);

//        int choose = 0;
//        while (true) {
//            try {
//                System.out.println("+--------------------------------------------------+");
//                System.out.println("|              HOTEL MANAGEMENT SYSTEM             |");
//                System.out.println("+--------------------------------------------------+");
//                System.out.println("| 1. Room Management                               |");
//                System.out.println("| 2. Guest Management                              |");
//                System.out.println("| 3. Booking Management                            |");
//                System.out.println("| 0. Exit                                          |");
//                System.out.println("+--------------------------------------------------+");
//                System.out.print("CHOOSE AN OPTION: ");
//                choose = sc.nextInt();
//                sc.nextLine();
//
//                switch (choose) {
//                    case 1:
//                        menu.RoomMenu();
//                        break;
//
//                    case 2:
//                        menu.GuestMenu();
//                        break;
//                        
//                    case 3:
//                    case 0:
//                        System.out.println("See you !!");
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Only 0..3");
//                        break;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Try again !!");
//                sc.nextLine();
//            }
//
//        }
        BookingManagement bm = BookingManagement.getInstance();
        Booking b = bm.inputBooking();
        bm.insertBooking(b);
         System.out.println("+------------+------------+------------+--------------+--------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-12s |\n", "Booking ID", "Room ID", "Guest ID", "Check In", "Check Out");
        System.out.println("+------------+------------+------------+--------------+--------------+");
        System.out.println(b);
        System.out.println("+------------+------------+------------+--------------+--------------+");

    }
}
