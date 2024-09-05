/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.time.LocalDate;

/**
 *
 * @author ngoct
 */
public class Booking {
    private int booking_id;
    private int room_id;
    private int guest_id;
    private LocalDate check_in;
    private LocalDate check_out;

    public Booking(int booking_id, int room_id, int guest_id, LocalDate check_in, LocalDate check_out) {
        this.booking_id = booking_id;
        this.room_id = room_id;
        this.guest_id = guest_id;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public LocalDate getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDate check_in) {
        this.check_in = check_in;
    }

    public LocalDate getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDate check_out) {
        this.check_out = check_out;
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-8s | %-8s | %-12s | %-12s |",
            booking_id, room_id, guest_id, check_in, check_out);
    }
    
    
}
