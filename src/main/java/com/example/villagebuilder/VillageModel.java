package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.collections.FXCollections;

import java.util.List;

public class VillageModel {

    Builder builder = new Builder();

    private List<Building> buildings = FXCollections.observableArrayList();

    public void constructBuilding(String type) {
        switch (type) {
            case "FARM" -> buildings.add(builder.constructFarm());
            case "LUMBERJACK" -> buildings.add(builder.constructLumberjack());
            case "MASONRY" -> buildings.add(builder.constructMasonry());
        }
    }

    public void callForProduction(ResourceProduction resourceProduction) {
        resourceProduction.buildingResourceProduction(buildings);
    }
}
