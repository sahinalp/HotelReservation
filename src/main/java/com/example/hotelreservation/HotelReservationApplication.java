package com.example.hotelreservation;

import com.example.hotelreservation.Core.DbHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HotelReservationApplication extends Application {
    public static Connection connection;
    public static DbHelper dbHelper;

    public static boolean isLoggedIn =false;
    @Override
    public void start(Stage _stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 287, 382);
        HomeController.stage = new Stage();
        HomeController.stage.setTitle("Login");
        HomeController.stage.setScene(scene);

        HomeController.stage.show();

    }

    public static void main(String[] args) {
        dbHelper = new DbHelper();
        try {
            connection = dbHelper.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        launch();
    }
}