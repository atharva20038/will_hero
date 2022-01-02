package com.example.project_try;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Hero extends Characters implements Collision{

    private long coinScore;
    private Game currentGame;
    private transient Label coinCount;
    private Helmet helmet;
    private transient AnchorPane root;


    Hero(Coordinate coordinate,String image,Game currentGame,int life,AnchorPane root,ArrayList<Orcs> orcs,ArrayList<Chest> chests,ArrayList<Coin> coins,ArrayList<Islands> islands,Label coinCount) {
        super(coordinate,image,life,root,islands,orcs,chests,coins);
        coinScore = 0;
        this.currentGame = currentGame;
        this.root = root;
        this.orcs = orcs;
        this.chests = chests;
        this.coins = coins;
        this.islands = islands;
        this.coinCount = coinCount;
        helmet = Helmet.getInstance(this,root);
    }

    public long getCoinCount() {
        return coinScore;
    }

    private void openChest(int i){
        chests.get(i).openChest(this);
        ImageView openChest = new ImageView();
        Image openChestImage = null;
        try {
            openChestImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/ChestOpen.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        openChest.setLayoutX(chests.get(i).getCoordinate().getXStart());
        openChest.setLayoutY(chests.get(i).getCoordinate().getYStart());
        openChest.setFitWidth(chests.get(i).getCoordinate().getXEnd()-chests.get(i).getCoordinate().getXStart());
        openChest.setFitHeight(chests.get(i).getCoordinate().getYEnd()-chests.get(i).getCoordinate().getYStart());
        openChest.setImage(openChestImage);
        root.getChildren().addAll(openChest);
    }

    private AnimationTimer collideChest(){
        Hero hero = this;
        AnimationTimer animationTimerChest = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (hero.getHelmet().getActiveWeapon()!= null) {
                    Coordinate temp = new Coordinate((int)hero.getImageView().getBoundsInParent().getMinX() - 8, (int) hero.imageView.getBoundsInParent().getMinY() - 10, 20, 50);
                    hero.getHelmet().getActiveWeapon().setCoordinate(temp);
                }
                for(int i=0;i< chests.size();i++){
                    if(!chests.get(i).getIsOpen()&&imageView.getBoundsInParent().intersects(chests.get(i).getImageView().getBoundsInParent())){
                        root.getChildren().remove(chests.get(i).getImageView());
                        openChest(i);
                        if(chests.get(i) instanceof WeaponChest){
                            if(helmet.getActiveWeapon()!=null){
                                helmet.getActiveWeapon().setCoordinate(new Coordinate((int)hero.imageView.getLayoutX(),(int)hero.imageView.getLayoutY(),(int)hero.imageView.getFitWidth(),(int)hero.imageView.getFitHeight()));
                                if(!root.getChildren().contains(helmet.getActiveWeapon().imageView)){
                                    root.getChildren().addAll(helmet.getActiveWeapon().imageView);
                                }
                            }
                            System.out.println("Weapon Chest");
                        }else {
                            coinCount.setText(coinScore+"");
                            System.out.println("Coin Chest");
                        }
                        break;
                    }
                }
            }
        };

        return animationTimerChest;
    }

    private AnimationTimer collideCoins() {
        AnimationTimer animationTimerCoin = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Coin x:coins) {
                    if(x.getImageView().getBoundsInParent().intersects(imageView.getBoundsInParent())&&!x.getIsCollected()){
                        x.collectCoin();
                        root.getChildren().remove(x.getImageView());
                        coinScore++;
                        coinCount.setText(""+coinScore);
                    }
                }
            }
        };

        return animationTimerCoin;
    }

    private AnimationTimer collideOrc(){

        AnimationTimer animationTimerOrc = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i=0;i<orcs.size();i++){
                    //collide orc
                    if(orcs.get(i).imageView.getBoundsInParent().intersects(imageView.getBoundsInParent())){

                        if(helmet.getActiveWeapon()!=null){
                            WeaponAnimate();
                            helmet.getActiveWeapon().attack(orcs.get(i));
                        }

                        if(orcs.get(i).life<=0){
                            orcs.get(i).die();
                        }else {
                            TranslateTransition translateTransition = new TranslateTransition();
                            this.getImageView().setLayoutX(this.getCoordinate().getXStart());
                            this.getCoordinate().setXStart(this.getCoordinate().getXStart());
                            this.getCoordinate().setXEnd(this.getCoordinate().getXEnd());
                            orcs.get(i).getImageView().setLayoutX(orcs.get(i).getCoordinate().getXStart()+50);
                            orcs.get(i).getCoordinate().setXStart(orcs.get(i).getCoordinate().getXStart()+50);
                            orcs.get(i).getCoordinate().setXEnd(orcs.get(i).getCoordinate().getXEnd()+50);
                            translateTransition.setCycleCount(1);
                            translateTransition.setDuration(Duration.millis(500));
                            SequentialTransition sequentialTransition = new SequentialTransition(orcs.get(i).imageView,translateTransition);
                            sequentialTransition.play();
                        }

                    }
                }
            }

            private ImageView getImageView() {
                return Hero.super.getImageView();
            }

            private Coordinate getCoordinate() {
                return Hero.super.getCoordinate();
            }
        };

        return animationTimerOrc;
    }

    private void WeaponAnimate() {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(135);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setDuration(Duration.millis(100));
        rotateTransition.setCycleCount(2);
        SequentialTransition sequentialTransition = new SequentialTransition(helmet.getActiveWeapon().imageView,rotateTransition);
        sequentialTransition.play();
    }


    @Override
    public void collide() {
        AnimationTimer timerChest = collideChest();
        AnimationTimer timerCoin = collideCoins();
        AnimationTimer timerOrc = collideOrc();
        timerChest.start();
        timerCoin.start();
        timerOrc.start();
    }

    public void AnimateGravity(ArrayList<Islands> islands){
        int i = 0;
        while (i<islands.size()){
            if(this.imageView.getBoundsInParent().intersects(islands.get(i).imageView.getBoundsInParent())){
                AnimateY( islands);
                return;
            }
            i++;
        }

        Gravity();

    }

    private void AnimateY(ArrayList<Islands> islands){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000));
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.setFromY(0);
        translateTransition.setToY(-130);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AnimateGravity(islands);
            }
        });
        SequentialTransition sequentialTransition = new SequentialTransition(this.getImageView(),translateTransition);
        sequentialTransition.play();
    }

    private void Gravity(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000));
        translateTransition.setCycleCount(1);
        translateTransition.setFromY(10);
        translateTransition.setToY(500);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    currentGame.endGame();
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        SequentialTransition sequentialTransition = new SequentialTransition(this.imageView,translateTransition);
        sequentialTransition.play();
    }

    public long getCoinScore() {
        return coinScore;
    }

    public void setCoinScore(long coinScore) {
        this.coinScore = coinScore;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public void setCoinCount(Label coinCount) {
        this.coinCount = coinCount;
    }
}
