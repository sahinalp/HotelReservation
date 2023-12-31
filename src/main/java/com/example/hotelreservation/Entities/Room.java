package com.example.hotelreservation.Entities;

public class Room implements IEntity{
    private String ID;
    private RoomTypes type;
    private Boolean isFull;
    private String facilities;
    private String description;
    private String photo;
    private int roomSize;
    private Double price;

    private String currency;

    private int number;

    public Room(String ID, RoomTypes type, Boolean isFull, String facilities,
                String description, String photo, int roomSize, Double price) {
        this.ID = ID;
        this.type = type;
        this.isFull = isFull;
        this.facilities = facilities;
        this.description = description;
        this.photo = photo;
        this.roomSize = roomSize;
        this.price = price;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+"'"+this.type+"'"+","+this.isFull+","+
                "'"+this.facilities +"'"+","+"'"+this.description+"'"+","+"'"+this.photo+"'"+","+
                this.roomSize+","+this.price;
    }

    @Override
    public String getColumns() {
        return "ID,type,isFull,facilities,description,photo,roomSize,price";
    }
}
