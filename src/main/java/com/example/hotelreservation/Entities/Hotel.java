package com.example.hotelreservation.Entities;

public class Hotel implements IEntity{
    private String ID;
    private String name;
    private String address;
    private String website;
    private String telNo;
    private String fax;
    private Double rank;
    private String country;
    private String city;
    private int roomCapacity;

    public Hotel(String ID, String name, String address, String website, String telNo, String fax, Double rank,String country,String city,int roomCapacity) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.website = website;
        this.telNo = telNo;
        this.fax = fax;
        this.rank = rank;
        this.city=city;
        this.country=country;
        this.roomCapacity=roomCapacity;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.name+"'"+","+"'"+this.address+"'"+","+
                "'"+this.website+"'"+","+"'"+this.telNo+"'"+","+"'"+this.fax+"'"+","+this.rank+","+this.roomCapacity+"'"+this.country+"'"+this.city+"'";
    }

    @Override
    public String getColumns() {
        return "ID,name,address,website,telNo,fax,rank,roomCapacity,country,city,services";
    }
}
