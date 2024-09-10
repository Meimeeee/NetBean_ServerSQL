/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Management.Guest;
import Management.GuestManagement;
import Management.Room;
import Management.RoomManagement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Menu {
    public static Menu instance;
    public static Menu getinstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private final static Scanner sc = new Scanner(System.in);
    RoomManagement rm = RoomManagement.getInstance();
    GuestManagement gm = GuestManagement.getInstance();
    
    public void RoomMenu() throws SQLException {
        while (true) {
            try {
                System.out.println("+-------------------------------------+");
                System.out.println("|            ROOM MANAGEMENT          |");
                System.out.println("+-------------------------------------+");
                System.out.println("| 1. Add New Room                     |");
                System.out.println("| 2. Update Room                      |");
                System.out.println("| 3. Delete Room                      |");
                System.out.println("| 4. Find Room                        |");
                System.out.println("| 5. View Room List                   |");
                System.out.println("| 0. Back to Management               |");
                System.out.println("+-------------------------------------+");
                System.out.print("Choose an option: ");
                int room = sc.nextInt();
                sc.nextLine();

                switch (room) {
                    case 1://add
                        Room r = rm.inputRoom();
                        rm.insertRoom(r);
                        break;

                    case 2://update
                        rm.updateRoom();
                        break;

                    case 3://delete
                        rm.removeRoom();
                        break;
                    case 4://find
                        rm.getRoomByID();
                        break;

                    case 5://show all
                        rm.showAllRoom();
                        break;

                    case 0://exit
                        System.out.println("See youu !!");
                        return;
                    default:
                        System.out.println("Only 0..5");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again !!");
                sc.nextLine();
            }
        }
        
    
    
    
    
    
    
    
}

    public void GuestMenu() throws SQLException{
        while (true){
        try {
            System.out.println("+--------------------------------------+");
            System.out.println("|           GUEST MANAGEMENT           |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1. Add New Guest                     |");
            System.out.println("| 2. Update Guest                      |");
            System.out.println("| 3. Delete Guest                      |");
            System.out.println("| 4. Find Guest                        |");
            System.out.println("| 5. View Guest List                   |");
            System.out.println("| 0. Back to Management                |");
            System.out.println("+--------------------------------------+");
            System.out.print("Choose an option: ");
            int guest = sc.nextInt();
            sc.nextLine();
            
            switch(guest){
                case 1://add
                    Guest g = gm.inputGuest();
                    gm.insertGuest(g);
                    break;
                    
                case 2://update
                    gm.updateGuest();
                    break;
                    
                case 3://delete
                    gm.removeGuest();
                    break;
                    
                case 4://find
                    gm.getGuestByID();
                    break;
                    
                case 5://view
                    gm.showAllGuest();
                    break;
                    
                case 0://exit
                    System.out.println("Baii baii");
                    return;
                default:
                    System.out.println("Only 1..5");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input !!!");
        }
    }
    }
    
    public void BookingMenu(){
        
    }
}