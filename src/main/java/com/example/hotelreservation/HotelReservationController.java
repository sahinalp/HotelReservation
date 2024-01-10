package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Core.DbHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HotelReservationController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML
    protected void onHelloButtonClick() throws IOException {

        CustomerManager customerManager = new CustomerManager();
        boolean canLogin=false;
        canLogin=customerManager.login(HotelReservationApplication.dbHelper,HotelReservationApplication.connection,
                username.getText(),password.getText());

        if (canLogin)
        {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Hata!!");
            a.setHeaderText("Hatalı şifre");
            a.show();
        }
    }
}