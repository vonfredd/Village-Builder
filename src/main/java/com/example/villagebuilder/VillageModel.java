package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.collections.FXCollections;

import java.util.List;

public class VillageModel {

    ResourceProduction resourceProduction;
    private final List<Building> buildings = FXCollections.observableArrayList();

    public VillageModel(ResourceProduction resourceProduction) {
        this.resourceProduction = resourceProduction;
    }

}
