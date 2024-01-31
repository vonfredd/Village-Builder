package com.example.villagebuilder.cost;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class CostOfBuilding {

    private final SimpleIntegerProperty lumberPrice = new SimpleIntegerProperty();
    private final SimpleIntegerProperty brickPrice = new SimpleIntegerProperty();
    private final SimpleIntegerProperty wheatPrice = new SimpleIntegerProperty();

    List<SimpleIntegerProperty> getCostOfFarm(){
        return List.of(lumberPrice,brickPrice,wheatPrice);
    }

    void setPrice(int level){
        lumberPrice.add(lumberPrice.getValue() * level);
        lumberPrice.add(brickPrice.getValue() * level);
        lumberPrice.add(wheatPrice.getValue() * level);
    }
}
