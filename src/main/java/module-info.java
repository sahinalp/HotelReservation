module com.example.otelreservation {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.hotelreservation to javafx.fxml;
    exports com.example.hotelreservation;
}