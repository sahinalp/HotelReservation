package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;

import java.sql.Connection;

public interface ICustomerService {

    void register();

    void updateInfo();

    boolean login(DbHelper dbHelper, Connection connection, String username, String password);
}
