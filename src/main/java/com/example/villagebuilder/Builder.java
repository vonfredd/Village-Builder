package com.example.villagebuilder;

import com.example.villagebuilder.buildings.*;

public class Builder {
    private final BuildingFactory farmFactory = new FarmFactory();
    private final BuildingFactory lumberjackFactory = new LumberjackFactory();
    private final BuildingFactory masonryFactory = new MasonryFactory();

    public Building constructFarm(){
        return farmFactory.createBuilding(1);
    }
    public Building constructLumberjack(){
        return lumberjackFactory.createBuilding(1);
    }
    public Building constructMasonry(){
        return masonryFactory.createBuilding(1);
    }
}
