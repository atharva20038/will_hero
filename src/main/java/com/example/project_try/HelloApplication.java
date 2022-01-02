package com.example.project_try;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
        Label scoreLabel = new Label("SCORE : ");
        styleLabel(scoreLabel,301,12,24);

        //Score
        Label score = new Label("0");
        styleLabel(score,402,5,36);

        //Coin Count
        Label count = new Label("0");
        styleCoinLabel(count,558,17,20);

        //Coin Image
        ImageView coin = new ImageView();
        Image coinImage = null;

        try {
            coinImage = new Image(new FileInputStream("src/main/java/com/example/project_try/assets/Coin.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        coin.setImage(coinImage);
        setImage(coin,586,16,32,32);

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
                System.out.println("Resume Game");
            }
        };

        resumeButton.setOnAction(printResume);

        //save button
        Button saveButton = new Button("Save Game");
        styleButton(saveButton,301,189,121,35);


        EventHandler savePrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Save Game");
            }
        };

        saveButton.setOnAction(savePrint);

        //New Game button
        Button newGameButton = new Button("New Game");
        styleButton(newGameButton,301,258,121,35);

        EventHandler newGamePrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Load New Game");
            }
        };

        newGameButton.setOnAction(newGamePrint);

        //menu button
        Button menuButton = new Button("Main Menu");
        styleButton(menuButton,301,321,121,35);

        EventHandler menuPrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Load Main Menu");
                Game gameMenu = new Game();
                try {
                    gameMenu.display(stage);
                } catch (FileNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
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
        root.getChildren().addAll(count);
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

    public static void main(String[] args) {
        launch();
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


}