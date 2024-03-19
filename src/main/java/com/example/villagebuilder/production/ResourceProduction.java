package com.example.villagebuilder.production;

import com.example.villagebuilder.buildings.Building;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class ResourceProduction {

    // Connect resource amount with fxml labels
    private final SimpleIntegerProperty lumberAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty brickAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty wheatAmount = new SimpleIntegerProperty();

    public void baseProduction() {
        setLumberAmount(getLumberAmount() + 1);
        setBrickAmount(getBrickAmount() + 1);
        setWheatAmount(getWheatAmount() + 1);
    }

    public void buildingResourceProduction(List<Building> buildingList) {
        if (buildingList.isEmpty())
            baseProduction();
        buildingList.forEach((e) -> {
                    switch (e.getType()) {
                        case "FARM" -> setWheatAmount(getWheatAmount() + e.produceResource() + 1);
                        case "LUMBERJACK" -> setLumberAmount(getLumberAmount() + e.produceResource() + 1);
                        case "MASONRY" -> setBrickAmount(getBrickAmount() + e.produceResource() + 1);
                        default -> baseProduction();
                    }
                }
        );
    }

    public void startingMaterial(){
        setLumberAmount(100);
        setBrickAmount(100);
        setWheatAmount(100);
    }

    public int getLumberAmount() {
        return lumberAmount.get();
    }

    public SimpleIntegerProperty lumberAmountProperty() {
        return lumberAmount;
    }

    public void setLumberAmount(int lumberAmount) {
        this.lumberAmount.set(lumberAmount);
    }

    public int getBrickAmount() {
        return brickAmount.get();
    }

    public SimpleIntegerProperty brickAmountProperty() {
        return brickAmount;
    }

    public void setBrickAmount(int brickAmount) {
        this.brickAmount.set(brickAmount);
    }

    public int getWheatAmount() {
        return wheatAmount.get();
    }

    public SimpleIntegerProperty wheatAmountProperty() {
        return wheatAmount;
    }

    public void setWheatAmount(int wheatAmount) {
        this.wheatAmount.set(wheatAmount);
    }

}
