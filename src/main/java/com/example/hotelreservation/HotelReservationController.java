package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Business.Concrete.ReservationManager;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Hotel;
import com.example.hotelreservation.Entities.HotelRoom;
import com.example.hotelreservation.Entities.Room;
import com.example.hotelreservation.Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//import javax.mail.*;
//import javax.mail.internet.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.example.hotelreservation.HomeController.customer;
import static com.example.hotelreservation.HomeController.stage;

public class HotelReservationController {
    public Label errorText;

    public static int reservationResult = 0;


    public Button makeReservationButton;
    private int menu = 1;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private TextField usernameRegister;
    @FXML
    private TextField nameRegister;
    @FXML
    private TextField surnameRegister;
    @FXML
    private TextField mailRegister;
    @FXML
    private TextField phoneRegister;
    @FXML
    private TextField identityNumberRegister;
    @FXML
    private TextField birthdayRegister;
    @FXML
    private TextField passwordRegister;
    @FXML
    private TextField passwordAgainRegister;
    @FXML
    private SplitMenuButton genderMenuRegister;
    @FXML
    private Button backRegister;
    @FXML
    private MenuItem womanRegister;
    @FXML
    private MenuItem manRegister;
    @FXML
    private MenuItem otherRegister;




    ObservableList<HotelRoom> hotelRoomList = null;
    ArrayList<HotelRoom> roomsArrayList = null;

    @FXML
    private TextField usernameUserInfo;
    @FXML
    private TextField passwordUserInfo;
    @FXML
    private TextField mailUserInfo;
    @FXML
    private TextField nameUserInfo;
    @FXML
    private TextField surnameUserInfo;
    @FXML
    private TextField identificationNumberUserInfo;
    @FXML
    private TextField birthDateUserInfo;
    @FXML
    private TextField phoneUserInfo;
    @FXML
    private TextField genderUserInfo;



    private static boolean isStageShowEventRun = false;
    CustomerManager customerManager = new CustomerManager();




    private String mailSenderMail = null;
    private String mailSenderPassword = null;



    @FXML
    protected void onSignInButtonClick() throws IOException {

        boolean canLogin = false;
        canLogin = customerManager.login(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                username.getText(), password.getText());

        if (canLogin) {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Hotel Reservation");
            stage.setScene(scene);


            HotelReservationApplication.isLoggedIn = true;
            stage.centerOnScreen();
            stage.show();

//            Platform.runLater(() -> this.stageShowEvent());


        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Hata!!");
            a.setHeaderText("Hatalı şifre");
            a.show();
        }
    }

    @FXML
    protected void onContinueWithoutLogin() throws IOException {
        HotelReservationApplication.isLoggedIn = false;

        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 894, 604);
        stage.setTitle("Hotel Reservation");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    protected void onSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("register.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Register");
            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }

    @FXML
    protected void changeGender1() throws IOException {

        genderMenuRegister.setText("Woman");
    }

    @FXML
    protected void changeGender2() throws IOException {

        genderMenuRegister.setText("Man");
    }

    @FXML
    protected void changeGender3() throws IOException {

        genderMenuRegister.setText("Other");
    }

    @FXML
    protected void onSignUpComplete() throws IOException {
        int genderValue = 2;
        if (genderMenuRegister.getText().equals(GenderTypes.Woman.name())) {
            genderValue = 0;
        } else if (genderMenuRegister.getText().equals(GenderTypes.Man.name())) {
            genderValue = 1;
        }
        if (usernameRegister.getText() == null || nameRegister.getText() == null || surnameRegister.getText() == null || mailRegister.getText() == null || usernameRegister.getText() == null || passwordRegister.getText().isEmpty()
                || passwordRegister.getText() == null || passwordAgainRegister.getText().isEmpty() || passwordAgainRegister.getText() == null || usernameRegister.getText().isEmpty() || mailRegister.getText().isEmpty() || nameRegister.getText().isEmpty() || surnameRegister.getText().isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Lütfen yıldızlı alanları doldurunuz.");
        } else {
            errorText.setVisible(false);
            int id = customerManager.getTotalUser(HotelReservationApplication.dbHelper, HotelReservationApplication.connection);
            id++;
            if (passwordRegister.getText().contentEquals(passwordAgainRegister.getText())) {
                Customer customer = new Customer(id, usernameRegister.getText(), passwordRegister.getText(), mailRegister.getText(), nameRegister.getText(),
                        surnameRegister.getText(), identityNumberRegister.getText(), birthdayRegister.getText(), phoneRegister.getText(), genderValue);
                int result = customerManager.register(HotelReservationApplication.dbHelper, HotelReservationApplication.connection, customer);
                if (result == 1) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("");
                    a.setHeaderText("Kaydınız yapılmıştır. Hoşgeldiniz " + customer.getUsername());
                    a.show();
                    FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 287, 382);
                    stage.setTitle("Login");
                    stage.setScene(scene);

                    stage.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("");
                    a.setHeaderText("Kaydınız yapılamadı, lütfen daha sonra tekrar deneyin ");
                    a.show();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("");
                a.setHeaderText("Girdiğiniz şifreler uyuşmuyor");
                a.show();
            }
        }

//        Stage stage = new Stage();


    }

    protected void sendMail() {
//        stage.setTitle("Email sender");
//        stage.setScene(scene);
//        stage.show();

        try {
            FileInputStream file = new FileInputStream("src/main/resources/com/example/hotelreservation/database.config");
            Properties prop = new Properties();
            prop.load(file);
            mailSenderMail = prop.getProperty("mailSender-mail");
            mailSenderPassword = prop.getProperty("mailSender-password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (mailSenderMail != null && mailSenderPassword != null) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com"); // Use your email provider's SMTP server
            props.put("mail.smtp.port", "587"); // Use the appropriate port
//            Session session = Session.getInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//            try {
//                Message message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(customer.getUsername()));
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getMail())));
//                message.setSubject("Subject of the email");
//                message.setText("Body of the email");
//
//                Transport.send(message);
//
//                System.out.println("Email sent successfully!");
//
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
        }
    }

    @FXML
    protected void onBackRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 287, 382);
        stage.setTitle("Login");
        stage.setScene(scene);

        stage.show();
    }




