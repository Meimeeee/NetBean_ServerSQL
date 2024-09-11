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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class BookingManagement {

    public final static Scanner sc = new Scanner(System.in);
    public static BookingManagement instance;

    public static BookingManagement getInstance() {
        if (instance == null) {
            instance = new BookingManagement();
        }
        return instance;
    }

    String table = "| %-10s | %-10s | %-10s | %-12s | %-12s |%n";

    //check tồn tại
//    public boolean isRoomExist(int IDroom) throws SQLException {
//        Connection connection = JDBC.Connect.getConnection();
//        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM rooms WHERE id = ?");
//        ps.setInt(1, IDroom);
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) { //trỏ đến hàng kế tiếp
//            return rs.getInt(1) > 0; //nếu có sẽ lấy trị cột đầu của rs
//        } else {                    //nếu không nó sẽ trả về 1 vì vậy cần điều kiện là > 0
//            return false;
//        }
//    }
//
//    public boolean isGuestExist(int IDGuest) throws SQLException {
//        Connection connection = JDBC.Connect.getConnection();
//        PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM guests WHERE is = ?");
//        ps.setInt(1, IDGuest);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1) > 0;
//        } else {
//            return false;
//        }
//    }
    //1. add
    public Booking inputBooking() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int id_booking = Input.readInt("ID booking: ");
        int id_room = checkID("rooms");
        int id_guest = checkID("guests");
        LocalDate check_in = Input.readDate("Check_in: ");
        LocalDate check_out = Input.readDate("Check_out: ");

        return new Booking(id_booking, id_room, id_guest, check_in, check_out);

    }

    public void insertBooking(Booking b) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO bookings(id, room_id, guest_id, check_in, check_out) VALUES (?, ?, ?, ?, ?)");
        ps.setInt(1, b.getBooking_id());
        ps.setInt(2, b.getRoom_id());
        ps.setInt(3, b.getGuest_id());
        ps.setObject(4, b.getCheck_in());
        ps.setObject(5, b.getCheck_out());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Add SUCCESSFULLY !!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAll();
        } else {
            System.out.println("FAILED !!!");
        }
    }

    //2.Update
    public void updateCheckInDate() {
        Connection connection = JDBC.Connect.getConnection();
        try {
            int roomID = checkID("rooms");
            int guestID = checkID("guests");
            int bookingID = Input.readInt("Enter Booking ID: ");

            LocalDate checkIn = Input.readDate("Update Date Check in: ");
            PreparedStatement ps = connection.prepareStatement("UPDATE bookings SET check_in = ? WHERE id = ?");
            ps.setObject(1, checkIn);
            ps.setInt(2, bookingID);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Update SUCCESSFULLY");
                System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
                showAll();
            } else {
                System.out.println("FAILED");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateRoom() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int roomID = checkID("rooms");
        int guestID = checkID("guests");
        int bookingID = Input.readInt("Enter Booking ID: ");

        int newRoom = Input.readInt("Enter new room ID: ");
        PreparedStatement ps = connection.prepareStatement("UPDATE bookings SET room_id = ? WHERE id = ?");
        ps.setInt(1, newRoom);
        ps.setInt(2, bookingID);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("UPDATE SUCCESSFULLY");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAll();
        } else {
            System.out.println("UPDATE FAILED !!");
        }

    }

    //3.Remove
    public void Delete() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int id = Input.readInt("Enter id booking: ");
        PreparedStatement ps = connection.prepareStatement("DELETE FROM bookings WHERE id = ?");
        ps.setInt(1, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("DELETE SUCCESSFULLLY !!!");
            System.out.println("***___***___***___**___***___***___**___***___***___**___***___***___**___***___***___**___***___***___");
            showAll();
        } else {
            System.out.println("FAILED !!!");
        }
    }

    //4. findByID
    public void findBooking() throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        int id = Input.readInt("Enter ID Booking: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM bookings WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean found = false;
        while (rs.next()) {
            found = true;
            LocalDate check_in = rs.getDate("check_in").toLocalDate();
            LocalDate check_out = rs.getDate("check_out").toLocalDate();
            printHeader();
            System.out.format(table,
                    rs.getInt("id"),
                    rs.getInt("room_id"),
                    rs.getInt("guest_id"),
                    check_in,
                    check_out
            );
            printFooter();
            if (!found) {
                System.out.println("NOT FOUND !!");
            }
        }
    }

    //5.Show all
    public List<Booking> listBooking() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM bookings");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LocalDate check_in = rs.getDate("check_in").toLocalDate();
            LocalDate check_out = rs.getDate("ccheck_out").toLocalDate();
            Booking b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("room_id"),
                    rs.getInt("guest_id"),
                    check_in,
                    check_out
            );
            bookings.add(b);
        }
        return bookings;
    }

    public void showAll() throws SQLException {
        List<Booking> bookings = listBooking();
        if (!bookings.isEmpty()) {
            printHeader();
            for (Booking booking : bookings) {
                System.out.println(booking);
                printFooter();
            }
        } else {
            System.out.println("EMPTY !!");
        }
    }

    //checkID
    private int checkID(String tableName) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        while (true) {
            try {
                int id = Input.readInt("Enter ID " + tableName);
                PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM " + tableName + " WHERE id = ?");
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return id;
                } else {
                    System.out.println("ID not exist. Try again !!");
                    
                }
            } catch (SQLException e) {
                System.out.println("Database Error !!!" + e.getMessage());
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!!");
                sc.nextLine();
            }
        }
    }

    //print
    private void printHeader() {
        System.out.println("+------------+------------+------------+--------------+--------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-12s |\n", "Booking ID", "Room ID", "Guest ID", "Check In", "Check Out");
        System.out.println("+------------+------------+------------+--------------+--------------+");
    }

    private void printFooter() {
        System.out.println("+------------+------------+------------+--------------+--------------+");
    }
}

//    private int booking_id;
//    private int room_id;
//    private int guest_id;
//    private LocalDate check_in;
//    private LocalDate check_out;
