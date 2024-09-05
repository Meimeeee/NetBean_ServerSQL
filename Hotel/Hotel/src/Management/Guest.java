/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

/**
 *
 * @author ngoct
 */
public class Guest {
    private int guest_id;
    private String guest_name;
    private String guest_email;
    private long guest_phone;

    public Guest(int guest_id, String guest_name, String guest_email, long guest_phone) {
        this.guest_id = guest_id;
        this.guest_name = guest_name;
        this.guest_email = guest_email;
        this.guest_phone = guest_phone;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public long getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(long guest_phone) {
        this.guest_phone = guest_phone;
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-20s | %-15s |", 
            guest_id, guest_name, guest_email, guest_phone);
        
    }
    
    
    
}
