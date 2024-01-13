package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Reservation;
import com.example.hotelreservation.Entities.Room;
import com.example.hotelreservation.HotelReservationApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;

public class ReservationManager implements IReservationService {

    // lock to control synchronization with this buffer
    private Lock lock;

    @Override
    public int makeReservation(DbHelper dbHelper, Connection connection, Room room, Customer customer, String checkInDate, String checkOutDate) {
        lock.lock();
        int result=0;
        try {
            // Set room's isFull as true
            try {
                result = dbHelper.update(connection,"room","\"isFull\"=true",room.getID());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Setting room as isFull is successful
            if(result==1)
            {
                int reservationID = getTotalReservation(dbHelper,connection);
                reservationID++;
                Reservation reservation = new Reservation(reservationID,customer.getID(),
                        room.getID(), room.getHotelID(), checkInDate,checkOutDate);
                try {
                    result=dbHelper.insert(connection,"reservation",reservation);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } finally {


        }
        //if 1 then insert is successful
        return result;
    }

    @Override
    public void cancelReservation() {
        lock.lock();
        try {
        } finally {


        }
    }

    @Override
    public void getHistory() {
        lock.lock();
        try {
        } finally {


        }
    }
    private int getTotalReservation(DbHelper dbHelper,Connection connection)
    {
        ResultSet resultSet;
        Reservation reservation = new Reservation();
        int count=0;
        try {
            resultSet=dbHelper.getEntity(connection,"reservation",reservation);

            while (resultSet.next())
            {
                count++;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
