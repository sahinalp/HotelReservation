package com.example.hotelreservation;

import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelReservationApplication extends Application {
    public static Connection connection;
    public static DbHelper dbHelper;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 368);
        stage.setTitle("Login");
        stage.setScene(scene);

        stage.show();

//        Connection connection = null;
//        Room room = new Room();
//        ArrayList<Room> roomsArrayList = null;
//        try {
//            DbHelper dbHelper = new DbHelper();
//            connection = dbHelper.getConnection();
//            roomsArrayList=dbHelper.getListOfRooms(connection, room);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        for (Room room1:roomsArrayList) {
//            System.out.println(room1.getValues());
//        }
    }

    public static void main(String[] args) {
        dbHelper = new DbHelper();
        try {
            connection = dbHelper.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        launch();
    }
}