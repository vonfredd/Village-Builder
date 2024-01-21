package com.example.villagebuilder.production;

import com.example.villagebuilder.buildings.Building;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class ResourceProduction {

    // Connect resource amount with fxml labels
    private final SimpleIntegerProperty lumberAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty  brickAmount = new SimpleIntegerProperty();
    private final SimpleIntegerProperty  wheatAmount = new SimpleIntegerProperty();

    private List <Building> buildings = FXCollections.observableArrayList();

    public void baseProduction(){
        buildingResourceProduction();
        setLumberAmount(getLumberAmount()+1);
        setBrickAmount(getBrickAmount()+1);
        setWheatAmount(getWheatAmount()+1);
    }

    private void buildingResourceProduction(){
        buildings.forEach((e)-> e.produceResource());
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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