//
//    @FXML
//    private void changeMenu() {
//        if (menu == 1) {
//            menu = 2;
//            label1.setVisible(false);
//            label2.setVisible(false);
//            label3.setVisible(false);
//            label4.setVisible(false);
//            label5.setVisible(false);
//
//            roomTypeMenu.setVisible(false);
//
//            checkinDate.setVisible(false);
//            checkoutDate.setVisible(false);
//            currency.setVisible(false);
//            order.setVisible(false);
//            roomListTable.setVisible(false);
//            city.setVisible(false);
//            slider.setVisible(false);
//            price.setVisible(false);
//            userMenu.setVisible(false);
//            refresh.setVisible(false);
//            roomImageReserve.setVisible(true);
//            reserveButton.setVisible(true);
//            facilitiesReserve.setVisible(true);
//            descriptionReserve.setVisible(true);
//            priceReserve.setVisible(true);
//            hotelNameReserve.setVisible(true);
//            websiteReserve.setVisible(true);
//            telNoReserve.setVisible(true);
//            roomSizeReserve.setVisible(true);
//            rankReserve.setVisible(true);
//            addressReserve.setVisible(true);
//            backReserve.setVisible(true);
//            roomImageReserve.setDisable(false);
//            reserveButton.setDisable(false);
//            facilitiesReserve.setDisable(false);
//            descriptionReserve.setDisable(false);
//            priceReserve.setDisable(false);
//            hotelNameReserve.setDisable(false);
//            websiteReserve.setDisable(false);
//            telNoReserve.setDisable(false);
//            roomSizeReserve.setDisable(false);
//            rankReserve.setDisable(false);
//            addressReserve.setDisable(false);
//            backReserve.setDisable(false);
//
//
//            userLogin.setVisible(false);
//            userLogin.setDisable(true);
//            userMenu.setVisible(false);
//            userMenu.setDisable(true);
//            roomTypeMenu.setVisible(false);
//
//
//        } else {
//            menu = 1;
//            label1.setVisible(true);
//            label2.setVisible(true);
//            label3.setVisible(true);
//            label4.setVisible(true);
//            label5.setVisible(true);
//            roomTypeMenu.setVisible(true);
//            checkinDate.setVisible(true);
//            checkoutDate.setVisible(true);
//            currency.setVisible(true);
//            order.setVisible(true);
//            roomListTable.setVisible(true);
//            city.setVisible(true);
//            slider.setVisible(true);
//            price.setVisible(true);
//            refresh.setVisible(true);
//            roomImageReserve.setVisible(false);
//            reserveButton.setVisible(false);
//            facilitiesReserve.setVisible(false);
//            descriptionReserve.setVisible(false);
//            priceReserve.setVisible(false);
//            hotelNameReserve.setVisible(false);
//            websiteReserve.setVisible(false);
//            telNoReserve.setVisible(false);
//            roomSizeReserve.setVisible(false);
//            rankReserve.setVisible(false);
//            addressReserve.setVisible(false);
//            backReserve.setVisible(false);
//
//            roomTypeMenu.setVisible(true);
//
//            if (customer != null) {
//                userLogin.setVisible(false);
//                userLogin.setDisable(true);
//                userMenu.setVisible(true);
//                userMenu.setDisable(false);
//            } else {
//                userLogin.setVisible(true);
//                userLogin.setDisable(false);
//                userMenu.setVisible(false);
//                userMenu.setDisable(true);
//            }
//        }
//    }



    //    @FXML
//    protected void stageShowEvent() {
//        if (!isStageShowEventRun) {
//            isStageShowEventRun = true;
//            try {
//                this.onRefreshButtonClick();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            if(customer!=null) {
//                userMenu.setText(customer.getName() + " " + customer.getSurname());
//                userLogin.setVisible(false);
//                userLogin.setDisable(true);
//                userMenu.setVisible(true);
//                userMenu.setDisable(false);
//                LocalDate minCheckinDate = LocalDate.now();
//                LocalDate minCheckoutDate = LocalDate.now().plusDays(1);
//                checkinDate.setValue(minCheckinDate);
//                checkoutDate.setValue(minCheckoutDate);
//
//            }
//
//        }
//    }
    @FXML
    protected void stageShowEventUserInfo() {
        if (!isStageShowEventRun) {
            isStageShowEventRun = true;
            if (customer != null) {

                usernameUserInfo.setText(customer.getUsername());
                passwordUserInfo.setText(customer.getPassword());
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
                genderUserInfo.setText(gender);


            }

        }
    }

//    @FXML
//    protected void stageShowEventPaymentPage() {
//        if (!isStageShowEventRun) {
//            isStageShowEventRun = true;
//
//            checkinDatePayment.setValue(selectedCheckinDate);
//            checkoutDatePayment.setValue(selectedCheckoutDate);
//            hotelNamePayment.setText(selectedHotelName);
//            pricePayment.setText(selectedRoomPrice);
//
//
//        }
//    }

    public static void setCustomer(Customer _customer) {
        customer = _customer;
        isStageShowEventRun = false;
    }


    @FXML
    protected void onbackHome() {
        isStageShowEventRun = false;
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

    @FXML
    protected void onSaveUserInfo() {
        System.out.println(customer.getID());

    }


}