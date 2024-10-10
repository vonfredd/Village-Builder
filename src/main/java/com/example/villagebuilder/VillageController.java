package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.buildings.model.BuildingModel;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class VillageController {
    @FXML
    public Label farmLogsPriceLabel;
    @FXML
    public Label farmFoodPriceLabel;
    @FXML
    public Label farmBrickPriceLabel;
    @FXML
    public Label lumberjackBrickPriceLabel;
    @FXML
    public Label lumberjackFoodPriceLabel;
    @FXML
    public Label lumberjackLogsPriceLabel;
    @FXML
    private Pane buildMenu;
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
    private VillageModel model;
    private BuildingModel buildingModel;
    private List<ImageView> constructionSiteImages;
    private Circle circle;

    public void initialize() {
        buildMenu.setVisible(false);
        resourceProduction = new ResourceProduction();
        buildingModel = new BuildingModel(resourceProduction);
        model = new VillageModel(resourceProduction);
        resourceProduction.startingMaterial();
        startingTheTimeline();
        constructionSiteImages = List.of(siteOneImage, siteTwoImage, siteThreeImage, siteFourImage, siteFiveImage, siteSixImage, siteSevenImage);
        brickLabel.textProperty().bind(resourceProduction.brickAmountProperty().asString());
        lumberLabel.textProperty().bind(resourceProduction.lumberAmountProperty().asString());
        wheatLabel.textProperty().bind(resourceProduction.wheatAmountProperty().asString());
        farmLogsPriceLabel.textProperty().bind(Bindings.concat("Lumber: " + buildingModel.getFarm().lumberPriceProperty().get()));
        farmFoodPriceLabel.textProperty().bind(Bindings.concat("Food: " + buildingModel.getFarm().wheatPriceProperty().get()));
        farmBrickPriceLabel.textProperty().bind(Bindings.concat("Bricks: " + buildingModel.getFarm().bricksPriceProperty().get()));

        lumberjackLogsPriceLabel.textProperty().bind(Bindings.concat("Lumber: " + buildingModel.getLumberjack().lumberPriceProperty().get()));
        lumberjackFoodPriceLabel.textProperty().bind(Bindings.concat("Food: " + buildingModel.getLumberjack().wheatPriceProperty().get()));
        lumberjackBrickPriceLabel.textProperty().bind(Bindings.concat("Bricks: " + buildingModel.getLumberjack().bricksPriceProperty().get()));

    }

    private void startingTheTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timelineProduce = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> buildingModel.callForProduction())
        );
        timelineProduce.setCycleCount(Animation.INDEFINITE);
        timelineProduce.play();
    }

    public void constructionSite(MouseEvent mouseEvent) {
        buildMenu.setVisible(!buildMenu.isVisible());
        circle = (Circle) mouseEvent.getSource();
    }

    public void buildOnSite(MouseEvent mouseEvent) {
        Pane pane = (Pane) mouseEvent.getSource(); // The clicked pane from buildmenu ..ex Farmhouse icon
        try {
            Building building = buildingModel.buildOnSite(pane.getId()); // new
            var imageViewList = constructionSiteImages.stream().filter(e -> e.getId().equals(circle.getId() + "Image")).toList(); // Gets the circle (buildingsite) from the list of buildingsites
            ImageView imageView = imageViewList.getFirst(); // Get the imageview
            setTheImage(pane, imageView); // set the image of the imageview to the proper icon based on paneId from buildingsmenu
            buildMenu.setVisible(!buildMenu.isVisible());
            buildingModel.addToConstructed(building, circle, imageView);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    private void setTheImage(Pane pane, ImageView imageView) {
        URL resourceUrl = getClass().getResource("/com/example/villagebuilder/images/" + pane.getId() + ".png");
        if (resourceUrl == null)
            throw new NullPointerException("Not a valid path");
        Image image = new Image(resourceUrl.toString());
        imageView.setTranslateX(circle.getTranslateX());
        imageView.setTranslateY(circle.getTranslateY());
        imageView.setImage(image);
        imageView.setVisible(true);
    }

}