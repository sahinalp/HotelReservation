package com.example.hotelreservation;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Business.Concrete.ReservationManager;
import com.example.hotelreservation.Business.Threads.ReservationReader;
import com.example.hotelreservation.Business.Threads.ReservationWriter;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.hotelreservation.HomeController.customer;
import static com.example.hotelreservation.HomeController.reservationRoom;
import static com.example.hotelreservation.HotelReservationController.reservationResult;

public class OldReservationController implements Initializable {
    ExecutorService executorService =null;
    Lock lock = new ReentrantLock();
    public Button backOldReservations;
    @FXML
    private TableColumn<OldReservations, Integer> oldReservationRoomID;
    @FXML
    private TableColumn<OldReservations, Integer> oldReservationReservationID;
    ObservableList<OldReservations> oldReservationsList = null;
    public static ArrayList<OldReservations> oldReservationsArrayList = null;

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

    IReservationService reservationService = new ReservationManager();

    private int reservationID;

    HotelsManager hotelsManager = new HotelsManager();
    PaymentDetailController paymentDetailController = new PaymentDetailController();
    private String checinDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        executorService= Executors.newCachedThreadPool();
        oldReservationsList = FXCollections.observableArrayList();
        executorService.execute(new ReservationReader(reservationService,HotelReservationApplication.dbHelper,HotelReservationApplication.connection,customer.getID()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        oldReservationsList.addAll(oldReservationsArrayList);
        oldReservationRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        oldReservationReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        hotelNameOldReservations.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomTypeOldReservations.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        addressOldReservations.setCellValueFactory(new PropertyValueFactory<>("address"));
        checkinDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkoutDay"));
        priceCurrencyOldReservations.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
        roomListTableOldReservations.setItems(oldReservationsList);
        executorService.shutdown();
    }

    @FXML
    protected void goHome() {
        paymentDetailController.goMainMenu();
    }

    @FXML
    protected void getOldReservation() throws InterruptedException {
        executorService= Executors.newCachedThreadPool();
        int selectedRoomIndex = roomListTableOldReservations.getSelectionModel().getSelectedIndex();
        if (selectedRoomIndex <= -1) {
            return;
        }
        oldReservationsList = FXCollections.observableArrayList();


        oldReservationsArrayList=reservationService.getAllOldReservations(HotelReservationApplication.dbHelper,
                HotelReservationApplication.connection,customer.getID());

        oldReservationsList.addAll(oldReservationsArrayList);
        oldReservationRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        oldReservationReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        hotelNameOldReservations.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomTypeOldReservations.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        addressOldReservations.setCellValueFactory(new PropertyValueFactory<>("address"));
        checkinDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkoutDay"));
        priceCurrencyOldReservations.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
        roomListTableOldReservations.setItems(oldReservationsList);
        reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                oldReservationRoomID.getCellData(selectedRoomIndex));
        reservationID = oldReservationReservationID.getCellData(selectedRoomIndex);
        checinDate = checkinDateOldReservations.getCellData(selectedRoomIndex);

        executorService.shutdown();
    }

    @FXML
    protected void onCancelReservationClick() throws IOException, InterruptedException {
        executorService= Executors.newCachedThreadPool();
        LocalDate today = LocalDate.now();
        String[] checinDateList = checinDate.split("/");
        LocalDate checkinDate = LocalDate.parse(checinDateList[2] + "-" + checinDateList[1] + "-" + checinDateList[0]);
        if (checkinDate.isAfter(today.plusDays(1))) {
            lock.lock();
            executorService.execute(new ReservationWriter(reservationService,false,
                    HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                    reservationRoom, reservationID,customer,checinDate,
                    checinDate));
            lock.unlock();
            paymentDetailController.goPaymentResultPage();
        } else {
            reservationResult = 0;
            paymentDetailController.goPaymentResultPage();
        }


        executorService.shutdown();

    }
}
