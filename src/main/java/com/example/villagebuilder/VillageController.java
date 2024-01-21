package com.example.villagebuilder;

import com.example.villagebuilder.production.ResourceProduction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class VillageController {
    @FXML
    private Label welcomeText;

    private ResourceProduction resourceProduction = new ResourceProduction();

    public void initialize() {
        startingTheTimeline();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void startingTheTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> resourceProduction.baseProduction())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline timelineProduce = new Timeline(
                //new KeyFrame(Duration.seconds(1), event -> model.materialProduction())
        );
        timelineProduce.setCycleCount(Timeline.INDEFINITE);
        timelineProduce.play();
    }

    private void updateLabel() {
        //model.updateMaterialsCountingLabels();
        //myListView.refresh();
    }
}