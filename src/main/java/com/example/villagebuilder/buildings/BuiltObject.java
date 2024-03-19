package com.example.villagebuilder.buildings;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class BuiltObject <T extends Building>{
    private T building;
    Circle circle;
    ImageView imageView;

    public BuiltObject(T building, Circle circle, ImageView imageView) {
        this.building = building;
        this.circle = circle;
        this.imageView = imageView;
    }

    public T getBuilding() {
        return building;
    }

    public void setBuilding(T building) {
        this.building = building;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
