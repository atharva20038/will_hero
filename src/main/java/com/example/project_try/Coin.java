package com.example.project_try;

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
