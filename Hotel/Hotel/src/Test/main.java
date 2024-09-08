/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import JDBC.Connect;
import Management.Room;
import Management.RoomManagement;
import java.sql.SQLException;
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

        int choose = 0;
        while (true) {
            try {
                System.out.println("+--------------------------------------------------+");
                System.out.println("|              HOTEL MANAGEMENT SYSTEM             |");
                System.out.println("+--------------------------------------------------+");
                System.out.println("| 1. Room Management                               |");
                System.out.println("| 2. Guest Management                              |");
                System.out.println("| 3. Booking Management                            |");
                System.out.println("| 0. Exit                                          |");
                System.out.println("+--------------------------------------------------+");
                System.out.print("CHOOSE AN OPTION: ");
                choose = sc.nextInt();
                sc.nextLine();

                switch (choose) {
                    case 1:
                        menu.RoomMenu();
                        break;

                    case 2:
                    case 3:
                    case 0:
                        System.out.println("See you !!");
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again !!");
                sc.nextLine();
            }

        }

    }
}
