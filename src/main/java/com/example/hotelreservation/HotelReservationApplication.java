package com.example.hotelreservation;

import com.example.hotelreservation.Entities.HotelRoom;
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