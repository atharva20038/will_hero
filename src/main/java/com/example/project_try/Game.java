package com.example.project_try;



import javafx.animation.*;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private PauseMenu helloApplication;
    private Game previousGame;
    private User user;
    private int score;
    private ArrayList<Chest> chests;
    private ArrayList<Coin> coins;
    private ArrayList<Islands> islands;
    private ArrayList<Orcs> orcs;
    private Hero heroObj;
    private transient AnchorPane root;
    private transient Label scoreLabel;
    private transient Label coinCount;
    private double sceneX;
    private double sceneY;
    private transient AnimationTimer animationTimer;
    private double labelScore;
    private double labelCoin;
    private int coinNumber;

    Game(User user, PauseMenu pauseMenu,Game game){
        previousGame = game;
        this.user = user;
        user.setCurrentGame(this);
        islands = new ArrayList<>();
        score = 0;
        this.helloApplication = pauseMenu;
        chests = new ArrayList<>();
        coins = new ArrayList<>();
        orcs = new ArrayList<>();
        coinNumber = 0;
    }

    public double getLabelCoin() {
        return labelCoin;
    }

    public double getLabelScore() {
        return labelScore;
    }

    public int getCoinNumber() {
        return coinNumber;
    }

    public void animateScene(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                sceneX = root.getLayoutX();
                sceneY = root.getLayoutY();
                labelCoin = coinCount.getLayoutX();
                labelScore = scoreLabel.getLayoutX();
                coinNumber = Integer.parseInt(coinCount.getText());

            }
        };

        animationTimer.start();
    }

    public Stage display(Stage stage) throws FileNotFoundException, InterruptedException {
        System.out.println("New Game");

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

        PauseMenu helloApplication = this.helloApplication;
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



                }else if(key.getCode()==KeyCode.S){
                    try {
                        helloApplication.SaveGame();
                        helloApplication.start(stage);

                        helloApplication.start(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
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
        animateScene();
        return stage;
    }


    public double getSceneX() {
        return sceneX;
    }

    public double getSceneY() {
        return sceneY;
    }

    void endGame() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Game Over");
        helloApplication.SaveGame();
        helloApplication.start(helloApplication.stage);
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

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public ArrayList<Orcs> getOrcs() {
        return orcs;
    }

    public ArrayList<Islands> getIslands() {
        return islands;
    }

    public Hero getHeroObj() {
        return heroObj;
    }

    public int getScore() {
        return score;
    }


    private void createWorld2(AnchorPane root) throws FileNotFoundException {
        islands = previousGame.getIslands();

        //Island Spawning
        for(int i=0;i<100;i++){
            islands.get(i*3+0).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/BalancingRocks4.png"))));
            islands.get(i*3+1).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/BalancingRocks5.png"))));
            islands.get(i*3+2).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/BalancingRocks3.png"))));
            root.getChildren().addAll(islands.get(i*3+0).getImageView());
            root.getChildren().addAll(islands.get(i*3+1).getImageView());
            root.getChildren().addAll(islands.get(i*3+2).getImageView());
        }
        chests = previousGame.getChests();
        int j =0;
        //Spawning Chest
        for(int i =0;i<100;i+=2){

            if(!chests.get(j).getIsOpen()){
                chests.get(j).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/ChestClosed.png"))));
            }else{
                chests.get(j).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/ChestClosed.png"))));
            }

            root.getChildren().addAll(chests.get(j).getImageView());
            j++;
        }

        coins = previousGame.getCoins();

        //Coin Spawn
        for (int i=0;i<100;i++){
            coins.get(i*6+0).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));
            coins.get(i*6+1).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));
            coins.get(i*6+2).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));
            coins.get(i*6+3).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));
            coins.get(i*6+4).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));
            coins.get(i*6+5).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"))));


            if(!coins.get(6*i+0).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+0).getImageView());
            }
            if(!coins.get(6*i+1).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+1).getImageView());
            }
            if(!coins.get(6*i+2).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+2).getImageView());
            }
            if(!coins.get(6*i+3).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+3).getImageView());
            }
            if(!coins.get(6*i+4).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+4).getImageView());
            }
            if(!coins.get(6*i+5).getIsCollected()){
                root.getChildren().addAll(coins.get(i*6+5).getImageView());
            }
        }



        orcs = previousGame.getOrcs();

        for(int i=0;i<99;i++){

//                Orcs orc1 = new Orcs(new Coordinate(120 +i*1000,170,40,50),"src/main/java/com/example/project_try/assets/Orc1.png",10,30,root,islands,orcs,chests,coins);
//                orcs.add(orc1);
//                orc1.AnimateGravity(islands,orcs,root);
//                root.getChildren().addAll(orc1.imageView);

            orcs.get(i*2+0).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/RedOrc1.png"))));
            orcs.get(i*2+1).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/RedOrc2.png"))));
            orcs.get(i*2).AnimateGravity(islands,orcs,root);
            orcs.get(i*2+1).AnimateGravity(islands,orcs,root);
            orcs.get(i*2).setRoot(root);
            orcs.get(i*2+1).setRoot(root);

            if(!orcs.get(i*2).isDead()){
                root.getChildren().addAll(orcs.get(i*2).getImageView());
            }
            if(!orcs.get(i*2+1).isDead()){
                root.getChildren().addAll(orcs.get(i*2+1).getImageView());
            }



        }
    }

    void loadPreviousGame(Stage stage, Game game) throws FileNotFoundException, InterruptedException {
//        user.setCurrentGame(game);
        this.previousGame = game;

//        AnchorPane root = new AnchorPane();
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
        scoreLabel = new Label(game.getScore()+"");
        score = game.getScore();
        scoreLabel.setFont(new Font("System",42));
        scoreLabel.setLayoutX(game.getLabelScore());
        scoreLabel.setLayoutY(10);
        scoreLabel.setTextFill(Color.WHITE);


        //coinImage
        Image coinImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"));
        ImageView coin = new ImageView();
        coin.setFitHeight(27);
        coin.setFitWidth(27);
        coin.setLayoutY(9);
        coin.setLayoutX(game.getLabelCoin());
        coin.setImage(coinImage);

        //Label CoinCount
        coinCount = new Label(game.getHeroObj().getCoinCount()+"");
        coinCount.setLayoutX(game.getLabelCoin()-40);
        coinCount.setLayoutY(7);
        coinCount.setTextFill(Color.YELLOW);
        coinCount.setFont(new Font("System",21));

        //adding to stage
        root.getChildren().addAll(bg);
        root.getChildren().addAll(scoreLabel);
        root.getChildren().addAll(coin);
        root.getChildren().addAll(coinCount);

        //hero
        heroObj = game.getHeroObj();
        heroObj.setRoot(root);
        heroObj.getHelmet().getWeapons().get(0).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/WeaponSword.png"))));
        heroObj.getHelmet().getWeapons().get(1).setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/WeaponChampionLance.png"))));


        heroObj.setCoinCount(coinCount);
        heroObj.setCoinScore(game.getHeroObj().getCoinScore());
        heroObj.setImageView(new ImageView(new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Helmet4.png"))));



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



                }else if(key.getCode()==KeyCode.S){
                    try {
                        helloApplication.SaveGame();
                        helloApplication.start(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Wrong Input");
                }


            }
        };

//        scene.setOnKeyPressed(onKeyPressed);
//        //adding to stage
//        root.getChildren().addAll(bg);
//        root.getChildren().addAll(scoreLabel);
//        root.getChildren().addAll(coin);
//        root.getChildren().addAll(coinCount);

        createWorld2(root);
//        Thread.currentThread().sleep(2);
        heroObj.collide();
        heroObj.AnimateGravity(islands);
        scene.setOnKeyPressed(onKeyPressed);
        root.getChildren().addAll(heroObj.getImageView());
        if(game.getHeroObj().getHelmet().getActiveWeapon()!=null){
            root.getChildren().addAll(game.getHeroObj().getHelmet().getActiveWeapon().imageView);
        }
        scene.getRoot().setLayoutY(game.getSceneY());
        scene.getRoot().setLayoutX(game.getSceneX());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        animateScene();
        scene.getRoot().requestFocus();

    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
