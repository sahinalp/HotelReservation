package com.example.hotelreservation.Business.Threads;

import com.example.hotelreservation.Business.Abstract.IReservationService;

public class ReservationReader implements Runnable {

    // create IReservationService

    IReservationService reservationService;


    // create constructor
    public ReservationReader(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void run() {
        reservationService.getHistory();
    }
}
