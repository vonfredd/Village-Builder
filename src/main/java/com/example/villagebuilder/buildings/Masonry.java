package com.example.villagebuilder.buildings;

public class Masonry implements EconomicBuilding {
    private int level;
    private String type;

    public Masonry() {
        this.type = "MASONRY";
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
