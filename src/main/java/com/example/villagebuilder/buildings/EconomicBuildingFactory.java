package com.example.villagebuilder.buildings;

public class EconomicBuildingFactory implements BuildingFactory{
    @Override
    public Building createBuilding() {
        return new EconomicBuilding();
    }
}
