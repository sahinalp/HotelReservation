package com.example.hotelreservation.Entities;

public class Reservation implements IEntity{

    private int ID;
    private int customerID;
    private int roomID;
    private int hotelID;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(int customerID, int roomID, int hotelID, String checkInDate, String checkOutDate) {
        this.customerID = customerID;
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Reservation() {

    }

    @Override
    public String getValues() {
        return this.customerID+","+this.roomID+","+this.hotelID+","+"'"+this.checkInDate+"'"+","+
                "'"+this.checkOutDate+"'";
    }

    @Override
    public String getColumns() {
        return "\"customerID\", \"roomID\", \"hotelID\", \"checkInDate\", \"checkOutDate\"";
    }
}
