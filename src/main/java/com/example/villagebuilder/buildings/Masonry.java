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
    public void produceResource() {
        System.out.println("Masonry is producing bricks.");
    }
}
