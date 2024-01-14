package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Entities.Hotel;
import com.example.hotelreservation.Entities.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.hotelreservation.HomeController.*;

public class PaymentDetailController implements Initializable {
    public ImageView roomImageReserve=new ImageView();
    public TextArea facilitiesReserve;
    public TextField priceReserve;
    public TextArea descriptionReserve;
    public TextField hotelNameReserve;
    public TextField websiteReserve;
    public TextField telNoReserve;
    public TextField roomSizeReserve;
    public TextField rankReserve;
    public TextArea addressReserve;
    public Button reserveButton;

    public ImageView rankPhoto = new ImageView();
    public Label currency;
    HotelsManager hotelsManager = new HotelsManager();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Room reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                roomIDSend.getCellData(selectedRoomIndex));
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                reservationRoom.getHotelID());
        rankPhoto.setImage(new Image("/star.png", 30, 30, false, false));
        Image image = new Image(reservationRoom.getPhoto(), 376, 228, false, false);
        roomImageReserve.setImage(image);
        facilitiesReserve.setText(reservationRoom.getFacilities());
        descriptionReserve.setText(reservationRoom.getDescription());
        priceReserve.setText(reservationRoom.getPrice().toString());
        hotelNameReserve.setText(hotel.getName());
        websiteReserve.setText(hotel.getWebsite());
        telNoReserve.setText(hotel.getTelNo());
        roomSizeReserve.setText(String.valueOf(reservationRoom.getRoomSize()));
        rankReserve.setText(String.valueOf(hotel.getRank()));
        addressReserve.setText(hotel.getAddress());
        currency.setText(reservationRoom.getCurrency());
    }

    @FXML
    protected void goPaymentPage() {
        if (customer != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("PaymentPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 894, 604);
                stage.setTitle("Payment Page");
                stage.setScene(scene);

                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!!");
            a.setHeaderText("Please login first");
            a.show();
        }
    }

    @FXML
    protected void backReservationOnAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Reservation");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }



    protected  void goPaymentResultPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("payment-result.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reservation Result");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void goMainMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Hotel Reservation");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
