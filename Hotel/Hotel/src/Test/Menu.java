/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Management.Room;
import Management.RoomManagement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Menu {
    Scanner sc = new Scanner(System.in);
    RoomManagement rm = RoomManagement.getInstance();
    
    public void RoomMenu() throws SQLException{
        int room = 0;
        do{
            System.out.println("+-------------------------------------+");
            System.out.println("|            ROOM MANAGEMENT          |");
            System.out.println("+-------------------------------------+");
            System.out.println("| 1. Add New Room                     |");
            System.out.println("| 2. Update Room                      |");
            System.out.println("| 3. Delete Room                      |");
            System.out.println("| 4. Find Room                        |");
            System.out.println("| 5. View Room List                   |");
            System.out.println("| 0. Exit                             |");
            System.out.println("+-------------------------------------+");
            System.out.print("Choose an option: ");
            room = sc.nextInt();
            sc.nextLine();
            
            switch(room){
                case 1://add
                    Room r = rm.inputRoom();
                    rm.insertRoom(r);
                    
                case 2://update
                    rm.updateRoom();
                case 3://delete
                    rm.removeRoom();
//                case 4://find
//                    rm.getRoom();
                case 5://show all
                    rm.showAllRoom();
                case 0://exit
                    System.out.println("See youu !!");
                    System.exit(0);
                    break;
                
            }
        } while (room != 0);
    }
}
