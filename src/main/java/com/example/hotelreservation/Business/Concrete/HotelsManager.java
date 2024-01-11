package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.IHotelsManager;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.HotelRoom;
import com.example.hotelreservation.Entities.Reservation;
import com.example.hotelreservation.Entities.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelsManager implements IHotelsManager {

    @Override
    public void getHotels() {

    }

    @Override
    public ArrayList<HotelRoom> getAllRooms(DbHelper dbHelper, Connection connection,String script) {
        ResultSet resultSet;

        ArrayList<HotelRoom> roomsArrayList = new ArrayList<HotelRoom>();
        try {
            resultSet=dbHelper.getListOfRooms(connection, script);

            while (resultSet.next())
            {
                roomsArrayList.add(new HotelRoom(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7)
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roomsArrayList;
    }

    @Override
    public void getRoom() {

    }
}