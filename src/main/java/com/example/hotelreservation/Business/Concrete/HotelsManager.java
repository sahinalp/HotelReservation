package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.IHotelsManager;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelsManager implements IHotelsManager {

    // Get all rooms from database
    @Override
    public ArrayList<HotelRoom> getAllRooms(DbHelper dbHelper, Connection connection, String script, int dateDiff) {
        ResultSet resultSet;        // create result set
        ArrayList<HotelRoom> roomsArrayList = new ArrayList<HotelRoom>();   // create array list
        try {
            resultSet = dbHelper.getListOfRooms(connection, script);  // get rooms from database
            // add rooms to array list
            while (resultSet.next()) {
                roomsArrayList.add(new HotelRoom(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        dateDiff
                ));
            }
            // close result set
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // return rooms array list
        return roomsArrayList;
    }

    // get room from database
    @Override
    public Room getRoom(DbHelper dbHelper, Connection connection, int ID) {
        ResultSet resultSet;    // create result set
        Room room = new Room(); // create room object
        try {
            resultSet = dbHelper.getEntity(connection, "room", room, ID);    // get room from database
            resultSet.next();       // get next row
            // set room object
            room = new Room(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getBoolean(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getDouble(10)
            );
            // close result set
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;

    }

    // get hotel from database
    @Override
    public Hotel getHotel(DbHelper dbHelper, Connection connection, int ID) {
        ResultSet resultSet;        // create result set
        Hotel hotel = new Hotel();  // create hotel object
        try {
            resultSet = dbHelper.getEntity(connection, "hotel", hotel, ID);// get hotel from database
            resultSet.next();   // get next row
            hotel = new Hotel(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            );
            // close result set
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // return hotel object
        return hotel;
    }
}