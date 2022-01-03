package com.example.project_try;

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
import java.util.List;

public class Win {
    public void win(Stage stage){

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
        Label score = new Label("YOU WIN!!!");
        styleLabel(score,301,12,24);

        //menu button
        Button menuButton = new Button("Exit");
        styleButton(menuButton,301,321,121,35);


        EventHandler menuPrint = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.exit(0);
            }
        };

        menuButton.setOnAction(menuPrint);

        //setting scene
        Scene scene = new Scene(root, 723.2, 421);
//        scene.getRoot().requestFocus();
        root.getChildren().addAll(bg);
        root.getChildren().addAll(score);
        root.getChildren().addAll(menuButton);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public void styleLabel(Label label,double x,double y,double size){
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("System",size));
        label.setTextFill(Color.WHITE);
    }
    public void styleButton(Button button,double x,double y,double w,double h){
        button.setMinHeight(h);
        button.setMinWidth(w);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setTextFill(Color.DARKBLUE);
    }
}
