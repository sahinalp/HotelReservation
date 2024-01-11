package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;

import java.sql.Connection;

public interface ICustomerService {

    void register(DbHelper dbHelper,Connection connection, Customer customer);

    void updateInfo();

    boolean login(DbHelper dbHelper, Connection connection, String username, String password);
}
