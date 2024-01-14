package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Entities.GenderTypes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.hotelreservation.HomeController.customer;
import static com.example.hotelreservation.HomeController.stage;
import static com.example.hotelreservation.HotelReservationController.reservationResult;


public class ProfileController implements Initializable {
    public TextField usernameUserInfo;
    public TextField mailUserInfo;
    public TextField nameUserInfo;
    public TextField surnameUserInfo;
    public TextField identificationNumberUserInfo;
    public TextField birthDateUserInfo;
    public Button backHome;
    public Button saveUserInfo;
    public TextField phoneUserInfo;
    public SplitMenuButton genderMenuProfile;

    CustomerManager customerManager = new CustomerManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameUserInfo.setText(customer.getUsername());
        mailUserInfo.setText(customer.getMail());
        nameUserInfo.setText(customer.getName());
        surnameUserInfo.setText(customer.getSurname());
        identificationNumberUserInfo.setText(customer.getIdentificationNumber());
        birthDateUserInfo.setText(customer.getBirthDate());
        phoneUserInfo.setText(customer.getPhone());
        String gender = "Other";
        if (customer.getGender() == 0)
            gender = "Woman";
        else if (customer.getGender() == 1)
            gender = "Man";
        genderMenuProfile.setText(gender);
    }

    @FXML
    protected void onSaveUserInfo() throws IOException {
        ReservationDetailController detailController = new ReservationDetailController();
        int gender;
        if (GenderTypes.Woman.name().equals(genderMenuProfile.getText())) {
            gender = 0;
        } else if (genderMenuProfile.getText().equals(GenderTypes.Man.name())) {
            gender = 1;
        } else {
            gender = 2;
        }
        customer.setBirthDate(birthDateUserInfo.getText());
        customer.setGender(gender);
        customer.setIdentificationNumber(identificationNumberUserInfo.getText());
        customer.setMail(mailUserInfo.getText());
        customer.setName(nameUserInfo.getText());
        customer.setPhone(phoneUserInfo.getText());
        customer.setSurname(surnameUserInfo.getText());
        customer.setUsername(usernameUserInfo.getText());
        reservationResult = customerManager.updateInfo(HotelReservationApplication.dbHelper, HotelReservationApplication.connection, customer);
        detailController.goPaymentResultPage();
    }

    @FXML
    protected void onbackHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Hotel Reservation");
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void changeGender1()  {

        genderMenuProfile.setText("Woman");
    }

    @FXML
    protected void changeGender2()  {

        genderMenuProfile.setText("Man");
    }

    @FXML
    protected void changeGender3()  {

        genderMenuProfile.setText("Other");
    }
}
