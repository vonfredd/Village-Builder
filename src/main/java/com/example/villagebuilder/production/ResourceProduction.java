package com.example.villagebuilder.production;

import com.example.villagebuilder.buildings.Building;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.Set;

public class ResourceProduction {

    // Connect resource amount with fxml labels
    private final SimpleIntegerProperty lumberAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty brickAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty wheatAmount = new SimpleIntegerProperty();

    public void baseProduction(Set<String> buildingTypes) {
        if (!buildingTypes.contains("LUMBERJACK"))
            setLumberAmount(getLumberAmount() + 1);
        if (!buildingTypes.contains("MASONRY"))
            setBrickAmount(getBrickAmount() + 1);
        if (!buildingTypes.contains("FARM"))
            setWheatAmount(getWheatAmount() + 1);
    }

    public void buildingResourceProduction(Set<String> constructedBuildingsSet, List<Building> buildingList) {
        if (!buildingList.isEmpty())
            buildingList.forEach(e -> {
                        switch (e.getType()) {
                            case "FARM" -> setWheatAmount(getWheatAmount() + e.produceResource() + 1);
                            case "LUMBERJACK" -> setLumberAmount(getLumberAmount() + e.produceResource() + 1);
                            case "MASONRY" -> setBrickAmount(getBrickAmount() + e.produceResource() + 1);
                            default -> baseProduction(constructedBuildingsSet);
                        }
                    }
            );
        baseProduction(constructedBuildingsSet);
    }

    public void startingMaterial() {
        setLumberAmount(1000);
        setBrickAmount(1000);
        setWheatAmount(1000);
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
