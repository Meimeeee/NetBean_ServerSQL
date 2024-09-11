/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

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
public class GuestManagement {

    public static GuestManagement instance;

    public static GuestManagement getInstance() {
        if (instance == null) {
            instance = new GuestManagement();
        }
        return instance;
    }

    public final static Scanner sc = new Scanner(System.in);
    String table = "| %-15s | %-20s | %-30s | %-15s |%n";

    //list guest
    public List<Guest> listGuest() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM guests");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Guest g = new Guest(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
            );
            guests.add(g);
        }
        return guests;
    }

    //5.show all
    public void showAllGuest() throws SQLException {
        List<Guest> guests = listGuest();
        if (!guests.isEmpty()) {
            printHeader();
            for (Guest guest : guests) {
                System.out.format(table, guest.getGuest_id(), guest.getGuest_name(), guest.getGuest_email(), guest.getGuest_phone());
                printFooter();
            }
        } else {
            System.out.println("EMPTY !!!");
        }
    }
    
    //1. add
    public Guest inputGuest() {
        int id = Input.readInt("Enter ID: ");
        String name = Input.readString("Enter name: ");
        String mail = Input.readEmail("Enter mail: ");
        String phone = Input.inputPhonNumber("Enter phone number: ");

        return new Guest(id, name, mail, phone);
    }

    public void insertGuest(Guest guest) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO guests(id, name, email, phone) VALUES (?, ?, ?, ?)");
        ps.setInt(1, guest.getGuest_id());
        ps.setString(2, guest.getGuest_name());
        ps.setString(3, guest.getGuest_email());
        ps.setString(4, guest.getGuest_phone());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Add SUCCESFULLY !!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllGuest();
        } else {
            System.out.println("Insert FAILED !!!!!");
        }
    }

    //2.update
    public void updateGuest() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE guests SET phone = ? WHERE id = ?");
        int id = Input.readInt("Enter guest ID: "); 
        sc.nextLine();
        String phone = Input.inputPhonNumber("New Phone:");
        ps.setString(1, phone);
        ps.setInt(2, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Update SUCCESFULL");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllGuest();
        } else {
            System.out.println("UPDATE FAILED !!!!");
            System.out.println("Not found guest by id: " + id);
        }
    }

    //3.remove
    public void removeGuest() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM guests WHERE id = ?");
        int id = Input.readInt("Enter guest ID: ");
        ps.setInt(1, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("REMOVE SUCCESFULLY !!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAllGuest();
        } else {
            System.out.println("REMOVE FAILED !!!");
        }
    }

    //4.find guest by id
    public void getGuestByID() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM guests WHERE id = ?");
        int id = Input.readInt("Enter guest ID: ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean found = false;

        while (rs.next()) {
            found = true;
            printHeader();
            System.out.format(table,
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
            );
            printFooter();
        }
        if (!found) {
            System.out.println("NOT FOUND !!!");
        }

    }

    private void printHeader() {
        System.out.println("+-----------------+----------------------+--------------------------------+-----------------+");
        System.out.format("| %-15s | %-20s | %-30s | %-15s |%n", "ID", "Name", "Email", "Phone");
        System.out.println("+-----------------+----------------------+--------------------------------+-----------------+");
    }

    private void printFooter() {
        System.out.println("+-----------------+----------------------+--------------------------------+-----------------+");
    }
}
