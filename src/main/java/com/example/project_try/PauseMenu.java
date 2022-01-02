package com.example.project_try;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

public class PauseMenu implements Serializable{
    User user = new User();
    Game gameMenu = new Game(user,this,null);
    transient Stage stage;
    transient Label scoreLabel;
    transient Label coinCount;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void start(Stage stage) throws IOException {
        this.stage = stage;
        //establishing anchor pane
        AnchorPane root = new AnchorPane();

        //image background
        ImageView bg = new ImageView();
        Image image = null;

        try {
            image = new Image(new FileInputStream("src/main/java/com/example/project_try/back.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bg.setImage(image);
        bg.setFitHeight(421);
        bg.setFitWidth(723.2);

        //Score label
        Label score = new Label("SCORE : ");
        styleLabel(score,301,12,24);

        //Score
        scoreLabel = new Label(gameMenu.getScore()+"");
        styleLabel(scoreLabel,402,5,36);

//       Coin Count
        coinCount = new Label(gameMenu.getCoinNumber()+"");
        styleCoinLabel(coinCount,558,17,20);

        //Coin Image
        ImageView coin = new ImageView();
        Image coinImage = null;

        try {
            coinImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        coin.setImage(coinImage);
        setImage(coin,600,16,32,32);

        //SideDecor Image
        ImageView sideDecor = new ImageView();
        Image sideDecorImage = null;

        try {
            sideDecorImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/WeaponShuriken.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sideDecor.setImage(sideDecorImage);
        setImage(sideDecor,435,88,35,35);

        ImageView sideDecor2 = new ImageView();
        sideDecor2.setImage(sideDecorImage);
        setImage(sideDecor2,256,88,35,35);

        ImageView sideDecor3 = new ImageView();
        sideDecor3.setImage(sideDecorImage);
        setImage(sideDecor3,256,357,35,35);

        ImageView sideDecor4 = new ImageView();
        sideDecor4.setImage(sideDecorImage);
        setImage(sideDecor4,435,357,35,35);

        //side decor orc
        ImageView orc = new ImageView();
        Image orcImage = null;

        try {
            orcImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/RedOrc1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        orc.setImage(orcImage);
        setImage(orc,561,135,65,74);
        orc.setRotate(37.4);

        //side decor hero
        ImageView hero = new ImageView();
        Image heroImage = null;

        try {
            heroImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Helmet4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        hero.setImage(heroImage);
        setImage(hero,79,135,67,72);
        hero.setRotate(-28.7);

        //side decor weapon
        ImageView weapon = new ImageView();
        Image weaponImage = null;

        try {
            weaponImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/WeaponAxe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        weapon.setImage(weaponImage);
        setImage(weapon,170,121,32,54);


        //resume button
        Button resumeButton = new Button("Resume");
        styleButton(resumeButton,301,121,121,35);

        EventHandler printResume = new EventHandler() {
            @Override
            public void handle(Event event) {
                buttonAction(1);
            }
        };

        resumeButton.setOnAction(printResume);

        //save button
        Button saveButton = new Button("Load Game");
        styleButton(saveButton,301,189,121,35);


        EventHandler savePrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                buttonAction(2);
//                try {
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };

        saveButton.setOnAction(savePrint);

        //New Game button
        Button newGameButton = new Button("New Game");
        styleButton(newGameButton,301,258,121,35);


        EventHandler newGamePrint = new EventHandler() {
            @Override
            public void handle(Event event) {
               buttonAction(3);
            }

        };

        newGameButton.setOnAction(newGamePrint);

        //menu button
        Button menuButton = new Button("Exit");
        styleButton(menuButton,301,321,121,35);

        EventHandler menuPrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                buttonAction(4);
            }
        };

        menuButton.setOnAction(menuPrint);

        //adding elements to root
        root.getChildren().addAll(bg);
        root.getChildren().addAll(scoreLabel);
        root.getChildren().addAll(sideDecor);
        root.getChildren().addAll(sideDecor2);
        root.getChildren().addAll(sideDecor3);
        root.getChildren().addAll(sideDecor4);
        root.getChildren().addAll(orc);
        root.getChildren().addAll(hero);
        root.getChildren().addAll(weapon);
        root.getChildren().addAll(score);
        root.getChildren().addAll(coinCount);
        root.getChildren().addAll(coin);
        root.getChildren().addAll(resumeButton);
        root.getChildren().addAll(saveButton);
        root.getChildren().addAll(newGameButton);
        root.getChildren().addAll(menuButton);

        //setting scene
        Scene scene = new Scene(root, 723.2, 421);
//        scene.getRoot().requestFocus();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void styleButton(Button button,double x,double y,double w,double h){
        button.setMinHeight(h);
        button.setMinWidth(w);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setTextFill(Color.DARKBLUE);
    }

    public void styleLabel(Label label,double x,double y,double size){
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("System",size));
        label.setTextFill(Color.WHITE);
    }

    public void styleCoinLabel(Label label,double x,double y,double size){
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("System",size));
        label.setTextFill(Color.YELLOW);
    }

    public void setImage(ImageView image,double x,double y,double w, double h){
        image.setFitWidth(w);
        image.setFitHeight(h);
        image.setLayoutX(x);
        image.setLayoutY(y);
    }

    public void SaveGame() throws IOException, ClassNotFoundException, InterruptedException {
        FileOutputStream fileOutputStream = new FileOutputStream("out.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        SaveGame saveGame = new SaveGame(gameMenu);
        outputStream.writeObject(saveGame);
        outputStream.close();
        fileOutputStream.close();
        //Exit To Main Menu

    }

    public SaveGame LoadGame() throws IOException, ClassNotFoundException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream("out.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        SaveGame saveGame = (SaveGame) inputStream.readObject();
//        saveGame.game.display(stage);

        saveGame.game.loadPreviousGame(stage,saveGame.game);
        inputStream.close();
        fileInputStream.close();
        return saveGame;
    }

    public void buttonAction(int verbose){
        switch (verbose){
            case 1:
                //resume
                System.out.println("Resume Game");
                try {
                    LoadGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                //Load Saved Game
                System.out.println("Load Game");
                break;
            case 3:
                PauseMenu pauseMenu = this;
                //New Game
                System.out.println("Load New Game");
                try {
                    Game game = new Game(user,pauseMenu,null);
                    gameMenu = game;
                    gameMenu.display(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                //Exit
                System.out.println("Exit");
                System.exit(0);
                break;
        }
    }


}