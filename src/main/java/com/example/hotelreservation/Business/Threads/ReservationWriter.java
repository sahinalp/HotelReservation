package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Room;

import static com.example.hotelreservation.HotelReservationController.reservationResult;

import java.sql.Connection;

public class ReservationWriter implements Runnable {

    // create IReservationService
    private final IReservationService reservationService;
    // create boolean variable
    private final boolean makeReservation;

    private final DbHelper dbHelper;
    private final Connection connection;
    private final Room room;
    private final int reservationID;

    private final Customer customer;

    private final String checkInDate;

    private final String checkOutDate;


    // create constructor
    public ReservationWriter(IReservationService reservationService, boolean makeReservation, DbHelper dbHelper,
                             Connection connection, Room room, int reservationID, Customer customer, String checkInDate, String checkOutDate) {
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

    @Override
    public void run() {
        // make reservation or cancel reservation
        if (makeReservation) {
            reservationResult = reservationService.makeReservation(dbHelper, connection, room, customer, checkInDate, checkOutDate);
        } else {
            reservationResult = reservationService.cancelReservation(dbHelper, connection, room, reservationID);
        }
    }
}
