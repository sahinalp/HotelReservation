package com.example.hotelreservation;

import com.example.hotelreservation.Business.Concrete.CustomerManager;
import com.example.hotelreservation.Business.Concrete.HotelsManager;
import com.example.hotelreservation.Core.DbHelper;
import com.example.hotelreservation.Entities.HotelRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class HotelReservationController {
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

    ObservableList<HotelRoom> list = FXCollections.observableArrayList();

    int index;
    CustomerManager customerManager = new CustomerManager();
    HotelsManager hotelsManager = new HotelsManager();

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
            stage.show();
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

        System.out.println(hotelName.getCellData(index)+" "+ address.getCellData(index));
    }
}