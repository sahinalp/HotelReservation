package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.Customer;
import com.example.hotelreservation.Entities.Hotel;
import com.example.hotelreservation.Entities.HotelRoom;
import com.example.hotelreservation.Entities.Room;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.image.Image.*;

public class HotelReservationController {
    private int menu = 1;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private TableView<HotelRoom> roomListTable;
    @FXML
    private TableColumn<HotelRoom, Integer> roomID;
    @FXML
    private TableColumn<HotelRoom,String> hotelName;
    @FXML
    private TableColumn<HotelRoom,String> roomType;
    @FXML
    private TableColumn<HotelRoom,String> address;
    @FXML
    private TableColumn<HotelRoom,Double> hotelRank;
    @FXML
    private TableColumn<HotelRoom,String> priceCurrency;

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

    private static Customer customer;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        boolean canLogin=false;
        canLogin=customerManager.login(HotelReservationApplication.dbHelper,HotelReservationApplication.connection,
                username.getText(),password.getText());

        if (canLogin)
        {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HotelReservationApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 894, 604);
            stage.setTitle("Login");
            stage.setScene(scene);
            HotelReservationApplication.isLoggedIn=true;
            stage.show();
//            Platform.runLater(() -> this.stageShowEvent());

        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Hata!!");
            a.setHeaderText("Hatalı şifre");
            a.show();
        }
    }
    @FXML
    protected void onRefreshButtonClick() throws IOException
    {
        ArrayList<HotelRoom> roomsArrayList;
        list = FXCollections.observableArrayList();
        roomsArrayList=hotelsManager.getAllRooms(HotelReservationApplication.dbHelper,HotelReservationApplication.connection,
                "");
        for (HotelRoom room: roomsArrayList)
        {
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
    protected void getItem()
    {
        index=roomListTable.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }

        Room room = hotelsManager.getRoom(HotelReservationApplication.dbHelper,HotelReservationApplication.connection,
                roomID.getCellData(index));
        Hotel hotel = hotelsManager.getHotel(HotelReservationApplication.dbHelper,HotelReservationApplication.connection,
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
    private void changeMenu()
    {
        if(menu==1)
        {
            menu=2;
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

        }
        else
        {
            menu=1;
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
    private void stageShowEvent()
    {
        if(!isStageShowEventRun) {
            isStageShowEventRun=true;
            try {
                this.onRefreshButtonClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userMenu.setText(customer.getName() + " "+ customer.getSurname());
        }
    }
    public static void setCustomer(Customer _customer)
    {
        customer=_customer;
    }
}