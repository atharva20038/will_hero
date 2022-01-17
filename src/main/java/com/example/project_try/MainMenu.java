package com.example.project_try;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMenu extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Database database = new Database();
        GameMenu pauseMenu = new GameMenu(database);
        pauseMenu.start(stage);
    }
}
