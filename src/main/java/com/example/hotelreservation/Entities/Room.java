package com.example.hotelreservation.Entities;

public class Room implements IEntity{
    private String ID;
    private String hotelID;
    private RoomTypes roomType;
    private Boolean isFull;
    private String facilities;
    private String description;
    private String photo;
    private int roomSize;
    private Double price;

    public Room(String ID, String hotelID, RoomTypes roomType, Boolean isFull, String facilities,
                String description, String photo, int roomSize, Double price) {
        this.ID = ID;
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.isFull = isFull;
        this.facilities = facilities;
        this.description = description;
        this.photo = photo;
        this.roomSize = roomSize;
        this.price = price;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.hotelID+"'"+","+"'"+this.roomType+"'"+","+this.isFull+","+
                "'"+this.facilities +"'"+","+"'"+this.description+"'"+","+"'"+this.photo+"'"+","+
                this.roomSize+","+this.price;
    }

    @Override
    public String getColumns() {
        return "ID,hotelID,roomType,isFull,facilities,description,photo,roomSize,price";
    }
}
