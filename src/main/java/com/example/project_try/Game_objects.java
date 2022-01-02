package com.example.project_try;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Game_objects {
    protected Coordinate coordinate;
    private String image;
    protected final ImageView imageView;

    Game_objects(Coordinate coordinate,String image){
        this.coordinate = coordinate;
        this.image = image;
        this.imageView = new ImageView();
        Image image1 = null;

        try {
            image1 = new Image(new FileInputStream(image));
        }catch (Exception e){
            System.out.println("Couldnt find image");
        }

        imageView.setImage(image1);
        imageView.setLayoutX(coordinate.getXStart());
        imageView.setLayoutY(coordinate.getYStart());
        imageView.setFitWidth(coordinate.getXEnd()-coordinate.getXStart());
        imageView.setFitHeight(coordinate.getYEnd()-coordinate.getYStart());
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        imageView.setLayoutX(coordinate.getXStart());
        imageView.setLayoutY(coordinate.getYStart());
        imageView.setFitWidth(coordinate.getXEnd()-coordinate.getXStart());
        imageView.setFitHeight(coordinate.getYEnd()-coordinate.getYStart());
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
