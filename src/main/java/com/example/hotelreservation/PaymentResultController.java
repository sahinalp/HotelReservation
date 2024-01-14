package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.ReservationManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.hotelreservation.HomeController.customer;
import static com.example.hotelreservation.HomeController.reservationRoom;
import static com.example.hotelreservation.HotelReservationController.reservationResult;

public class PaymentResultController implements Initializable {
    public Label paymentResult=new Label();
    public AnchorPane paymentResultPage= new AnchorPane();
    PaymentDetailController detailController = new PaymentDetailController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (reservationResult == 1) {
            paymentResult.setText("It was successfully made.");
            paymentResultPage.setStyle("-fx-background-color:#006400");
        } else {
            paymentResultPage.setStyle("-fx-background-color:#8B0000");
            paymentResult.setText("It failed ");
        }
    }

    @FXML
    protected void onOkClick(){

        detailController.goMainMenu();
    }


}
