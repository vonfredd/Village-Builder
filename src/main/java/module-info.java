module com.example.villagebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.villagebuilder to javafx.fxml;
    exports com.example.villagebuilder;
    exports com.example.villagebuilder.buildings;
    exports com.example.villagebuilder.production;
}