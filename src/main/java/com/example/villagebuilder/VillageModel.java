package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.collections.FXCollections;

import java.util.List;

public class VillageModel {

    Builder builder = new Builder();
    ResourceProduction resourceProduction;
    private final List<Building> buildings = FXCollections.observableArrayList();

    public VillageModel(ResourceProduction resourceProduction) {
        this.resourceProduction = resourceProduction;
    }

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


    public boolean checkPrice(Building building, Farm farm) {
        if (resourceProduction.getLumberAmount() >= farm.getLumberPrice() && resourceProduction.getWheatAmount() >= farm.getWheatPrice() && resourceProduction.getBrickAmount() >= farm.getBricksPrice())
            return true;
        return false;
    }

    public void removePriceFromStockpile(Farm farm) {
        resourceProduction.setBrickAmount(resourceProduction.getBrickAmount() - farm.getBricksPrice());
        resourceProduction.setWheatAmount(resourceProduction.getWheatAmount() - farm.getWheatPrice());
        resourceProduction.setLumberAmount(resourceProduction.getLumberAmount() - farm.getLumberPrice());
    }
}
