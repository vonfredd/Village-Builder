package com.example.villagebuilder;

import com.example.villagebuilder.production.ResourceProduction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VillageController {
    @FXML
    private Circle siteOne;
    @FXML
    private Circle siteTwo;
    @FXML
    private Circle siteThree;
    @FXML
    private Circle siteFour;
    @FXML
    private Circle siteFive;
    @FXML
    private Circle siteSeven;
    @FXML
    private Circle siteSix;
    @FXML
    private ImageView siteOneImage;
    @FXML
    private ImageView siteTwoImage;
    @FXML
    private ImageView siteThreeImage;
    @FXML
    private ImageView siteFourImage;
    @FXML
    private ImageView siteFiveImage;
    @FXML
    private ImageView siteSixImage;
    @FXML
    private ImageView siteSevenImage;
    @FXML
    private Label brickLabel;
    @FXML
    private Label lumberLabel;
    @FXML
    private Label wheatLabel;
    private ResourceProduction resourceProduction;
    private final VillageModel model = new VillageModel();
    private List<Circle> constructionSites;

    public void initialize() {
        resourceProduction = new ResourceProduction();
        startingTheTimeline();
        constructionSites = Arrays.asList(siteOne,siteTwo,siteThree,siteFour,siteFive,siteSix,siteSeven);
        brickLabel.textProperty().bind(resourceProduction.brickAmountProperty().asString());
        lumberLabel.textProperty().bind(resourceProduction.lumberAmountProperty().asString());
        wheatLabel.textProperty().bind(resourceProduction.wheatAmountProperty().asString());
    }

    private void startingTheTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timelineProduce = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> model.callForProduction(resourceProduction))
        );
        timelineProduce.setCycleCount(Animation.INDEFINITE);
        timelineProduce.play();
    }


    public void constructionSite(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        switch (circle.getId()){
            case"siteOne" -> System.out.println(1);
            case "siteTwo" -> System.out.println(2);
            case "siteThree" -> System.out.println(3);
            case "siteFour" -> System.out.println(4);
            case "siteFive" -> System.out.println(5);
            case "siteSix" -> System.out.println(6);
            case "siteSeven" -> System.out.println(7);
        }
    }


}