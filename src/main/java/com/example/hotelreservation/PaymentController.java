package com.example.hotelreservation;

import com.example.hotelreservation.Business.Abstract.IReservationService;
import com.example.hotelreservation.Business.Concrete.ReservationManager;
import com.example.hotelreservation.Business.Threads.ReservationWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.hotelreservation.HomeController.*;

public class PaymentController implements Initializable {

    ExecutorService executorService = Executors.newCachedThreadPool();
    Lock lock = new ReentrantLock();
    public TextField hotelNamePayment;
    public TextField pricePayment;
    public Label label3;
    public Label label2;
    public Button reserveReservationPage;
    public Button backReservationPage;
    PaymentDetailController detailController = new PaymentDetailController();
    IReservationService reservationService = new ReservationManager();

    public DatePicker checkinDatePayment;
    public DatePicker checkoutDatePayment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkinDatePayment.setValue(selectedCheckinDate);
        checkoutDatePayment.setValue(selectedCheckoutDate);
        hotelNamePayment.setText(selectedHotelName);
        pricePayment.setText(selectedRoomPrice);
    }

    @FXML
    protected void goHome() {
        detailController.goMainMenu();
    }

    @FXML
    protected void onMakeReservation() throws IOException, InterruptedException {
        String[] checkinDateList = checkinDatePayment.getValue().toString().split("-");
        String[] checkoutDateList = checkoutDatePayment.getValue().toString().split("-");
        String checkinDate = checkinDateList[2] + "/" + checkinDateList[1] + "/" + checkinDateList[0];
        String checkoutDate = checkoutDateList[2] + "/" + checkoutDateList[1] + "/" + checkoutDateList[0];
        lock.lock();
        executorService.execute(new ReservationWriter(reservationService, true, HotelReservationApplication.dbHelper, HotelReservationApplication.connection, reservationRoom, reservationRoom.getID(), customer, checkinDate, checkoutDate));
        Thread.sleep(5000);
        lock.unlock();
        detailController.goPaymentResultPage();
        executorService.shutdown();
    }
}
