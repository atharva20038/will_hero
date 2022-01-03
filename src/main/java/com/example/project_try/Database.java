package com.example.project_try;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Database implements Serializable {
    private static ArrayList<SaveGame> saveGames;
    Database(){
        saveGames = new ArrayList<>();
    }

    public ArrayList<SaveGame> getSaveGames() {
        return saveGames;
    }

    public void setSaveGames(ArrayList<SaveGame> saveGames) {
        this.saveGames = saveGames;
    }

}
