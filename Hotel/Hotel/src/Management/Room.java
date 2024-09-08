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
public class Room {
    private int room_id;
    private String room_type;
    private double room_price;
    private int room_status;

    public Room(int room_id, String room_type, double room_price, int room_status) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.room_price = room_price;
        this.room_status = room_status;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public double getRoom_price() {
        return room_price;
    }

    public void setRoom_price(double room_price) {
        this.room_price = room_price;
    }

    public int getRoom_status() {
        return room_status;
    }

    public void setRoom_status(int room_status) {
        this.room_status = room_status;
    }

    @Override
    public String toString() {
        return String.format("| %-10d | %-20s | %-12.2f | %-10d |", 
            room_id, room_type, room_price, room_status);
    }
    
    
    
}
