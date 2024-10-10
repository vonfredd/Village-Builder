package com.example.villagebuilder.buildings.model;

import com.example.villagebuilder.Builder;
import com.example.villagebuilder.buildings.Building;
import com.example.villagebuilder.buildings.BuiltObject;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Optional;

public class BuildModel {

    Builder builder;

    public BuildModel() {
        builder = new Builder();
    }

//    public void buildOnSite(MouseEvent mouseEvent) {
//        Pane pane = (Pane) mouseEvent.getSource();
//        Optional<Building> optional = typeOfBuilding(pane.getId());
//        if (optional.isEmpty())
//            return;
//        if (!model.isAffordable(buildingModel.priceOfFarm()))
//            return;
//        var imageViewList = constructionSiteImages.stream().filter(e -> e.getId().equals(circle.getId() + "Image")).toList();
//        ImageView imageView = imageViewList.getFirst();
//        setTheImage(pane, imageView);
//        buildMenu.setVisible(!buildMenu.isVisible());
//        constructedBuildings.add(new BuiltObject<>(optional.get(), circle, imageView));
//    }

    private Optional<Building> typeOfBuilding(String id) {
        if (id.equals("farmHouse")) {
            return Optional.of(builder.constructFarm());
        }
        throw new RuntimeException("Nope");
    }

    public Building constructFarm(){
        return builder.constructFarm();
    }

    public Building constructLumberjack(){
        return builder.constructLumberjack();
    }


}
