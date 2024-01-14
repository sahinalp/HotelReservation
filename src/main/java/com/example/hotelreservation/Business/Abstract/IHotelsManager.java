package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Hotel;
import com.example.hotelreservation.Entities.HotelRoom;
import com.example.hotelreservation.Entities.Room;

import java.sql.Connection;
import java.util.ArrayList;

public interface IHotelsManager {
    //  get all rooms,hotel and room from database
    ArrayList<HotelRoom> getAllRooms(DbHelper dbHelper, Connection connection, String script, int dateDiff);

    Room getRoom(DbHelper dbHelper, Connection connection, int ID);

    Hotel getHotel(DbHelper dbHelper, Connection connection, int ID);
}
