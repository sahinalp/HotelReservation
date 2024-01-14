package com.example.hotelreservation.Entities;

public class HotelRoom {
    public int roomID;
    public String hotelName;
    public String roomType;
    public String address;
    public Double hotelRank;
    public String priceCurrency;
    public String currency;
    public Double price;

    public int dateDiff;

    public HotelRoom() {

    }

    public HotelRoom(int roomID, String hotelName, String roomType, String address, Double hotelRank, Double price, String currency,int dateDiff) {
        this.roomID = roomID;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.address = address;
        this.hotelRank = hotelRank;
        this.currency = currency;
        this.price = price*dateDiff;
        this.priceCurrency = String.valueOf(price*dateDiff) +" "+currency;
    }


    public HotelRoom(int roomID, String hotelName, String roomType, String address, Double hotelRank, String priceCurrency) {
        this.roomID = roomID;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.address = address;
        this.hotelRank = hotelRank;
        this.priceCurrency = priceCurrency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public final int getRoomID() {
        return roomID;
    }

    public final void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public final String getHotelName() {
        return hotelName;
    }

    public final void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public final String getRoomType() {
        return roomType;
    }

    public final void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final Double getHotelRank() {
        return hotelRank;
    }

    public final void setHotelRank(Double hotelRank) {
        this.hotelRank = hotelRank;
    }

    public final String getPriceCurrency() {
        return priceCurrency;
    }

    public final void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}
