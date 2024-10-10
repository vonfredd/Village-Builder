package com.example.villagebuilder.buildings;

public class LumberjackFactory implements BuildingFactory{
    @Override
    public Building createBuilding(int level) {
        Lumberjack lumberjack = new Lumberjack();
        lumberjack.setLevel(level);
        lumberjack.setCost(level);
        return lumberjack;
    }
}
