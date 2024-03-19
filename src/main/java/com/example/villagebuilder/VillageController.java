package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
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
    private Pane farmHouse;
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
    private List<Circle> constructionSites;
    private List<ImageView> constructionSiteImages;
    private List<BuiltObject> constructedBuildings;
    private Farm farm;

    private Circle circle;

    private Builder builder;

    public void initialize() {
        builder = new Builder();
        constructedBuildings = new ArrayList<>();
        buildMenu.setVisible(false);
        resourceProduction = new ResourceProduction();
        model = new VillageModel(resourceProduction);
        resourceProduction.startingMaterial();
        farm = new Farm();
        farm.setCost(1);
        startingTheTimeline();
        constructionSites = Arrays.asList(siteOne, siteTwo, siteThree, siteFour, siteFive, siteSix, siteSeven);
        constructionSiteImages = Arrays.asList(siteOneImage, siteTwoImage, siteThreeImage, siteFourImage, siteFiveImage, siteSixImage, siteSevenImage);
        brickLabel.textProperty().bind(resourceProduction.brickAmountProperty().asString());
        lumberLabel.textProperty().bind(resourceProduction.lumberAmountProperty().asString());
        wheatLabel.textProperty().bind(resourceProduction.wheatAmountProperty().asString());
        farmLogsPriceLabel.textProperty().bind(Bindings.concat("Lumber: " + priceOfFarm().lumberPriceProperty().get()));
        farmFoodPriceLabel.textProperty().bind(Bindings.concat("Food: " + priceOfFarm().wheatPriceProperty().get()));
        farmBrickPriceLabel.textProperty().bind(Bindings.concat("Bricks: " + priceOfFarm().bricksPriceProperty().get()));

    }

    private Farm priceOfFarm() {
        List<Farm> farms = constructedBuildings.stream()
                .filter(builtObject -> builtObject.getBuilding() instanceof Farm)
                .map(builtObject -> (Farm) builtObject.getBuilding())
                .toList();
        if (farms.isEmpty())
            return farm;
        return farms.stream()
                .max(Comparator.comparingInt(Farm::getLevel)).get();

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
        buildMenu.setVisible(!buildMenu.isVisible());
        circle = (Circle) mouseEvent.getSource();
    }

    public void buildOnSite(MouseEvent mouseEvent) {
        Pane pane = (Pane) mouseEvent.getSource();
        Optional<Building> optional = typeOfBuilding(pane.getId());
        if (model.checkPrice(optional.get(), priceOfFarm()))
            model.removePriceFromStockpile(priceOfFarm());
        else
            return;
        var imageViewList = constructionSiteImages.stream().filter(e -> e.getId().equals(circle.getId() + "Image")).toList();
        ImageView imageView = imageViewList.getFirst();
        setTheImage(pane, imageView);
        buildMenu.setVisible(!buildMenu.isVisible());
        constructedBuildings.add(new BuiltObject<>(optional.get(), circle, imageView));
    }

    private void setTheImage(Pane pane, ImageView imageView) {
        URL resourceUrl = getClass().getResource("/com/example/villagebuilder/images/" + pane.getId() + ".png");
        if (resourceUrl == null)
            throw new NullPointerException("Not valid path");
        Image image = new Image(resourceUrl.toString());
        imageView.setTranslateX(circle.getTranslateX());
        imageView.setTranslateY(circle.getTranslateY());
        imageView.setImage(image);
        imageView.setVisible(true);
    }

    private Optional<Building> typeOfBuilding(String id) {
        if (id.equals("farmHouse")) {
            return Optional.of(builder.constructFarm());
        }
        throw new RuntimeException("Nope");
    }

    public List<Building> getConstructedBuildings() {
        return List.copyOf(constructedBuildings.stream().map(BuiltObject::getBuilding).toList());
    }
}