package com.example.villagebuilder.buildings;

public class Farm implements EconomicBuilding {
    private int level;
    private final String type;

    public Farm() {
        this.type = "FARM";
    }

    @Override
    public int produceResource() {
        return getLevel();
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
