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
        boolean isUser = true;
        try
        {

            canLogin = customerManager.login(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                    username.getText(), password.getText());
        }
        catch (Exception e)
        {
            isUser=false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!!");
            a.setHeaderText("User couldn't found");
            a.show();
        }

        if (canLogin) {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Hotel Reservation");
            stage.setScene(scene);


            HotelReservationApplication.isLoggedIn = true;
            stage.centerOnScreen();
            stage.show();

//            Platform.runLater(() -> this.stageShowEvent());


        } else if(isUser) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!!");
            a.setHeaderText("Wrong Password");
            a.show();
        }
    }

    @FXML
    protected void onContinueWithoutLogin() throws IOException {
        HotelReservationApplication.isLoggedIn = false;

        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("home.fxml"));
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

    public static void setCustomer(Customer _customer) {
        customer = _customer;
        isStageShowEventRun = false;
    }





}