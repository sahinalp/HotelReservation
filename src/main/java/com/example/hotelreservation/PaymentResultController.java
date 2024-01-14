package com.example.hotelreservation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import static com.example.hotelreservation.HotelReservationController.reservationResult;

public class PaymentResultController implements Initializable {
    public Label paymentResult=new Label();
    public AnchorPane paymentResultPage= new AnchorPane();
    ReservationDetailController detailController = new ReservationDetailController();

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
