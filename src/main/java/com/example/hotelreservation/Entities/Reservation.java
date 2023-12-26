package com.example.hotelreservation.Entities;

public class Reservation implements IEntity{

    private String ID;
    private String customerID;
    private String roomID;
    private String hotelID;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(String ID, String customerID, String roomID, String hotelID, String checkInDate,
                       String checkOutDate) {
        this.ID = ID;
        this.customerID = customerID;
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.customerID+"'"+","+"'"+this.roomID+"'"+","+
                "'"+this.hotelID+"'"+","+"'"+this.checkInDate+"'"+","+"'"+this.checkOutDate+"'";
    }

    @Override
    public String getColumns() {
        return "ID,customerID,roomID,hotelID,checkInDate,checkOutDate";
    }
}
