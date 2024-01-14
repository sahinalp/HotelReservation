package com.example.hotelreservation.Business.Concrete;

import com.example.hotelreservation.Business.Abstract.ICustomerService;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.HotelReservationController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager implements ICustomerService {

    // register customer
    @Override
    public int register(DbHelper dbHelper, Connection connection, Customer customer) {
        // insert customer to database
        int result;
        try {
            result = dbHelper.insert(connection, "customer", customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // get total customer from database
    public int getTotalUser(DbHelper dbHelper, Connection connection) {
        ResultSet resultSet;
        Customer customer = new Customer();
        int count = 0;
        try {
            resultSet = dbHelper.getEntity(connection, "customer", customer);
            while (resultSet.next()) {
                count++;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    // update customer information from database
    @Override
    public int updateInfo(DbHelper dbHelper, Connection connection, Customer customer) {
        String script = "\"mail\"='" + customer.getMail() + "'" +
                ", \"username\"='" + customer.getUsername() + "'" +
                ", \"password\"='" + customer.getPassword() +
                "', \"name\"='" + customer.getName() +
                "', \"surname\"='" + customer.getSurname() +
                "', \"phone\"='" + customer.getPhone() +
                "', \"identificationNumber\"='" + customer.getIdentificationNumber() +
                "', \"birthDate\"='" + customer.getBirthDate() + "'" +
                ", \"gender\"='" + customer.getGender() + "'";
        int result = 0;
        try {
            result = dbHelper.update(connection, "customer", script, customer.getID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // login customer from database
    @Override
    public boolean login(DbHelper dbHelper, Connection connection, String username, String password) {
        ResultSet resultSet;
        Customer customer = new Customer();
        String script = "WHERE username = '" + username + "' ";
        try {
            resultSet = dbHelper.getEntity(connection, "customer", customer, script);
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

        if (customer.isCustomer(username, password)) {
            HotelReservationController.setCustomer(customer);
            return true;
        }
        return false;
    }
}
