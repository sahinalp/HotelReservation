package com.example.hotelreservation.Entities;

public class Reservation implements IEntity{

    private String ID;
    private Customer customer;
    private Room room;
    private Hotel hotel;
    private String checkInDate;
    private String checkOutDate;
    private int totalDays;

    public Reservation(String ID, Customer customer, Room room, Hotel hotel, String checkInDate,
                       String checkOutDate,int totalDays) {
        this.ID = ID;
        this.customer = customer;
        this.room = room;
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalDays = totalDays;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.customer+"'"+","+"'"+this.room+"'"+","+
                "'"+this.hotel+"'"+","+"'"+this.checkInDate+"'"+","+"'"+this.checkOutDate+"'"+this.totalDays+"'";
    }

    @Override
    public String getColumns() {
        return "ID,customer,room,hotel,checkInDate,checkOutDate,totalDays";
    }
}
