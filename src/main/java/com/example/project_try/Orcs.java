package com.example.project_try;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Orcs extends Characters implements Collision{
    private int setToY;
    private transient AnimationTimer animationTimer;
    private ArrayList<Orcs> orcs;
    private boolean isDead;
    private transient AnchorPane root;
    private int reduceLife;


    Orcs(Coordinate coordinate, String image, int setToY, int life,AnchorPane root,ArrayList<Islands> islands,ArrayList<Orcs> orcs,ArrayList<Chest> chests,ArrayList<Coin> coins,int reduceLife) {
        super(coordinate, image,life,root,islands,orcs,chests,coins);
        this.setToY = setToY;
        this.orcs = orcs;
        collide();
        isDead = false;
        this.root = root;
        this.reduceLife = reduceLife;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public void AnimateGravity(ArrayList<Islands> islands, ArrayList<Orcs> orcs, AnchorPane root){
        int i = 0;
        while (i<islands.size()){
            if(islands.get(i).imageView!=null&&islands.get(i).imageView.getBoundsInParent().intersects(this.getImageView().getBoundsInParent())){
                AnimateY(islands,orcs,root);
                return;
            }
            i++;
        }

        Gravity(islands,orcs,root);

    }

    public void attack(Hero hero){
        hero.reduceLife(reduceLife);
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
        KeyValue keyValue = new KeyValue(imageView.yProperty(),500);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2000),keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                orcs.remove(this);
                root.getChildren().remove(imageView);
            }
        });
        timeline.play();
    }

    @Override
    public void collide() {
        Orcs current = this;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i=0;i<orcs.size();i++){
                    if(current!=orcs.get(i)&&imageView.getBoundsInParent().intersects(orcs.get(i).getImageView().getBoundsInParent())){
                        Coordinate temp = orcs.get(i).coordinate;
                        temp.setXEnd(temp.getXEnd()+70);
                        temp.setXStart(temp.getXStart()+70);
                        orcs.get(i).imageView.setX(orcs.get(i).imageView.getLayoutX()+70);
                        orcs.get(i).setCoordinate(temp);
                    }
                }
            }
        };

        animationTimer.start();

    }

    public void die(){
        root.getChildren().remove(this.imageView);
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
