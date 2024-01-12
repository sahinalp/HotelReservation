package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Hotel;
import com.example.hotelreservation.Entities.HotelRoom;
import com.example.hotelreservation.Entities.Room;
import com.example.hotelreservation.Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ArrayList;
import java.util.Properties;

public class HotelReservationController {
    public Label errorText;
    public SplitMenuButton roomTypeMenu;
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


    @FXML
    private TableView<HotelRoom> roomListTable;
    @FXML
    private TableColumn<HotelRoom, Integer> roomID;
    @FXML
    private TableColumn<HotelRoom, String> hotelName;
    @FXML
    private TableColumn<HotelRoom, String> roomType;
    @FXML
    private TableColumn<HotelRoom, String> address;
    @FXML
    private TableColumn<HotelRoom, Double> hotelRank;
    @FXML
    private TableColumn<HotelRoom, String> priceCurrency;

    ObservableList<HotelRoom> list;

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private ChoiceBox roomTypeChoiceBox;
    @FXML
    private DatePicker checkinDate;
    @FXML
    private DatePicker checkoutDate;
    @FXML
    private ChoiceBox currency;
    @FXML
    private SplitMenuButton order;
    @FXML
    private TextField city;
    @FXML
    private Slider slider;
    @FXML
    private TextField price;
    @FXML
    private SplitMenuButton userMenu;
    @FXML
    private Button refresh;
    @FXML
    private Button backReserve;
    @FXML
    private ImageView roomImageReserve;
    @FXML
    private Button reserveButton;
    @FXML
    private TextArea facilitiesReserve;
    @FXML
    private TextArea descriptionReserve;
    @FXML
    private TextField priceReserve;
    @FXML
    private TextField hotelNameReserve;
    @FXML
    private TextField websiteReserve;
    @FXML
    private TextField telNoReserve;
    @FXML
    private TextField roomSizeReserve;
    @FXML
    private TextField rankReserve;
    @FXML
    private TextArea addressReserve;
    int index;
    private boolean isStageShowEventRun = false;
    CustomerManager customerManager = new CustomerManager();
    HotelsManager hotelsManager = new HotelsManager();
    static Stage stage;
    Stage home = new Stage();
    Stage register = new Stage();

    private static Customer customer;

    private String mailSenderMail = null;
    private String mailSenderPassword = null;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        boolean canLogin = false;
        canLogin = customerManager.login(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                username.getText(), password.getText());

