package com.example.hotelreservation.Entities;

public class OldReservations {
    public String hotelName;
    public String roomType;
    public String address;
    public String checkinDate;
    public String checkoutDay;
    public String priceCurrency;

    public OldReservations() {
    }

    public OldReservations(String hotelName, String roomType, String address,String checkinDate,
                           String checkoutDay, Double price, String currency) {
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.address = address;
        this.checkinDate=checkinDate;
        this.checkoutDay = checkoutDay;
        this.priceCurrency = price.toString() +" "+currency;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDay() {
        return checkoutDay;
    }

    public void setCheckoutDay(String checkoutDay) {
        this.checkoutDay = checkoutDay;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}
