package com.example.villagebuilder.buildings;

public class Lumberjack implements EconomicBuilding {
    private int level;
    private String type;

    public Lumberjack() {
        this.type = "LUMBERJACK";
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int produceResource() {
        return getLevel();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getWheatPrice() {
        return 0;
    }

    @Override
    public int getBricksPrice() {
        return 0;
    }

    @Override
    public int getLumberPrice() {
        return 0;
    }

    public int getLevel() {
        return level;
    }

}
