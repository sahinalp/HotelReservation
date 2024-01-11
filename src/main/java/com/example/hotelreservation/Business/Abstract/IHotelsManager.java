package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.HotelRoom;

import java.sql.Connection;
import java.util.ArrayList;

public interface IHotelsManager {

    void getHotels();

    ArrayList<HotelRoom> getAllRooms(DbHelper dbHelper, Connection connection,String script);
    void getRoom();

}
