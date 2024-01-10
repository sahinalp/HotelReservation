package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.ICustomerService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.IEntity;
import com.example.hotelreservation.Entities.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager implements ICustomerService {

    @Override
    public void register() {

    }

    @Override
    public void updateInfo() {

    }

    @Override
    public boolean login(DbHelper dbHelper,Connection connection, String username,String password) {
        ResultSet resultSet;
        Customer customer = new Customer();
        String script="WHERE username = '"+username+"' ";
        try {
            resultSet=dbHelper.getEntity(connection,"customer",customer,script);
            resultSet.next();
                customer = new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getInt(10)
                );
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(customer.isCustomer(username,password))
        {
            return true;
        }
        return false;


    }
}
