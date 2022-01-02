package com.example.project_try;



import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {
    private int score;
    private ArrayList<Chest> chests;
    private ArrayList<Coin> coins;
    private ArrayList<Islands> islands;
    private ArrayList<Orcs> orcs;
    private Hero heroObj;
    private AnchorPane root;
    private Label scoreLabel;
    private Label coinCount;




    Game(){
        islands = new ArrayList<>();
        score = 0;
        chests = new ArrayList<>();
        coins = new ArrayList<>();
        orcs = new ArrayList<>();
    }



    public void display(Stage stage) throws FileNotFoundException, InterruptedException {

        root = new AnchorPane();

        Scene scene = new Scene(root, 1000, 421);
        scene.getRoot().requestFocus();

        //Background Image
        ImageView bg = new ImageView();
        Image image = null;

        try {
            image = new Image(new FileInputStream("src/main/java/com/example/project_try/back.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bg.setImage(image);
        bg.setFitHeight(421);
        bg.setFitWidth(Integer.MAX_VALUE);

        //Label Score
        scoreLabel = new Label("0");
        scoreLabel.setFont(new Font("System",42));
        scoreLabel.setLayoutX(335);
        scoreLabel.setLayoutY(10);
        scoreLabel.setTextFill(Color.WHITE);


        //coinImage
        Image coinImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"));
        ImageView coin = new ImageView();
        coin.setFitHeight(27);
        coin.setFitWidth(27);
        coin.setLayoutY(9);
        coin.setLayoutX(648);
        coin.setImage(coinImage);

        //Label CoinCount
        coinCount = new Label(0+"");
        coinCount.setLayoutX(608);
        coinCount.setLayoutY(7);
        coinCount.setTextFill(Color.YELLOW);
        coinCount.setFont(new Font("System",21));

        //hero
        heroObj = new Hero(new Coordinate(120,170,40,40),"src/main/java/com/example/project_try/assets/Helmet4.png",this,1000,root,orcs,chests,coins,islands,coinCount);


        EventHandler onKeyPressed = new EventHandler() {
            @Override
            public void handle(Event event) {

                KeyEvent key = (KeyEvent) event;

                if(key.getCode()== KeyCode.SPACE){
                    heroObj.getImageView().setLayoutX(heroObj.getCoordinate().getXStart()+30);
                    heroObj.getCoordinate().setXStart(heroObj.getCoordinate().getXStart()+30);
                    heroObj.getCoordinate().setXEnd(heroObj.getCoordinate().getXEnd()+30);
                    if(heroObj.getCoordinate().getXStart()>=400){
                        scoreLabel.setLayoutX(scoreLabel.getLayoutX()+30);
                        coinCount.setLayoutX(coinCount.getLayoutX()+30);
                        coin.setLayoutX(coin.getLayoutX()+30);
                        scene.getRoot().setLayoutX(scene.getRoot().getLayoutX()-30);
                    }
                    scoreLabel.setText((++score)+"");



                }else{
                    System.out.println("Wrong Input");
                }


            }
        };

        scene.setOnKeyPressed(onKeyPressed);
        //adding to stage
        root.getChildren().addAll(bg);
        root.getChildren().addAll(scoreLabel);
        root.getChildren().addAll(coin);
        root.getChildren().addAll(coinCount);

        createWorld(root);
        heroObj.collide();
        heroObj.AnimateGravity(islands);

        root.getChildren().addAll(heroObj.getImageView());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }




    static void endGame() {
        System.out.println("Game Over");
        System.exit(0);
    }

    private void createWorld(AnchorPane root){
        //Island Spawning
        for(int i=0;i<100;i++){
            Islands island1 = new Islands(new Coordinate( 100+i*1000,210,97,154),"src/main/java/com/example/project_try/assets/BalancingRocks4.png");
            Islands island2 = new Islands(new Coordinate( 400+i*1000,210,150,102),"src/main/java/com/example/project_try/assets/BalancingRocks5.png");
            Islands island3 = new Islands(new Coordinate(750+i*1000,210,150,102),"src/main/java/com/example/project_try/assets/BalancingRocks3.png");
            islands.add(island1);
            islands.add(island2);
            islands.add(island3);
            root.getChildren().addAll(island1.getImageView());
            root.getChildren().addAll(island2.getImageView());
            root.getChildren().addAll(island3.getImageView());
        }

        //Spawning Chest
        for(int i =0;i<100;i+=2){
            if(i%4==0){
                Chest chest = new WeaponChest(new Coordinate(400+i*1000,170,60,40),"src/main/java/com/example/project_try/assets/ChestClosed.png");
                chests.add(chest);
                root.getChildren().addAll(chest.getImageView());
            }else{
                Chest chest = new CoinChest(new Coordinate(400+i*1000,170,60,40),"src/main/java/com/example/project_try/assets/ChestClosed.png");
                chests.add(chest);
                root.getChildren().addAll(chest.getImageView());
            }
        }

        //Coin Spawn
        for (int i=0;i<100;i++){
            Coin coin1 = new Coin(new Coordinate(231+i*1000,100,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            Coin coin2 = new Coin(new Coordinate(270+i*1000,100,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            Coin coin3 = new Coin(new Coordinate(309+i*1000,100,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            Coin coin4 = new Coin(new Coordinate(231+i*1000,60,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            Coin coin5 = new Coin(new Coordinate(270+i*1000,60,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            Coin coin6 = new Coin(new Coordinate(309+i*1000,60,30,30),"src/main/java/com/example/project_try/assets/Coin.png");
            coins.add(coin1);
            coins.add(coin2);
            coins.add(coin3);
            coins.add(coin4);
            coins.add(coin5);
            coins.add(coin6);
            root.getChildren().addAll(coin1.getImageView());
            root.getChildren().addAll(coin2.getImageView());
            root.getChildren().addAll(coin3.getImageView());
            root.getChildren().addAll(coin4.getImageView());
            root.getChildren().addAll(coin5.getImageView());
            root.getChildren().addAll(coin6.getImageView());
        }





        for(int i=1;i<100;i++){

//                Orcs orc1 = new Orcs(new Coordinate(120 +i*1000,170,40,50),"src/main/java/com/example/project_try/assets/Orc1.png",10,30,root,islands,orcs,chests,coins);
//                orcs.add(orc1);
//                orc1.AnimateGravity(islands,orcs,root);
//                root.getChildren().addAll(orc1.imageView);

                Orcs orc2 = new Orcs(new Coordinate( 450+i*1000,170,40,50),"src/main/java/com/example/project_try/assets/RedOrc1.png",10,30,root,islands,orcs,chests,coins);
                orcs.add(orc2);
                orc2.AnimateGravity(islands,orcs,root);
                root.getChildren().addAll(orc2.imageView);

                Orcs orc3 = new Orcs(new Coordinate(800 +i*1000,170,70,70),"src/main/java/com/example/project_try/assets/RedOrc2.png",20,60,root,islands,orcs,chests,coins);
                orcs.add(orc3);
                orc3.AnimateGravity(islands,orcs,root);
                root.getChildren().addAll(orc3.imageView);


        }
    }



}
