package com.example.villagebuilder;

import com.example.villagebuilder.production.ResourceProduction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class VillageController {
    @FXML
    private Label brickLabel;
    @FXML
    private Label lumberLabel;
    @FXML
    private Label wheatLabel;
    private ResourceProduction resourceProduction;
    private VillageModel model = new VillageModel();

    public void initialize() {
        startingTheTimeline();
        resourceProduction = new ResourceProduction();
        brickLabel.textProperty().bind(resourceProduction.brickAmountProperty().asString());
        lumberLabel.textProperty().bind(resourceProduction.lumberAmountProperty().asString());
        wheatLabel.textProperty().bind(resourceProduction.wheatAmountProperty().asString());


    }

    private void startingTheTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline timelineProduce = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    model.callForProduction(resourceProduction);
                })
        );
        timelineProduce.setCycleCount(Timeline.INDEFINITE);
        timelineProduce.play();
    }

}