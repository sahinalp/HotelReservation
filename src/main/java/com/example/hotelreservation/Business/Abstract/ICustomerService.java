package com.example.hotelreservation.Business.Abstract;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;

import java.sql.Connection;

public interface ICustomerService {

    // customer service interface for register, update, login and get total user
    int register(DbHelper dbHelper,Connection connection, Customer customer); // register customer
    int getTotalUser(DbHelper dbHelper,Connection connection);  // get total user

    int updateInfo(DbHelper dbHelper,Connection connection, Customer customer);    // update customer info

    boolean login(DbHelper dbHelper, Connection connection, String username, String password);  // login customer
}
