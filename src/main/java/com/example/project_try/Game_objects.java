package com.example.project_try;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.Serializable;

public class Game_objects implements Serializable {
    protected Coordinate coordinate;
    private String image;
    protected transient ImageView imageView;
    private transient AnimationTimer animationTimer;

    Game_objects(Coordinate coordinate,String image){
        this.coordinate = coordinate;
        this.image = image;
        this.imageView = new ImageView();
        Image image1 = null;

        try {
            image1 = new Image(new FileInputStream(image));
        }catch (Exception e) {
            System.out.println("Couldnt find image");
        }
        imageView.setImage(image1);
        imageView.setLayoutX(coordinate.getXStart());
        imageView.setLayoutY(coordinate.getYStart());
        imageView.setFitWidth(coordinate.getXEnd()-coordinate.getXStart());
        imageView.setFitHeight(coordinate.getYEnd()-coordinate.getYStart());

        Game_objects game_objects = this;

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Coordinate temp = new Coordinate((int)imageView.getLayoutX(),(int)imageView.getLayoutY(),(int)imageView.getFitWidth(),(int)imageView.getFitHeight());
                 game_objects.coordinate = temp;
            }
        };
         animationTimer.start();
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        imageView.setLayoutX(coordinate.getXStart());
        imageView.setLayoutY(coordinate.getYStart());
        imageView.setFitWidth(coordinate.getXEnd()-coordinate.getXStart());
        imageView.setFitHeight(coordinate.getYEnd()-coordinate.getYStart());
    }

    public String getImage() {
        return image;
    }
}
