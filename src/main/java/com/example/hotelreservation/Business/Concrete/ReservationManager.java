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

    @Override
    public synchronized int makeReservation(DbHelper dbHelper, Connection connection, Room room, Customer customer, String checkInDate, String checkOutDate) {
        lock.lock();
        int result;
        try {
            // Set room's isFull as true
            try {
                ResultSet resultSet = dbHelper.getEntity(connection, "room", room, room.getID());
                resultSet.next();
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
            lock.unlock();
        }
        //if 1 then insert is successful
        return result;
    }

    @Override
    public synchronized int cancelReservation(DbHelper dbHelper, Connection connection, Room room, int reservationID) {
        lock.lock();
        int result;
        try {
            result = dbHelper.delete(connection, "reservation", reservationID);
            if (result == 1) {
                result = dbHelper.update(connection, "room", "\"isFull\"=false", room.getID());
            } else {
                result = 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return result;
    }
    
    public synchronized ArrayList<OldReservations> getAllMyReservations(DbHelper dbHelper, Connection connection, int customerID) {
        ResultSet resultSet;

        ArrayList<OldReservations> oldReservationsArrayList = new ArrayList<>();
        try {
            lock.lock();
            resultSet = dbHelper.getListOfOldReservations(connection, customerID);

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
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
        return oldReservationsArrayList;
    }
}
