package com.example.villagebuilder.buildings;

public interface Building{
    int produceResource();
    String getType();
    int getWheatPrice();
    int getBricksPrice();
    int getLumberPrice();
    int getLevel();
}
