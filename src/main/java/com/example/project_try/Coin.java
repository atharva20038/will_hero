package com.example.project_try;

import java.io.Serializable;

public class Coin extends Game_objects{
    private boolean isCollected;

    Coin(Coordinate coordinate, String image) {
        super(coordinate, image);
        isCollected = false;
    }

    public void collectCoin(){
        isCollected = true;
    }

    public boolean getIsCollected(){
        return isCollected;
    }
}
