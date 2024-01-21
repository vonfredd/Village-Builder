package com.example.villagebuilder.production;

import com.example.villagebuilder.buildings.Building;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.util.List;

public class ResourceProduction {

    //lumber brick wheat
    private int lumberAmount;
    private int brickAmount;
    private int wheatAmount;

    private List <Building> buildings = FXCollections.observableArrayList();

    public void startProduction(){
        lumberAmount += 1;
        brickAmount += 1;
        wheatAmount += 1;
    }
}
