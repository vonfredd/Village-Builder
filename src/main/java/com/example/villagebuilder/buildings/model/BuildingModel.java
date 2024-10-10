package com.example.villagebuilder.buildings.model;

import com.example.villagebuilder.buildings.Building;
import com.example.villagebuilder.buildings.BuiltObject;
import com.example.villagebuilder.buildings.Farm;
import com.example.villagebuilder.buildings.Lumberjack;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BuildingModel {

    private static final Logger logger = Logger.getLogger( BuildingModel.class.getName() );

    private final BuildModel buildModel;
    private final List<BuiltObject> constructedBuildings;
    private final Farm farm;
    private final ResourceProduction resourceProduction;
    private Farm cachedHighLevelFarm = null;
    private Lumberjack cachedHighLevelLumberjack = null;
    private final Lumberjack lumberjack;

    public BuildingModel(ResourceProduction resourceProduction) {
        lumberjack = new Lumberjack();
        buildModel = new BuildModel();
        farm = new Farm();
        constructedBuildings = new ArrayList<>();
        farm.setCost(1);
        lumberjack.setCost(1);
        this.resourceProduction = resourceProduction;
    }

    public Building priceOfBuilding(Building checkPrice) {
        var theType = checkPrice.getType();
        System.out.println(theType);
        List<Building> buildings = constructedBuildings.stream()
                .map(BuiltObject::getBuilding)
                .filter(building -> building.getType().equals(checkPrice.getType()))
                .toList();
        if (buildings.isEmpty())
            switch (theType) {
                case "FARM": return farm;
                case "LUMBERJACK": return lumberjack;
            }

        return buildings.stream()
                .max(Comparator.comparingInt(Building::getLevel)).get();
    }

    public List<Building> getConstructedBuildings() {
        return List.copyOf(constructedBuildings.stream().map(BuiltObject::getBuilding).toList());

    }

    public Set<String> getConstructedBuildingsSet() {
        return getConstructedBuildings().stream().map(Building::getType).collect(Collectors.toSet());
    }

    public Building buildOnSite(String id) {
        Optional<Building> optional = typeOfBuilding(id);  // The Id of the buildmenu icon .. ex 'farmHouse'
       logger.log(Level.INFO, optional.get().toString());
        if (!isAffordable(priceOfBuilding(optional.get()))) {
            throw new RuntimeException("Not affordable " + optional.get());
        }
        return optional.get();
    }

    //If pane contains image or building, an optional building is returned to caller.
    private Optional<Building> typeOfBuilding(String id) {
        if (id.equals("farmHouse")) {
            return Optional.of(buildModel.constructFarm());// Returns an instance of the class , which name is alike the id .. ex farmHouse = an object of type 'Farm'
        } else if (id.equals("lumberjack")) {
            return Optional.of(buildModel.constructLumberjack());
        }
        throw new RuntimeException("Nope");
    }

    public boolean isAffordable(Building building) {
        logger.log(Level.INFO, "IsAffordable");
        if (resourceProduction.getLumberAmount() >= building.getLumberPrice()
                && resourceProduction.getWheatAmount() >= building.getWheatPrice()
                && resourceProduction.getBrickAmount() >= building.getBricksPrice()) {
            removePriceFromStockpile(building);
            return true;
        }
        return false;
    }

    public void removePriceFromStockpile(Building building) {
        resourceProduction.setBrickAmount(resourceProduction.getBrickAmount() - building.getBricksPrice());
        resourceProduction.setWheatAmount(resourceProduction.getWheatAmount() - building.getWheatPrice());
        resourceProduction.setLumberAmount(resourceProduction.getLumberAmount() - building.getLumberPrice());
    }

    public void addToConstructed(Building building, Circle circle, ImageView imageView) {
        constructedBuildings.add(new BuiltObject<>(building, circle, imageView));// ..ex 'farmHouse' , CircleId and what image in imageview
        if (building instanceof Farm) {
            cachedHighLevelFarm = null;
        } else if (building instanceof Lumberjack) {
            cachedHighLevelLumberjack = null;
        }
    }

    public void callForProduction() {
        resourceProduction.buildingResourceProduction(getConstructedBuildingsSet(), constructedBuildings.stream().map(BuiltObject::getBuilding).toList());
    }

    public Farm getFarm() {
        if (cachedHighLevelFarm == null) {
            cachedHighLevelFarm = constructedBuildings.stream()
                    .map(BuiltObject::getBuilding)
                    .filter(b -> b instanceof Farm)
                    .map(b -> (Farm) b)
                    .max(Comparator.comparingInt(Building::getLevel))
                    .orElse(farm);
        }
        return cachedHighLevelFarm;
    }

    public Lumberjack getLumberjack() {
        if (cachedHighLevelLumberjack == null) {
            cachedHighLevelLumberjack = constructedBuildings.stream()
                    .map(BuiltObject::getBuilding)
                    .filter(b -> b instanceof Lumberjack)
                    .map(b -> (Lumberjack) b)
                    .max(Comparator.comparingInt(Building::getLevel))
                    .orElse(lumberjack);
        }
        logger.log(Level.INFO, "Lumberjack " + cachedHighLevelLumberjack.getLevel());
        return cachedHighLevelLumberjack;
    }
}
