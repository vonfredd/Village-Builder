package com.example.villagebuilder.buildings;

public class FarmFactory implements BuildingFactory{
    @Override
    public Building createBuilding(int level) {
        Farm farm = new Farm();
        farm.setLevel(level);
        return farm;
    }
}
