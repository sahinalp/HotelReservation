package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Room;

import static com.example.hotelreservation.HotelReservationController.reservationResult;

import java.sql.Connection;

public class ReservationWriter implements Runnable {
    private final IReservationService reservationService;           // create IReservationService

    private final boolean makeReservation;                          // create boolean variable

    private final DbHelper dbHelper;                                // create DbHelper
    private final Connection connection;                            // create Connection
    private final Room room;                                        // create Room
    private final int reservationID;                                // create reservationID

    private final Customer customer;                                // create Customer

    private final String checkInDate;                               // create checkInDate

    private final String checkOutDate;                              // create checkOutDate


    // create constructor
    public ReservationWriter(IReservationService reservationService, boolean makeReservation, DbHelper dbHelper,
                             Connection connection, Room room, int reservationID, Customer customer, String checkInDate, String checkOutDate) {
        // initialize variables
        this.reservationService = reservationService;
        this.makeReservation = makeReservation;
        this.dbHelper = dbHelper;
        this.connection = connection;
        this.room = room;
        this.reservationID = reservationID;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // run method
    @Override
    public void run() {
        // check if makeReservation is true
        if (makeReservation) {
            // make reservation
            reservationResult = reservationService.makeReservation(dbHelper, connection, room, customer, checkInDate, checkOutDate);
        } else {
            // cancel reservation
            reservationResult = reservationService.cancelReservation(dbHelper, connection, room, reservationID);
        }
    }
}
