package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    static Stage stage;
    public static Customer customer;
    private static boolean isStageShowEventRun = false;

    @FXML
    protected DatePicker checkinDate;
    @FXML
    protected DatePicker checkoutDate;
    public static Room reservationRoom;
    static  int selectedRoomIndex;

    @FXML
    private SplitMenuButton order;
    @FXML
    private TextField city;

    @FXML
    private MenuButton userMenu;
    @FXML
    private Button userLogin;

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
    public SplitMenuButton roomTypeMenu;
    static TableColumn<HotelRoom, Integer> roomIDSend;

    String roomTypeName = "All Types";

    public static LocalDate selectedCheckinDate;
    public static LocalDate selectedCheckoutDate;
    public static String selectedHotelName;
    public static String selectedRoomPrice;

    ObservableList<HotelRoom> hotelRoomList = null;
    HotelsManager hotelsManager = new HotelsManager();
    ArrayList<HotelRoom> roomsArrayList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelRoomList = FXCollections.observableArrayList();
        roomsArrayList = hotelsManager.getAllRooms(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                "");
        hotelRoomList.addAll(roomsArrayList);
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
        roomListTable.setItems(hotelRoomList);
        if (customer != null) {
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

    @FXML
    protected void searchCity() {
        search();
    }

    private void search() {
        hotelRoomList = FXCollections.observableArrayList();
        String cityText = city.getText();
        if (roomsArrayList != null && !cityText.equals("") && !roomTypeMenu.getText().equals("All Types")) {
            cityText = cityText.toLowerCase().substring(0, 1).toUpperCase() +
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {

                String[] city = room.getAddress().split(",");
                if (city[city.length - 2].contains(cityText) && roomTypeName.equals(room.getRoomType())) {
                    hotelRoomList.add(room);
                }
            }

        } else if (roomsArrayList != null && cityText.equals("") && !roomTypeMenu.getText().equals("All Types")) {
            for (HotelRoom room : roomsArrayList) {

                if (roomTypeName.equals(room.getRoomType())) {
                    hotelRoomList.add(room);
                }
            }
        } else if (roomsArrayList != null && !cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            cityText = cityText.toLowerCase().substring(0, 1).toUpperCase() +
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {

                String[] city = room.getAddress().split(",");
                if (city[city.length - 2].contains(cityText)) {
                    hotelRoomList.add(room);
                }
            }
        } else if (roomsArrayList != null && cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            hotelRoomList.addAll(roomsArrayList);
        }
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));

        roomListTable.setItems(hotelRoomList);
    }

    protected void searchRoomType()
    {
        search();
    }

    @FXML
    protected void onAllSizeClick() {
        roomTypeMenu.setText("All Types");
        roomTypeName = "All Types";
        searchRoomType();
    }

    @FXML
    protected void onSuiteClick() {
        roomTypeMenu.setText(RoomTypes.Suite.name());
        roomTypeName = RoomTypes.Suite.name();
        searchRoomType();
    }

    @FXML
    protected void onKingSizeClick() {
        roomTypeMenu.setText(RoomTypes.King.name());
        roomTypeName = RoomTypes.King.name();
        searchRoomType();
    }

    @FXML
    protected void onQuadSizeClick() {
        roomTypeMenu.setText(RoomTypes.Quad.name());
        roomTypeName = RoomTypes.Quad.name();
        searchRoomType();
    }

    @FXML
    protected void onTripleSizeClick() {
        roomTypeMenu.setText(RoomTypes.Triple.name());
        roomTypeName = RoomTypes.Triple.name();
        searchRoomType();
    }

    @FXML
    protected void onOnePersonRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.OnePersonRoom.name());
        roomTypeName = RoomTypes.OnePersonRoom.name();
        searchRoomType();
    }

    @FXML
    protected void onTwinRoomSizeClick() {
        roomTypeMenu.setText(RoomTypes.TwinRoom.name());
        roomTypeName = RoomTypes.TwinRoom.name();
        searchRoomType();
    }

    @FXML
    protected void onUserMenu1() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("UserInfo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 453, 604);
            stage.setTitle(customer.getName() + " " + customer.getSurname());
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();
            isStageShowEventRun = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUserMenu2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("OldReservations.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Old Hotel Reservations");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onUserLogin() {
        isStageShowEventRun = false;
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
        hotelRoomList.addAll(roomsArrayList);
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));

        roomListTable.setItems(hotelRoomList);

    }

    @FXML
    protected void getItem() throws IOException {
        selectedRoomIndex = roomListTable.getSelectionModel().getSelectedIndex();
        reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                roomID.getCellData(selectedRoomIndex));
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                reservationRoom.getHotelID());
        if (selectedRoomIndex <= -1) {
            return;
        }
        roomIDSend = roomID;
        selectedCheckinDate = checkinDate.getValue();
        selectedCheckoutDate = checkoutDate.getValue();
        selectedHotelName = hotel.getName();
        selectedRoomPrice = reservationRoom.getPrice().toString();

        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("payment-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reservation Detail");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    protected void onUserMenu3() {
        HotelReservationApplication.isLoggedIn = false;
        userMenu.setVisible(false);
        userMenu.setDisable(true);
        userLogin.setVisible(true);
        userLogin.setDisable(false);
        customer = null;
    }
}
