package com.example.hotelreservation.Entities;

public class Room implements IEntity{
    private String ID;
    private String hotelID;
    private RoomTypes roomType;
    private Boolean isFull;
    private String amenities;
    private String photo;
    private Double price;

    public Room(String ID, String hotelID, RoomTypes roomType, Boolean isFull, String amenities, String photo, Double price) {
        this.ID = ID;
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.isFull = isFull;
        this.amenities = amenities;
        this.photo = photo;
        this.price = price;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.hotelID+"'"+","+"'"+this.roomType+"'"+","+this.isFull+","+"'"+this.amenities+"'"+","+"'"+this.photo+"'"+","+this.price;
    }

    @Override
    public String getColumns() {
        return "ID,hotelID,roomType,isFull,amenities,photo,price";
    }
}
