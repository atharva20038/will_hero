package com.example.project_try;

import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Orcs extends Characters implements Collision{
    private int setToY;
    private AnimationTimer animationTimer;
    private ArrayList<Orcs> orcs;


    Orcs(Coordinate coordinate, String image, int setToY, int life,AnchorPane root,ArrayList<Islands> islands,ArrayList<Orcs> orcs,ArrayList<Chest> chests,ArrayList<Coin> coins) {
        super(coordinate, image,life,root,islands,orcs,chests,coins);
        this.setToY = setToY;
        this.orcs = orcs;
        collide();

    }

    public void AnimateGravity(ArrayList<Islands> islands, ArrayList<Orcs> orcs, AnchorPane root){
        int i = 0;
        while (i<islands.size()){
            if(islands.get(i).imageView.getBoundsInParent().intersects(this.getImageView().getBoundsInParent())){
                AnimateY(islands,orcs,root);
                return;
            }
            i++;
        }

        Gravity(islands,orcs,root);

    }

    private void AnimateY(ArrayList<Islands> islands,ArrayList<Orcs> orcs,AnchorPane root){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000));
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.setFromY(-1*setToY);
        translateTransition.setToY(-130);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AnimateGravity(islands,orcs,root);
            }
        });
        SequentialTransition sequentialTransition = new SequentialTransition(this.getImageView(),translateTransition);
        sequentialTransition.play();
    }

    private void Gravity(ArrayList<Islands> islands,ArrayList<Orcs> orcs,AnchorPane root){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000));
        translateTransition.setCycleCount(1);
        translateTransition.setFromY(10);
        translateTransition.setToY(500);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //remove orc from list of objects
                orcs.remove(this);
                root.getChildren().remove(imageView);
            }
        });
        SequentialTransition sequentialTransition = new SequentialTransition(imageView,translateTransition);
        sequentialTransition.play();
    }

    @Override
    public void collide() {
        Orcs current = this;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i=0;i<orcs.size();i++){
                    if(imageView.getBoundsInParent().intersects(orcs.get(i).getImageView().getBoundsInParent())&&current!=orcs.get(i)){
                        Coordinate temp = orcs.get(i).coordinate;
                        temp.setXEnd(temp.getXEnd()+70);
                        temp.setXStart(temp.getXStart()+70);
                        orcs.get(i).setCoordinate(temp);
                    }
                }
            }
        };

        animationTimer.start();

    }

    public void die(){
        root.getChildren().remove(this.imageView);
        orcs.remove(this);
    }
}
