package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.IReservationService;

import java.util.concurrent.locks.Lock;

public class ReservationManager implements IReservationService {

    // lock to control synchronization with this buffer
    private Lock lock;

    @Override
    public void makeReservation() {
        lock.lock();
        try {

        } finally {


        }
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
}
