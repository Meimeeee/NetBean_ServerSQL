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

    public boolean isRoomExist(int IDroom) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM rooms WHERE id = ?");
        ps.setInt(1, IDroom);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) { //trỏ đến hàng kế tiếp
            return rs.getInt(1) > 0; //nếu có sẽ lấy trị cột đầu của rs
        } else {                    //nếu không nó sẽ trả về 1 vì vậy cần điều kiện là > 0
            return false;
        }
    }

    public boolean isGuestExist(int IDGuest) throws SQLException {
        Connection connection = JDBC.Connect.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM guests WHERE is = ?");
        ps.setInt(1, IDGuest);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        } else {
            return false;
        }
    }

    //1. add
    public Booking inputBooking() {
        Connection connection = JDBC.Connect.getConnection();
        while (true) {
            try {
                int id_booking = Input.readInt("ID booking: ");
                int id_room = Input.readInt("ID room: ");
                int id_guest = Input.readInt("ID guest: ");
                sc.nextLine();
                LocalDate check_in = Input.readDate("Check_in: ");
                LocalDate check_out = Input.readDate("Check_out: ");
                if (!isRoomExist(id_room)) {
                    System.out.println("Room ID does not exist. Please enter again.");
                    continue;
                }
                if (!isGuestExist(id_guest)) {
                    System.out.println("Guest ID does not exist. Please enter again.");
                    continue;
                }
                return new Booking(id_booking, id_room, id_guest, check_in, check_out);

            } catch (SQLException e) {
                System.out.println("Error checking room or guest existence.");
            }
        }

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
        } else {
            System.out.println("FAILED !!!");
        }
    }

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
