/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import JDBC.Connect;
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
public class RoomManagement {
    public static RoomManagement instance;
    
    public static RoomManagement getInstance(){
        if(instance == null){
            instance = new RoomManagement();
        }
        return instance;
    }
    
    //1. add
    public Room inputRoom(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("ID: "); 
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Type: "); 
        String type = sc.nextLine();
        System.out.print("Price: "); 
        double price = sc.nextDouble();
        System.out.print("Status: "); 
        String status = sc.nextLine(); sc.nextLine();

        return new Room(id, type, price, id);
    }
    
    public void insertRoom(Room room) throws SQLException{
        Connect connection = Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO rooms (id, type, price, status) VALUE (?, ?, ?, ?)");
        ps.setInt(1, room.getRoom_id());
        ps.setString(2, room.getRoom_type());
        ps.setDouble(3, room.getRoom_price());
        ps.setInt(4, room.getRoom_status());
        
        int rowAffected = ps.executeUpdate();
        if(rowAffected>0){
            System.out.println("Insert Successfully!!");
        } else {
            System.out.println("Insert FAILED!!!!!!!!");
        }
    }
    
    //2. Update
    public void updateRoom() throws SQLException{
        Connect connection = Connect.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Status: "); int status = sc.nextInt();
        
        PreparedStatement ps = connection.PrepareStatement("UPDATE rooms SET status = ?");
        
        ps.setInt(1, status);
        
        int rowAffected = ps.executeUpdate();
        if(rowAffected>0){
            System.out.println("Update SUCCESSFULLY!!!!");
        } else {
            System.out.println("Update FAILEDDDDDDD");
        }
    }
    
    //3. remove
    public void removeRoom() throws SQLException{
        Connect connection = Connect.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id room: "); int id = sc.nextInt();
        
        PreparedStatement ps = connection.PrepareStatement("DELETE FROM rooms WHERE id = ?");
        ps.setInt(1, id);
        
        int rowAffected = ps.executeUpdate();
        if(rowAffected>0){
            System.out.println("Remove SUCCESSFULLy!!!");
        } else {
            System.out.println("Remove FAILEDDDDDDD");
        }
    }
    
    //4. Find room by id
    public void getRoom() throws SQLException{
        Connect connection = Connect.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID room: "); int id = sc.nextInt();
        PreparedStatement ps = connection.PrepareStatement("SELECT * FROM rooms WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        boolean found = false;
        while(rs.next()){
            found = true;
            System.out.println("+---------+-----------------+------------+--------------+");
            System.out.println("| Room ID | Room Type        | Room Price | Room Status |");
            System.out.println("+---------+-----------------+------------+--------------+");
            System.out.println(rs.toString());
            
        }
        if(!found){
            System.out.println("NOT FOUND !!!");
        }
        
    }
    
    //5. Show all
    public List<Room> listRoom() throws SQLException{
        List<Room> rooms = new ArrayList<>();
        Connect connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.PrepareStatement("SELECT * FROM rooms");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Room r = new Room(
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getInt("status")
            );
            rooms.add(r);
        }
        return rooms;
    }
    
    public void showAllRoom() throws SQLException{
        List<Room> rooms = listRoom();
        if(!rooms.isEmpty()){
            System.out.println("+---------+-----------------+------------+--------------+");
            System.out.println("| Room ID | Room Type        | Room Price | Room Status  |");
            System.out.println("+---------+-----------------+------------+--------------+");
            for (Room room : rooms) {
                System.out.println(rooms);
                System.out.println("+---------+-----------------+------------+--------------+");
            }
        } else {
            System.out.println("EMPTY !!!!!!!");
        }
    }
}
