package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Business.Concrete.ReservationManager;
import com.example.hotelreservation.Entities.OldReservations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.hotelreservation.HomeController.customer;
import static com.example.hotelreservation.HomeController.reservationRoom;
import static com.example.hotelreservation.HotelReservationController.reservationResult;

public class OldReservationController implements Initializable {
    public Button backOldReservations;
    @FXML
    private TableColumn<OldReservations, Integer> oldReservationRoomID;
    ObservableList<OldReservations> oldReservationsList = null;
    ArrayList<OldReservations> oldReservationsArrayList = null;

    @FXML
    private TableColumn<OldReservations, String> hotelNameOldReservations;
    @FXML
    private TableColumn<OldReservations, String> roomTypeOldReservations;
    @FXML
    private TableColumn<OldReservations, String> addressOldReservations;
    @FXML
    private TableColumn<OldReservations, String> checkinDateOldReservations;
    @FXML
    private TableColumn<OldReservations, String> checkoutDateOldReservations;
    @FXML
    private TableColumn<OldReservations, String> priceCurrencyOldReservations;
    @FXML
    private TableView<OldReservations> roomListTableOldReservations;

    ReservationManager reservationManager = new ReservationManager();

    HotelsManager hotelsManager = new HotelsManager();
    PaymentDetailController paymentDetailController = new PaymentDetailController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oldReservationsList = FXCollections.observableArrayList();
        oldReservationsArrayList = reservationManager.getAllOldReservations(HotelReservationApplication.dbHelper,
                HotelReservationApplication.connection, customer.getID());
        oldReservationsList.addAll(oldReservationsArrayList);
        oldReservationRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelNameOldReservations.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomTypeOldReservations.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        addressOldReservations.setCellValueFactory(new PropertyValueFactory<>("address"));
        checkinDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkoutDay"));
        priceCurrencyOldReservations.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
        roomListTableOldReservations.setItems(oldReservationsList);
    }

    @FXML
    protected void goHome() {
        paymentDetailController.goMainMenu();
    }

    @FXML
    protected void getOldReservation()  {
        oldReservationsList = FXCollections.observableArrayList();
        oldReservationsArrayList = reservationManager.getAllOldReservations(HotelReservationApplication.dbHelper,
                HotelReservationApplication.connection,customer.getID());
        oldReservationsList.addAll(oldReservationsArrayList);
        oldReservationRoomID.setCellValueFactory(new PropertyValueFactory<>("oldReservationRoomID"));
        hotelNameOldReservations.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomTypeOldReservations.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        addressOldReservations.setCellValueFactory(new PropertyValueFactory<>("address"));
        checkinDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkoutDay"));
        priceCurrencyOldReservations.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
        roomListTableOldReservations.setItems(oldReservationsList);
        int selectedRoomIndex = Integer.parseInt( oldReservationRoomID.getId());
        reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
               selectedRoomIndex);


    }

    @FXML
    protected void onCancelReservationClick() throws IOException {
        reservationResult = reservationManager.cancelReservation(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                reservationRoom);
        if(reservationResult==1){
            paymentDetailController.goPaymentResultPage();
        }

        System.out.println(reservationResult);

    }

}
