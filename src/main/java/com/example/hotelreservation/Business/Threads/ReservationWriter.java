package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;

public class ReservationWriter implements Runnable{

    // create IReservationService
    private final IReservationService reservationService;
    // create boolean variable
    private final boolean makeReservation;

    // create constructor
    public ReservationWriter(IReservationService reservationService,boolean makeReservation) {
        this.reservationService = reservationService;
        this.makeReservation =makeReservation;
    }


    @Override
    public void run() {
        // make reservation or cancel reservation
        if (makeReservation) {
//            reservationService.makeReservation();
        } else {
            reservationService.cancelReservation();
        }
    }
}
