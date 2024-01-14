package com.example.hotelreservation.Entities;

import java.time.Duration;
import java.time.LocalDate;

public class OldReservations {
    public int roomID;
    public int reservationID;
    public String hotelName;
    public String roomType;
    public String address;
    public String checkinDate;
    public String checkoutDay;
    public String priceCurrency;

    public OldReservations() {
    }

    public OldReservations(int roomID,int reservationID,String hotelName, String roomType, String address,String checkinDate,
                           String checkoutDay, Double price, String currency) {
        this.roomID = roomID;
        this.reservationID=reservationID;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.address = address;
        this.checkinDate=checkinDate;
        this.checkoutDay = checkoutDay;
        String[] chekinDList=checkinDate.split("/");
        LocalDate checkinD = LocalDate.parse(chekinDList[2] + "-" + chekinDList[1]+"-"+chekinDList[0]);
        String[] chekinOList=checkoutDay.split("/");
        LocalDate checkinO = LocalDate.parse(chekinOList[2] + "-" + chekinOList[1]+"-"+chekinOList[0]);
        int dateDiff = (int) Duration.between(checkinD.atStartOfDay(),checkinO.atStartOfDay()).toDays();
        this.priceCurrency = String.valueOf(price*dateDiff) +" "+currency;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
