module com.example.otelreservation {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.otelreservation to javafx.fxml;
    exports com.example.otelreservation;
}