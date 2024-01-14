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
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    static Stage stage;                                     // Stage for all scenes
    public static Customer customer;                        // Customer object for logged in user
    private static boolean isStageShowEventRun = false;     // Boolean for checking if stage show event is run

    protected static int dateDiff;                          // Difference between checkin and checkout dates
    @FXML
    protected DatePicker checkinDate;                       // Checkin date
    @FXML
    protected DatePicker checkoutDate;                      // Checkout date
    public static Room reservationRoom;                     // Room object for reservation
    static int selectedRoomIndex;                          // Index of selected room

    @FXML
    private SplitMenuButton order;                          // SplitMenuButton for sorting
    @FXML
    private TextField city;                                 // TextField for city search

    @FXML
    private MenuButton userMenu;                             // MenuButton for logged in user
    @FXML
    private Button userLogin;                                // Button for login

    @FXML
    private TableView<HotelRoom> roomListTable;             // TableView for room list
    @FXML
    private TableColumn<HotelRoom, Integer> roomID;         // TableColumn for room ID
    @FXML
    private TableColumn<HotelRoom, String> hotelName;       // TableColumn for hotel name
    @FXML
    private TableColumn<HotelRoom, String> roomType;        // TableColumn for room type
    @FXML
    private TableColumn<HotelRoom, String> address;         // TableColumn for address
    @FXML
    private TableColumn<HotelRoom, Double> hotelRank;       // TableColumn for hotel rank
    @FXML
    private TableColumn<HotelRoom, String> priceCurrency;   // TableColumn for price
    public SplitMenuButton roomTypeMenu;                    // SplitMenuButton for room type
    static TableColumn<HotelRoom, Integer> roomIDSend;      // TableColumn for sending room ID

    String roomTypeName = "All Types";                      // String for room type name

    public static LocalDate selectedCheckinDate;            // LocalDate for selected checkin date
    public static LocalDate selectedCheckoutDate;           // LocalDate for selected checkout date
    public static String selectedHotelName;                 // String for selected hotel name
    public static String selectedRoomPrice;                 // String for selected room price

    ObservableList<HotelRoom> hotelRoomList = null;         // ObservableList for hotel room list
    HotelsManager hotelsManager = new HotelsManager();      // HotelsManager object for database operations
    ArrayList<HotelRoom> roomsArrayList = null;             // ArrayList for hotel rooms

    // Initialize method for HomeController
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // date picker for checkin disable picking previous dates
        checkinDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
        // date picker for checkout disable picking previous dates
        checkoutDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
        LocalDate minCheckinDate = LocalDate.now();                         // LocalDate for minimum checkin date
        LocalDate minCheckoutDate = LocalDate.now().plusDays(1);  // LocalDate for minimum checkout date
        checkinDate.setValue(minCheckinDate);                               // Set checkin date to minimum checkin date
        checkoutDate.setValue(minCheckoutDate);                             // Set checkout date to minimum checkout date
        dateDiff = 1;                                                       // Set date difference to 1
        hotelRoomList = FXCollections.observableArrayList();                // Initialize hotel room list
        roomsArrayList = hotelsManager.getAllRooms(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                "", dateDiff);                                         // Get all rooms from database
        hotelRoomList.addAll(roomsArrayList);
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));           // Set room ID cell value factory
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));     // Set hotel name cell value factory
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));       // Set room type cell value factory
        address.setCellValueFactory(new PropertyValueFactory<>("address"));         // Set address cell value factory
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank"));     // Set hotel rank cell value factory
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("price"));     // Set price cell value factory
        roomListTable.setItems(hotelRoomList);                                         // Set room list table items
        if (customer != null) {                                                        // If customer is not null
            userMenu.setText(customer.getName() + " " + customer.getSurname());        // Set user menu text to customer name and surname
            userLogin.setVisible(false);                                               // Set user login button to invisible
            userLogin.setDisable(true);                                                // Set user login button to disable
            userMenu.setVisible(true);                                                 // Set user menu to visible
            userMenu.setDisable(false);                                                // Set user menu to disable
        }
    }

    @FXML
    protected void searchCity() {
        search();
    } // Search method for searching rooms

    // Search method for searching rooms
    private void search() {
        // Initialize hotel room list
        hotelRoomList = FXCollections.observableArrayList();
        String cityText = city.getText();               // Get city text from city TextField
        // If roomsArrayList is not null and city text is not empty and room type is not "All Types"
        if (roomsArrayList != null && !cityText.equals("") && !roomTypeMenu.getText().equals("All Types")) {
            // Convert city text to lowercase and capitalize first letter
            cityText = cityText.toLowerCase().substring(0, 1).toUpperCase() +
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {
                // Split address by comma
                String[] city = room.getAddress().split(",");
                // If city contains city text and room type is equal to room type name
                if (city[city.length - 2].contains(cityText) && roomTypeName.equals(room.getRoomType())) {
                    // Add room to hotel room list
                    hotelRoomList.add(room);
                }
            }
        // if roomArrayList is not null and empty and room type is not "All Types"
        } else if (roomsArrayList != null && cityText.equals("") && !roomTypeMenu.getText().equals("All Types")) {
            // For each room in roomsArrayList
            for (HotelRoom room : roomsArrayList) {
                // If room type is equal to room type name
                if (roomTypeName.equals(room.getRoomType())) {
                    // Add room to hotel room list
                    hotelRoomList.add(room);
                }
            }
            // If roomsArrayList is not null and city text is not empty and room type is "All Types"
        } else if (roomsArrayList != null && !cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            // Convert city text to lowercase and capitalize first letter
            cityText = cityText.toLowerCase().substring(0, 1).toUpperCase() +
                    cityText.toLowerCase().substring(1).toLowerCase();
            for (HotelRoom room : roomsArrayList) {
                // Split address by comma
                String[] city = room.getAddress().split(",");
                // If city contains city text
                if (city[city.length - 2].contains(cityText)) {
                    // Add room to hotel room list
                    hotelRoomList.add(room);
                }
            }
            // If roomsArrayList is not null and city text is empty and room type is "All Types"
        } else if (roomsArrayList != null && cityText.equals("") && roomTypeMenu.getText().equals("All Types")) {
            // Add all rooms to hotel room list
            hotelRoomList.addAll(roomsArrayList);
        }
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));       // Set room ID cell value factory
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName")); // Set hotel name cell value factory
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));   // Set room type cell value factory
        address.setCellValueFactory(new PropertyValueFactory<>("address"));     // Set address cell value factory
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank")); // Set hotel rank cell value factory
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency")); // Set price cell value factory
        // Set room list table items
        roomListTable.setItems(hotelRoomList);
    }


    protected void searchRoomType() {
        search();
    }  // Search method for searching rooms

    //
    @FXML
    protected void onAllSizeClick() {
        // Set room type menu text to "All Types"
        roomTypeMenu.setText("All Types");
        roomTypeName = "All Types";
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onSuiteClick() {
        // Set room type menu text to "Suite"
        roomTypeMenu.setText(RoomTypes.Suite.name());
        roomTypeName = RoomTypes.Suite.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onKingSizeClick() {
        // Set room type menu text to "King"
        roomTypeMenu.setText(RoomTypes.King.name());
        roomTypeName = RoomTypes.King.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onQuadSizeClick() {
        //  Set room type menu text to "Quad"
        roomTypeMenu.setText(RoomTypes.Quad.name());
        roomTypeName = RoomTypes.Quad.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onTripleSizeClick() {
        // Set room type menu text to "Triple"
        roomTypeMenu.setText(RoomTypes.Triple.name());
        roomTypeName = RoomTypes.Triple.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onOnePersonRoomSizeClick() {
        // Set room type menu text to "One Person"
        roomTypeMenu.setText(RoomTypes.OnePersonRoom.name());
        roomTypeName = RoomTypes.OnePersonRoom.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onTwinRoomSizeClick() {
        // Set room type menu text to "Twin"
        roomTypeMenu.setText(RoomTypes.TwinRoom.name());
        roomTypeName = RoomTypes.TwinRoom.name();
        // Search room type
        searchRoomType();
    }

    @FXML
    protected void onUserMenu1() {
        try {
            // create a new FXML loader for profile.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("profile.fxml"));
            // create a new scene with root and set the stage
            Scene scene = new Scene(fxmlLoader.load(), 453, 604);
            stage.setTitle(customer.getName() + " " + customer.getSurname());
            stage.setScene(scene);
            stage.centerOnScreen();     // center the stage
            stage.show();               // show the stage
            isStageShowEventRun = false;    // set isStageShowEventRun to false
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUserMenu2() {
        try {
            // create a new FXML loader for my-reservations.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("my-reservations.fxml"));
            // create a new scene with root and set the stage
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Old Hotel Reservations");
            stage.setScene(scene);
            stage.centerOnScreen(); // center the stage
            stage.show();           // show the stage

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onUserLogin() {
        isStageShowEventRun = false;            // set isStageShowEventRun to false
        try {
            // create a new FXML loader for login-view.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 287, 382);       // create a new scene with root and set the stage
            stage.setTitle("Hotel Reservation");                // set the stage title
            stage.setScene(scene);                              // set the scene
            stage.centerOnScreen();                             // center the stage
            stage.show();                                       // show the stage
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onSortHotelNameAscending() {
        // Set hotel name sort type to ascending
        hotelName.setSortType(TableColumn.SortType.ASCENDING);
        roomListTable.getSortOrder().clear();           // Clear room list table sort order
        roomListTable.getSortOrder().add(hotelName);    // Add hotel name to room list table sort order
        roomListTable.sort();                           // Sort room list table
        order.setText("Hotel Name Ascending");          // Set order text to "Hotel Name Ascending"
    }

    @FXML
    protected void onSortHotelNameDescending() {
        hotelName.setSortType(TableColumn.SortType.DESCENDING); // Set hotel name sort type to descending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(hotelName);            // Add hotel name to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Hotel Name Descending");                 // Set order text to "Hotel Name Descending"
    }

    @FXML
    protected void onRoomTypeAscending() {
        roomType.setSortType(TableColumn.SortType.ASCENDING);   // Set room type sort type to ascending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(roomType);             // Add room type to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Room Type Ascending");                   // Set order text to "Room Type Ascending"
    }

    @FXML
    protected void onRoomTypeNameDescending() {
        roomType.setSortType(TableColumn.SortType.DESCENDING);  // Set room type sort type to descending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(roomType);             // Add room type to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Room Type Descending");                  // Set order text to "Room Type Descending"
    }

    @FXML
    protected void onAddressAscending() {
        address.setSortType(TableColumn.SortType.ASCENDING);    // Set address sort type to ascending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(address);              // Add address to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Address Ascending");                     // Set order text to "Address Ascending"
    }

    @FXML
    protected void onAddressDescending() {
        address.setSortType(TableColumn.SortType.DESCENDING);   // Set address sort type to descending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(address);              // Add address to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Address Descending");                    // Set order text to "Address Descending"
    }

    @FXML
    protected void onSortHotelRankAscending() {
        hotelRank.setSortType(TableColumn.SortType.ASCENDING);  // Set hotel rank sort type to ascending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(hotelRank);            // Add hotel rank to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Rank Ascending");                        // Set order text to "Rank Ascending"
    }

    @FXML
    protected void onSortHotelRankDescending() {
        hotelRank.setSortType(TableColumn.SortType.DESCENDING); // Set hotel rank sort type to descending
        roomListTable.getSortOrder().clear();                   // Clear room list table sort order
        roomListTable.getSortOrder().add(hotelRank);            // Add hotel rank to room list table sort order
        roomListTable.sort();                                   // Sort room list table
        order.setText("Rank Descending");                       // Set order text to "Rank Descending"
    }

    @FXML
    protected void onSortPriceCurrencyAscending() {
        priceCurrency.setSortType(TableColumn.SortType.ASCENDING);  // Set price sort type to ascending
        roomListTable.getSortOrder().clear();                       // Clear room list table sort order
        roomListTable.getSortOrder().add(priceCurrency);            // Add price to room list table sort order
        roomListTable.sort();                                       // Sort room list table
        order.setText("Price Ascending");                           // Set order text to "Price Ascending"
    }

    @FXML
    protected void onSortPriceCurrencyDescending() {
        priceCurrency.setSortType(TableColumn.SortType.DESCENDING); // Set price sort type to descending
        roomListTable.getSortOrder().clear();                       // Clear room list table sort order
        roomListTable.getSortOrder().add(priceCurrency);            // Add price to room list table sort order
        roomListTable.sort();                                       // Sort room list table
        order.setText("Price Descending");                          // Set order text to "Price Descending"
    }

    @FXML
    protected void onRefreshButtonClick() throws IOException {
        // Get date difference between checkin and checkout dates
        dateDiff = (int) Duration.between(checkinDate.getValue().atStartOfDay(), checkoutDate.getValue().atStartOfDay()).toDays();
        // Initialize hotel room list
        hotelRoomList = FXCollections.observableArrayList();
        // Get all rooms from database
        roomsArrayList = hotelsManager.getAllRooms(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                "", dateDiff);
        // Add all rooms to hotel room list
        hotelRoomList.addAll(roomsArrayList);
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));       // Set room ID cell value factory
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName")); // Set hotel name cell value factory
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));   // Set room type cell value factory
        address.setCellValueFactory(new PropertyValueFactory<>("address"));     // Set address cell value factory
        hotelRank.setCellValueFactory(new PropertyValueFactory<>("hotelRank")); // Set hotel rank cell value factory
        priceCurrency.setCellValueFactory(new PropertyValueFactory<>("priceCurrency")); // Set price cell value factory

        roomListTable.setItems(hotelRoomList);                               // Set room list table items

    }

    @FXML
    protected void getItem() throws IOException {
        // Get selected room index
        selectedRoomIndex = roomListTable.getSelectionModel().getSelectedIndex();
        // Get room from database
        reservationRoom = hotelsManager.getRoom(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                roomID.getCellData(selectedRoomIndex));
        // Get room ID
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper, HotelReservationApplication.connection,
                reservationRoom.getHotelID());
        // Get room ID
        if (selectedRoomIndex <= -1) {
            return;
        }
        // Set room ID send to room ID
        roomIDSend = roomID;
        // Set selected checkin date to checkin date
        selectedCheckinDate = checkinDate.getValue();
        // Set selected checkout date to checkout date
        selectedCheckoutDate = checkoutDate.getValue();
        selectedHotelName = hotel.getName();// Set selected hotel name to hotel name
        selectedRoomPrice = reservationRoom.getPrice().toString();// Set selected room price to room price
        // create a new FXML loader for reservation-detail.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("reservation-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // create a new scene with root and set the stage
        stage.setTitle("Reservation Detail");       // set the stage title
        stage.setScene(scene);                      // set the scene
        stage.centerOnScreen();                     // center the stage
        stage.show();                               // show the stage
    }

    @FXML
    protected void onUserMenu3() {
        HotelReservationApplication.isLoggedIn = false; // Set isLoggedIn to false
        userMenu.setVisible(false);                     // Set user menu to invisible
        userMenu.setDisable(true);                      // Set user menu to disable
        userLogin.setVisible(true);                     // Set user login button to visible
        userLogin.setDisable(false);                    // Set user login button to disable
        customer = null;                                // Set customer to null
    }

    @FXML
    protected void dateChanged() throws IOException {
        // Set checkout date to checkin date plus 1 day
        checkoutDate.setValue(checkinDate.getValue().plusDays(1));
    }

    @FXML
    protected void setCheckoutDateChanged() throws IOException {
        // Get date difference between checkin and checkout dates
        this.onRefreshButtonClick();
    }
}
