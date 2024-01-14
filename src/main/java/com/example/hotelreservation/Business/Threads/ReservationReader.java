package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.MyReservationController;

import java.sql.Connection;

public class ReservationReader implements Runnable {

    // create IReservationService
    IReservationService reservationService;
    private final DbHelper dbHelper;            // create DbHelper
    private final Connection connection;        // create Connection
    private final int customerID;               // create customerID


    // create constructor
    public ReservationReader(IReservationService reservationService, DbHelper dbHelper, Connection connection, int customerID) {
        // initialize variables
        this.reservationService = reservationService;
        this.dbHelper = dbHelper;
        this.connection = connection;
        this.customerID = customerID;
    }

    // run method
    @Override
    public void run() {
        // get all my reservations
        MyReservationController.oldReservationsArrayList = reservationService.getAllMyReservations(dbHelper, connection, customerID);
    }
}
