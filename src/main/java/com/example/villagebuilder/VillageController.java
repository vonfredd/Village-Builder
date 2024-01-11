package com.example.villagebuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VillageController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}