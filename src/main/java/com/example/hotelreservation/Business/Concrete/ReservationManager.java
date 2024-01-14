package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationManager implements IReservationService {

    // lock to control synchronization with this buffer
    private final Lock lock = new ReentrantLock();

    // make reservation
    @Override
    public synchronized int makeReservation(DbHelper dbHelper, Connection connection, Room room, Customer customer, String checkInDate, String checkOutDate) {
        lock.lock();        // lock this buffer to control synchronization
        int result;         // result of the operation
        try {
            try {
                // get room from database
                ResultSet resultSet = dbHelper.getEntity(connection, "room", room, room.getID());
                resultSet.next();
                // check if room is full
                boolean isRoomFull = resultSet.getBoolean(4);
                if (!isRoomFull) {
                    result = dbHelper.update(connection, "room", "\"isFull\"=true", room.getID());
                } else {
                    result = -1;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Setting room as isFull is successful
            if (result == 1) {
                Reservation reservation = new Reservation(customer.getID(),
                        room.getID(), room.getHotelID(), checkInDate, checkOutDate);
                try {
                    result = dbHelper.insert(connection, "reservation", reservation);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } finally {
            // unlock this buffer
            lock.unlock();
        }
        //if 1 then insert is successful
        return result;
    }

    // cancel reservation
    @Override
    public synchronized int cancelReservation(DbHelper dbHelper, Connection connection, Room room, int reservationID) {
        lock.lock();        // lock this buffer to control synchronization
        int result;         // result of the operation
        try {
            // delete reservation from database
            result = dbHelper.delete(connection, "reservation", reservationID);
            // if delete is successful update room as isFull=false
            if (result == 1) {
                result = dbHelper.update(connection, "room", "\"isFull\"=false", room.getID());
            } else {
                result = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // unlock this buffer
            lock.unlock();
        }
        //if 1 then delete is successful
        return result;
    }

    // get all reservations of a customer
    public synchronized ArrayList<OldReservations> getAllMyReservations(DbHelper dbHelper, Connection connection, int customerID) {
        ResultSet resultSet;
        ArrayList<OldReservations> oldReservationsArrayList = new ArrayList<>();
        try {
            lock.lock(); // lock this buffer to control synchronization
            // get all reservations of a customer from database
            resultSet = dbHelper.getListOfOldReservations(connection, customerID);
            // add all reservations to arraylist
            while (resultSet.next()) {
                oldReservationsArrayList.add(new OldReservations(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getString(9)
                ));
            }
            // close resultset
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // unlock this buffer
        lock.unlock();
        // return arraylist
        return oldReservationsArrayList;
    }
}
