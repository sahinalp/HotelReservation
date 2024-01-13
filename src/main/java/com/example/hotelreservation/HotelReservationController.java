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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class HotelReservationController {
    public Label errorText;
    public SplitMenuButton roomTypeMenu;

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

    ObservableList<HotelRoom> hotelRoomList = null;
    ObservableList<OldReservations> oldReservationsList = null;
    ArrayList<HotelRoom> roomsArrayList=null;
    ArrayList<OldReservations> oldReservationsArrayList=null;

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
    private MenuButton userMenu;
    @FXML
    private Button userLogin;
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
    @FXML
    private DatePicker checkinDatePayment;
    @FXML
    private DatePicker checkoutDatePayment;
    @FXML
    private TextField hotelNamePayment;
    @FXML
    private TextField pricePayment;

    @FXML
    private TableColumn<OldReservations,String> hotelNameOldReservations;
    @FXML
    private TableColumn<OldReservations,String> roomTypeOldReservations;
    @FXML
    private TableColumn<OldReservations,String> addressOldReservations;
    @FXML
    private TableColumn<OldReservations,String> checkinDateOldReservations;
    @FXML
    private TableColumn<OldReservations,String> checkoutDateOldReservations;
    @FXML
    private TableColumn<OldReservations,String> priceCurrencyOldReservations;
    @FXML
    private TableView<OldReservations> roomListTableOldReservations;

    int index;
    private static boolean isStageShowEventRun = false;
    CustomerManager customerManager = new CustomerManager();
    HotelsManager hotelsManager = new HotelsManager();
    ReservationManager reservationManager = new ReservationManager();
    static Stage stage;
    private static Customer customer;

    private String mailSenderMail = null;
    private String mailSenderPassword = null;

    String roomTypeName ="All Types";
    private static Room reservationRoom;
    private static LocalDate selectedCheckinDate;
    private static LocalDate selectedCheckoutDate;
    private static String selectedHotelName;
    private static String selectedRoomPrice;

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
        hotelRoomList = FXCollections.observableArrayList();
        roomsArrayList = hotelsManager.getAllRooms(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                "");
        for (HotelRoom room : roomsArrayList) {
            hotelRoomList.add(room);
        }
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));

        roomListTable.setItems(hotelRoomList);

    }

    @FXML
    protected void getItem() {
        index = roomListTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                roomID.getCellData(index));
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                reservationRoom.getHotelID());

        selectedCheckinDate = checkinDate.getValue();
        selectedCheckoutDate = checkoutDate.getValue();

        changeMenu();

        Image image = new Image(reservationRoom.getPhoto());
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


    }
    @FXML
    protected void searchCity()
    {
        search();
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

            roomTypeMenu.setVisible(false);

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


            userLogin.setVisible(false);
            userLogin.setDisable(true);
            userMenu.setVisible(false);
            userMenu.setDisable(true);
            roomTypeMenu.setVisible(false);


        } else {
            menu = 1;
            label1.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            roomTypeMenu.setVisible(true);
            checkinDate.setVisible(true);
            checkoutDate.setVisible(true);
            currency.setVisible(true);
            order.setVisible(true);
            roomListTable.setVisible(true);
            city.setVisible(true);
            slider.setVisible(true);
            price.setVisible(true);
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

            roomTypeMenu.setVisible(true);

            if(customer!=null) {
                userLogin.setVisible(false);
                userLogin.setDisable(true);
                userMenu.setVisible(true);
                userMenu.setDisable(false);
            }
            else
            {
                userLogin.setVisible(true);
                userLogin.setDisable(false);
                userMenu.setVisible(false);
                userMenu.setDisable(true);
            }
        }
    }

    @FXML
    protected void onMakeReservation(){
        //Room room, Customer customer, String checkInDate, String checkOutDate
        String[] checkinDateList=checkinDatePayment.getValue().toString().split("-");
        String[] checkoutDateList=checkoutDatePayment.getValue().toString().split("-");
        String checkinDate = checkinDateList[2]+"/"+checkinDateList[1]+"/"+checkinDateList[0];
        String checkoutDate=checkoutDateList[2]+"/"+checkoutDateList[1]+"/"+checkoutDateList[0];;

       int sonuc= reservationManager.makeReservation(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
               reservationRoom,customer,checkinDate,checkoutDate);
        System.out.println(sonuc);
    }

    @FXML
    protected void stageShowEvent() {
        if (!isStageShowEventRun) {
            isStageShowEventRun = true;
            try {
                this.onRefreshButtonClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(customer!=null) {
                userMenu.setText(customer.getName() + " " + customer.getSurname());
                userLogin.setVisible(false);
                userLogin.setDisable(true);
                userMenu.setVisible(true);
                userMenu.setDisable(false);
                LocalDate minCheckinDate = LocalDate.now();
                LocalDate minCheckoutDate = LocalDate.now().plusDays(1);
                checkinDate.setValue(minCheckinDate);
                checkoutDate.setValue(minCheckoutDate);

            }

        }
    }
    @FXML
    protected void stageShowEventUserInfo()
    {
        if (!isStageShowEventRun) {
            isStageShowEventRun = true;
            if(customer!=null) {

                usernameUserInfo.setText(customer.getUsername());
                passwordUserInfo.setText(customer.getPassword());
                mailUserInfo.setText(customer.getMail());
                nameUserInfo.setText(customer.getName());
                surnameUserInfo.setText(customer.getSurname());
                identificationNumberUserInfo.setText(customer.getIdentificationNumber());
                birthDateUserInfo.setText(customer.getBirthDate());
                phoneUserInfo.setText(customer.getPhone());
                String gender = "Other";
                if(customer.getGender()==0)
                    gender="Woman";
                else if(customer.getGender()==1)
                    gender="Man";
                genderUserInfo.setText(gender);


            }

        }
    }

    @FXML
    protected void stageShowEventPaymentPage()
    {
        if (!isStageShowEventRun) {
            isStageShowEventRun = true;

            checkinDatePayment.setValue(selectedCheckinDate);
            checkoutDatePayment.setValue(selectedCheckoutDate);
            hotelNamePayment.setText(selectedHotelName);
            pricePayment.setText(selectedRoomPrice);


        }
    }

    @FXML
    protected void stageShowEventOldReservations()
    {
        if (!isStageShowEventRun) {
            oldReservationsList = FXCollections.observableArrayList();
            oldReservationsArrayList = reservationManager.getAllOldReservations(HotelReservationApplication.dbHelper,
                    HotelReservationApplication.connection,customer.getID());
            isStageShowEventRun = true;
            for (OldReservations reservation : oldReservationsArrayList) {

                oldReservationsList.add(reservation);

            }
            hotelNameOldReservations.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
            roomTypeOldReservations.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            addressOldReservations.setCellValueFactory(new PropertyValueFactory<>("address"));
            checkinDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
            checkoutDateOldReservations.setCellValueFactory(new PropertyValueFactory<>("checkoutDay"));
            priceCurrencyOldReservations.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));


            roomListTableOldReservations.setItems(oldReservationsList);



        }
    }

    public static void setCustomer(Customer _customer) {
        customer = _customer;
        isStageShowEventRun=false;
    }
    @FXML
    protected void onAllSizeClick(){
        roomTypeMenu.setText("All Types");
        roomTypeName="All Types";
        searchRoomType();
    }

    @FXML
    protected void onSuiteClick() {
        roomTypeMenu.setText(RoomTypes.Suite.name());
        roomTypeName=RoomTypes.Suite.name();
        searchRoomType();
    }

    @FXML
    protected void onKingSizeClick() {
        roomTypeMenu.setText(RoomTypes.King.name());
        roomTypeName=RoomTypes.King.name();
        searchRoomType();
    }

    @FXML
    protected void onQuadSizeClick() {
        roomTypeMenu.setText(RoomTypes.Quad.name());
        roomTypeName=RoomTypes.Quad.name();
        searchRoomType();
    }

    @FXML
    protected void onTripleSizeClick() {
        roomTypeMenu.setText(RoomTypes.Triple.name());
        roomTypeName=RoomTypes.Triple.name();
        searchRoomType();
    }

    @FXML
    protected void onOnePersonRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.OnePersonRoom.name());
        roomTypeName=RoomTypes.OnePersonRoom.name();
        searchRoomType();
    }

    @FXML
    protected void onTwinRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.TwinRoom.name());
        roomTypeName=RoomTypes.TwinRoom.name();
        searchRoomType();
    }

    protected void searchRoomType()
    {
        search();
    }
    private void search()
    {
        hotelRoomList = FXCollections.observableArrayList();
        String cityText = city.getText();
        if(roomsArrayList!=null && !cityText.equals("") && !roomTypeMenu.getText().equals("All Types"))
        {
            cityText= cityText.toLowerCase().substring(0,1).toUpperCase()+
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {

                String[] city = room.getAddress().split(",");
                if(city[city.length-2].contains(cityText) && roomTypeName.equals(room.getRoomType()))
                {
                    hotelRoomList.add(room);
                }
            }

        }
        else if(roomsArrayList!=null && cityText.equals("") && !roomTypeMenu.getText().equals("All Types")) {
            for (HotelRoom room : roomsArrayList) {

                if (roomTypeName.equals(room.getRoomType())) {
                    hotelRoomList.add(room);
                }
            }
        }
        else if(roomsArrayList!=null && !cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            cityText= cityText.toLowerCase().substring(0,1).toUpperCase()+
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {

                String[] city = room.getAddress().split(",");
                if(city[city.length-2].contains(cityText))
                {
                    hotelRoomList.add(room);
                }
            }
        }
        else if(roomsArrayList!=null && cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            for (HotelRoom room : roomsArrayList) {

                hotelRoomList.add(room);

            }
        }
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));

        roomListTable.setItems(hotelRoomList);
    }

    @FXML
    protected void onUserMenu1()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("UserInfo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 453, 604);
            stage.setTitle(customer.getName()+" "+customer.getSurname());
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();
            isStageShowEventRun=false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onUserMenu2()
    {
        try {
            isStageShowEventRun=false;
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("OldReservations.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Old Hotel Reservations");
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onUserMenu3()
    {
        HotelReservationApplication.isLoggedIn = false;
        userMenu.setVisible(false);
        userMenu.setDisable(true);
        userLogin.setVisible(true);
        userLogin.setDisable(false);
        customer=null;
    }
    @FXML
    protected void onUserLogin()
    {
        isStageShowEventRun=false;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 287, 382);
            stage.setTitle("Hotel Reservation");
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onbackHome()
    {
        isStageShowEventRun=false;
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
    protected void onSaveUserInfo()
    {
        System.out.println(customer.getID());

    }
    @FXML
    protected void goPaymentPage() {
        if (customer != null) {
            selectedHotelName = hotelNameReserve.getText();
            selectedRoomPrice = priceReserve.getText();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("PaymentPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 894, 604);
                stage.setTitle("Payment Page");
                stage.setScene(scene);

                stage.centerOnScreen();
                stage.show();
                isStageShowEventRun = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!!");
            a.setHeaderText("Please login first");
            a.show();
        }
    }
    @FXML
    protected void goMainMenu()
    {
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



}