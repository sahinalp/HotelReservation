package com.example.hotelreservation.Entities;

public class Room implements IEntity{
    private int ID;
    private int hotelID;
    private RoomTypes type;
    private Boolean isFull;
    private String facilities;
    private String description;
    private String photo;
    private int roomSize;
    private String currency;
    private Double price;

    public Room(int ID, int hotelID, RoomTypes type, Boolean isFull, String facilities, String description,
                String photo, int roomSize, String currency, Double price) {
        this.ID = ID;
        this.hotelID = hotelID;
        this.type = type;
        this.isFull = isFull;
        this.facilities = facilities;
        this.description = description;
        this.photo = photo;
        this.roomSize = roomSize;
        this.currency = currency;
        this.price = price;
    }

    @Override
    public String getValues() {
        return this.ID+","+this.hotelID+","+"'"+this.type+"'"+","+this.isFull+","+"'"+this.facilities +"'"+","+
                "'"+this.description+"'"+","+"'"+this.photo+"'"+","+this.roomSize+","+
                "'"+this.currency +"'"+","+this.price;
    }

    @Override
    public String getColumns() {
        return "ID,hotelID,type,isFull,facilities,description,photo,roomSize,currency,price";
    }
}
