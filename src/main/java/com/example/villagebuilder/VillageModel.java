package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.collections.FXCollections;

import java.util.List;

public class VillageModel {

    private List<Building> buildings = FXCollections.observableArrayList();

    public void constructBuilding() {
        //Add some code to add a building and make ResourceProduction builings list also see all buildings.
    }

    public void callForProduction(ResourceProduction resourceProduction) {
        resourceProduction.buildingResourceProduction(buildings);
    }
}