        if (canLogin) {

            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Login");
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
        ||passwordRegister.getText()==null ||passwordAgainRegister.getText().isEmpty() ||passwordAgainRegister.getText()==null || usernameRegister.getText().isEmpty() || mailRegister.getText().isEmpty() || nameRegister.getText().isEmpty() || surnameRegister.getText().isEmpty()) {
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

    @FXML
    protected void onSortHotelNameAscending() {
        hotelName.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(hotelName);
        roomListTable.sort();
        order.setText("Hotel Name Ascending");
    }

    @FXML
    protected void onSortHotelNameDescending() {
        hotelName.setSortType(TableColumn.SortType.DESCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(hotelName);
        roomListTable.sort();
        order.setText("Hotel Name Descending");
    }

    @FXML
    protected void onRoomTypeAscending() {
        roomType.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(roomType);
        roomListTable.sort();
        order.setText("Room Type Ascending");
    }

    @FXML
    protected void onRoomTypeNameDescending() {
        roomType.setSortType(TableColumn.SortType.DESCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(roomType);
        roomListTable.sort();
        order.setText("Room Type Descending");
    }

    @FXML
    protected void onAddressAscending() {
        address.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(address);
        roomListTable.sort();
        order.setText("Address Ascending");
    }

    @FXML
    protected void onAddressDescending() {
        address.setSortType(TableColumn.SortType.DESCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(address);
        roomListTable.sort();
        order.setText("Address Descending");
    }

    @FXML
    protected void onSortHotelRankAscending() {
        hotelRank.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(hotelRank);
        roomListTable.sort();
        order.setText("Rank Ascending");
    }

    @FXML
    protected void onSortHotelRankDescending() {
        hotelRank.setSortType(TableColumn.SortType.DESCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(hotelRank);
        roomListTable.sort();
        order.setText("Rank Descending");
    }

    @FXML
    protected void onSortPriceCurrencyAscending() {
        priceCurrency.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(priceCurrency);
        roomListTable.sort();
        order.setText("Price Ascending");
    }

    @FXML
    protected void onSortPriceCurrencyDescending() {
        priceCurrency.setSortType(TableColumn.SortType.DESCENDING);
        roomListTable.getSortOrder().clear();
        roomListTable.getSortOrder().add(priceCurrency);
        roomListTable.sort();
        order.setText("Price Descending");
    }

    @FXML
    protected void onRefreshButtonClick() throws IOException {
        ArrayList<HotelRoom> roomsArrayList;
        list = FXCollections.observableArrayList();
        roomsArrayList = hotelsManager.getAllRooms(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                "");
        for (HotelRoom room : roomsArrayList) {
//            roomListTable.getItems().add(room.hotelName,room.roomType,room.address,room.hotelRank,price);
//            list.add(new HotelRoom(room.roomID,room.hotelName,room.roomType,room.address,room.hotelRank,room.priceCurrency));
            list.add(room);
        }
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));

        roomListTable.setItems(list);

    }

    @FXML
    protected void getItem() {
        index = roomListTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

//        System.out.println(hotelName.getCellData(index)+" "+ address.getCellData(index));
        Room room = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                roomID.getCellData(index));
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                room.getHotelID());

        changeMenu();

        Image image = new Image(room.getPhoto());
        roomImageReserve.setImage(image);
        facilitiesReserve.setText(room.getFacilities());
        descriptionReserve.setText(room.getDescription());
        priceReserve.setText(room.getPrice().toString());
        hotelNameReserve.setText(hotel.getName());
        websiteReserve.setText(hotel.getWebsite());
        telNoReserve.setText(hotel.getTelNo());
        roomSizeReserve.setText(String.valueOf(room.getRoomSize()));
        rankReserve.setText(String.valueOf(hotel.getRank()));
        addressReserve.setText(hotel.getAddress());


    }

    @FXML
    private void changeMenu() {
        if (menu == 1) {
            menu = 2;
            label1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            roomTypeChoiceBox.setVisible(false);
            checkinDate.setVisible(false);
            checkoutDate.setVisible(false);
            currency.setVisible(false);
            order.setVisible(false);
            roomListTable.setVisible(false);
            city.setVisible(false);
            slider.setVisible(false);
            price.setVisible(false);
            userMenu.setVisible(false);
            refresh.setVisible(false);
            roomImageReserve.setVisible(true);
            reserveButton.setVisible(true);
            facilitiesReserve.setVisible(true);
            descriptionReserve.setVisible(true);
            priceReserve.setVisible(true);
            hotelNameReserve.setVisible(true);
            websiteReserve.setVisible(true);
            telNoReserve.setVisible(true);
            roomSizeReserve.setVisible(true);
            rankReserve.setVisible(true);
            addressReserve.setVisible(true);
            backReserve.setVisible(true);

            roomImageReserve.setDisable(false);
            reserveButton.setDisable(false);
            facilitiesReserve.setDisable(false);
            descriptionReserve.setDisable(false);
            priceReserve.setDisable(false);
            hotelNameReserve.setDisable(false);
            websiteReserve.setDisable(false);
            telNoReserve.setDisable(false);
            roomSizeReserve.setDisable(false);
            rankReserve.setDisable(false);
            addressReserve.setDisable(false);
            backReserve.setDisable(false);

        } else {
            menu = 1;
            label1.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            roomTypeChoiceBox.setVisible(true);
            checkinDate.setVisible(true);
            checkoutDate.setVisible(true);
            currency.setVisible(true);
            order.setVisible(true);
            roomListTable.setVisible(true);
            city.setVisible(true);
            slider.setVisible(true);
            price.setVisible(true);
            userMenu.setVisible(true);
            refresh.setVisible(true);
            roomImageReserve.setVisible(false);
            reserveButton.setVisible(false);
            facilitiesReserve.setVisible(false);
            descriptionReserve.setVisible(false);
            priceReserve.setVisible(false);
            hotelNameReserve.setVisible(false);
            websiteReserve.setVisible(false);
            telNoReserve.setVisible(false);
            roomSizeReserve.setVisible(false);
            rankReserve.setVisible(false);
            addressReserve.setVisible(false);
            backReserve.setVisible(false);
        }
    }

    @FXML
    private void stageShowEvent() {
        if (!isStageShowEventRun) {
            isStageShowEventRun = true;
            try {
                this.onRefreshButtonClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userMenu.setText(customer.getName() + " " + customer.getSurname());
        }
    }

    public static void setCustomer(Customer _customer) {
        customer = _customer;
    }

    @FXML
    protected void onSuiteClick() {
        roomTypeMenu.setText(RoomTypes.Suite.name());
    }

    @FXML
    protected void onKingSizeClick() {
        roomTypeMenu.setText(RoomTypes.King.name());
    }

    @FXML
    protected void onQuadSizeClick() {
        roomTypeMenu.setText(RoomTypes.Quad.name());
    }

    @FXML
    protected void onTripleSizeClick() {
        roomTypeMenu.setText(RoomTypes.Triple.name());
    }

    @FXML
    protected void onOnePersonRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.OnePersonRoom.name());
    }

    @FXML
    protected void onTwinRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.TwinRoom.name());
    }


}