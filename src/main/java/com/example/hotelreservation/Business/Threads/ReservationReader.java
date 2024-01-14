package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;

import java.sql.Connection;

import static com.example.hotelreservation.OldReservationController.oldReservationsArrayList;

public class ReservationReader implements Runnable {

    // create IReservationService
    IReservationService reservationService;
    private final DbHelper dbHelper;
    private final Connection connection;
    private final int customerID;


    // create constructor
    public ReservationReader(IReservationService reservationService, DbHelper dbHelper, Connection connection, int customerID) {
        this.reservationService = reservationService;
        this.dbHelper = dbHelper;
        this.connection = connection;
        this.customerID = customerID;
    }

    @Override
    public void run() {
        oldReservationsArrayList = reservationService.getAllOldReservations(dbHelper, connection, customerID);
    }
}
