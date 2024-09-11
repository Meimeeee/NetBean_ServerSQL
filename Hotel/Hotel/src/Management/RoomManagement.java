/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import JDBC.Connect;
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
public class RoomManagement {
    public static RoomManagement instance;
    public static RoomManagement getInstance() {
        if (instance == null) {
            instance = new RoomManagement();
        }
        return instance;
    }

    String table = "| %-10s | %-20s | %-13s | %-13s |%n";
    private final static Scanner sc = new Scanner(System.in);

    //1. add
    public Room inputRoom() {
        int id = Input.readInt("ID: ");
        System.out.println("Type: ");
        String type = sc.nextLine();
        double price = Input.readDouble("Price: ");
        int status = Input.readInt("Status: ");

        return new Room(id, type, price, status);
    }

    public void insertRoom(Room room) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO rooms (id, type, price, status) VALUES (?, ?, ?, ?)");
        ps.setInt(1, room.getRoom_id());
        ps.setString(2, room.getRoom_type());
        ps.setDouble(3, room.getRoom_price());
        ps.setInt(4, room.getRoom_status());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Insert Successfully!!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllRoom();
        } else {
            System.out.println("Insert FAILED!!!!!!!!");
        }
    }

    //2. Update
    public void updateRoom() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE rooms SET status = ? WHERE id = ?");
        int id = Input.readInt("Enter Room ID: ");
        int status = Input.readInt("Enter status: ");
        ps.setInt(1, status);
        ps.setInt(2, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Update SUCCESSFULLY!!!!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllRoom();
        } else {
            System.out.println("Update FAILEDDDDDDD");
        }
    }

    //3. remove
    public void removeRoom() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int id = Input.readInt("Enter room ID: ");

        PreparedStatement ps = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
        ps.setInt(1, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Remove SUCCESSFULLy!!!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllRoom();
        } else {
            System.out.println("Remove FAILEDDDDDDD");
        }
    }

    //4. Find room by id
    public void getRoomByID() throws SQLException {
        Connection connection = Connect.getConnection();
        int id = Input.readInt("Enter room ID: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM rooms WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        while (rs.next()) {
            found = true;
            printHeader();
            String roomStatus = (rs.getInt("status") <= 0) ? "FULL" : "AVAILABLE";
            System.out.printf(table,
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    roomStatus
            );
            printFooter();
        }
        if (!found) {
            System.out.println("NOT FOUND !!!");
        }
    }

    //5. Show all
    public List<Room> listRoom() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM rooms");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
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

    public void showAllRoom() throws SQLException {
        List<Room> rooms = listRoom();
        if (!rooms.isEmpty()) {

            printHeader();
            for (Room room : rooms) {
                String roomStatus = (room.getRoom_status() <= 0) ? "FULL" : "AVAILABLE";
                System.out.printf(table, room.getRoom_id(), room.getRoom_type(), room.getRoom_price(), roomStatus);
                printFooter();
            }
        } else {
            System.out.println("EMPTY !!!!!!!");
        }
    }

    private void printHeader() {
        System.out.println("+------------+----------------------+---------------+---------------+");
        System.out.println("| Room ID    | Room Type            | Room Price    | Room Status   |");
        System.out.println("+------------+----------------------+---------------+---------------+");
    }
    private void printFooter() {
        System.out.println("+------------+----------------------+---------------+---------------+");
    }
}
