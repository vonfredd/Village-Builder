package com.example.villagebuilder.buildings;

public class MasonryFactory implements BuildingFactory {
    @Override
    public Building createBuilding(int level) {
        Masonry masonry = new Masonry();
        masonry.setLevel(level);
        return masonry;
    }
}
