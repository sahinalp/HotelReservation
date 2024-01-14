package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.OldReservations;
import com.example.hotelreservation.Entities.Room;

import java.sql.Connection;
import java.util.ArrayList;

public interface IReservationService {
    int makeReservation(DbHelper dbHelper, Connection connection, Room room, Customer customer, String checkInDate, String checkOutDate);

    int cancelReservation(DbHelper dbHelper, Connection connection, Room room,int reservationID);

    ArrayList<OldReservations> getAllOldReservations(DbHelper dbHelper, Connection connection, int customerID);

}
