package com.example.villagebuilder.buildings.model;

import com.example.villagebuilder.buildings.Building;
import com.example.villagebuilder.buildings.BuiltObject;
import com.example.villagebuilder.buildings.Farm;
import com.example.villagebuilder.production.ResourceProduction;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.util.*;
import java.util.stream.Collectors;

public class BuildingModel {

    private final BuildModel buildModel;
    private final List<BuiltObject> constructedBuildings;
    private final Farm farm;
    private final ResourceProduction resourceProduction;

    public BuildingModel(ResourceProduction resourceProduction) {
        buildModel = new BuildModel();
        farm = new Farm();
        constructedBuildings = new ArrayList<>();
        farm.setCost(1);
        this.resourceProduction = resourceProduction;
    }

    public Farm priceOfFarm() {
        List<Farm> farms = constructedBuildings.stream()
                .map(builtObject -> (Farm) builtObject.getBuilding())
                .toList();
        if (farms.isEmpty())
            return farm;
        return farms.stream()
                .max(Comparator.comparingInt(Farm::getLevel)).get();
    }

    public List<Building> getConstructedBuildings() {
        return List.copyOf(constructedBuildings.stream().map(BuiltObject::getBuilding).toList());
    }

    public Set<String> getConstructedBuildingsSet(){
        return getConstructedBuildings().stream().map(Building::getType).collect(Collectors.toSet());
    }

    public Building buildOnSite(String id){
        Optional<Building> optional = typeOfBuilding(id);  // The Id of the buildmenu icon .. ex 'farmHouse'
       if (!isAffordable(priceOfFarm())){
           throw new RuntimeException("Not affordable " + optional.get());
       }
       return optional.get();
    }

    public boolean isAffordable(Farm farm) {
        if (resourceProduction.getLumberAmount() >= farm.getLumberPrice()
                && resourceProduction.getWheatAmount() >= farm.getWheatPrice()
                && resourceProduction.getBrickAmount() >= farm.getBricksPrice()) {
            removePriceFromStockpile(farm);
            return true;
        }
        return false;
    }

    public void removePriceFromStockpile(Farm farm) {
        resourceProduction.setBrickAmount(resourceProduction.getBrickAmount() - farm.getBricksPrice());
        resourceProduction.setWheatAmount(resourceProduction.getWheatAmount() - farm.getWheatPrice());
        resourceProduction.setLumberAmount(resourceProduction.getLumberAmount() - farm.getLumberPrice());
    }

    //If pane contains image or building, an optional building is returned to caller.
    private Optional<Building> typeOfBuilding(String id) {
        if (id.equals("farmHouse")) {
            return Optional.of(buildModel.constructFarm());// Returns an instance of the class , which name is alike the id .. ex farmHouse = an object of type 'Farm'
        }
        throw new RuntimeException("Nope");
    }

    public void addToConstructed(Building building, Circle circle, ImageView imageView) {
        constructedBuildings.add(new BuiltObject<>(building, circle, imageView));// ..ex 'farmHouse' , CircleId and what image in imageview
    }

    public void callForProduction(){
        resourceProduction.buildingResourceProduction(getConstructedBuildingsSet(), constructedBuildings.stream().map(BuiltObject::getBuilding).toList());
    }
}
