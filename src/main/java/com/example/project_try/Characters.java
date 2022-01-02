package com.example.project_try;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Characters extends Game_objects{
    protected int life;
    protected ArrayList<Orcs> orcs;
    protected ArrayList<Islands> islands;
    protected ArrayList<Coin> coins;
    protected ArrayList<Chest> chests;
    protected AnchorPane root;


    Characters(Coordinate coordinate, String image,int life,AnchorPane root,ArrayList<Islands> islands,ArrayList<Orcs> orcs,ArrayList<Chest> chests,ArrayList<Coin> coins) {
        super(coordinate,image);
        this.life = life;
        this.root = root;
        this.islands = islands;
        this.orcs = orcs;
        this.chests = chests;
        this.coins = coins;
    }

    public void reduceLife(int lifeReduced){
        life -= lifeReduced;
    }


}
