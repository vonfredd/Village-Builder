package com.example.villagebuilder.buildings;

public class Farm implements EconomicBuilding {
    private int level = 1;
    private final String type;

    public Farm() {
        this.type = "FARM";
    }

    @Override
    public void produceResource() {
        System.out.println("Farm is producing wheat.");
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
}
