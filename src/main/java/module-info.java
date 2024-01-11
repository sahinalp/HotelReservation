module com.example.hotelreservation {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.hotelreservation to javafx.fxml;
    opens com.example.hotelreservation.Entities to javafx.fxml;
    exports com.example.hotelreservation;
    exports com.example.hotelreservation.Entities;
}