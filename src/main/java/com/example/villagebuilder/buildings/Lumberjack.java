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

    public int getLevel() {
        return level;
    }

}
