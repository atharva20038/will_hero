package com.example.project_try;

public class CoinChest extends Chest{

    CoinChest(Coordinate coordinate, String image) {
        super(coordinate, image);
    }

    @Override
    public void afterOpen(Hero hero) {
        hero.setCoinScore(hero.getCoinScore()+30);
    }
}
