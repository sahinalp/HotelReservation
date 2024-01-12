package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;

import java.sql.Connection;

public interface ICustomerService {

    int register(DbHelper dbHelper,Connection connection, Customer customer);
    int getTotalUser(DbHelper dbHelper,Connection connection);

    void updateInfo();

    boolean login(DbHelper dbHelper, Connection connection, String username, String password);
}
