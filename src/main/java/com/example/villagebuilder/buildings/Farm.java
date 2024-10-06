package com.example.villagebuilder.buildings;

import javafx.beans.property.SimpleIntegerProperty;

public class Farm implements EconomicBuilding {
    private int level;
    private final String type;
    private final SimpleIntegerProperty wheatPrice = new SimpleIntegerProperty();
    private final SimpleIntegerProperty lumberPrice = new SimpleIntegerProperty();
    private final SimpleIntegerProperty bricksPrice = new SimpleIntegerProperty();


    public Farm() {
        this.type = "FARM";
    }

    @Override
    public int produceResource() {
        return getLevel();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getWheatPrice() {
        return wheatPrice.get();
    }

    public SimpleIntegerProperty wheatPriceProperty() {
        return wheatPrice;
    }
    @Override
    public int getLumberPrice() {
        return lumberPrice.get();
    }

    public SimpleIntegerProperty lumberPriceProperty() {
        return lumberPrice;
    }

    @Override
    public int getBricksPrice() {
        return bricksPrice.get();
    }

    public SimpleIntegerProperty bricksPriceProperty() {
        return bricksPrice;
    }

    public void setCost(int level) {
        wheatPrice.set(level * 100);
        lumberPrice.set(level * 50);
        bricksPrice.set(level * 50);
    }
}
