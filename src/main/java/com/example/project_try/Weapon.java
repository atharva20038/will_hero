package com.example.project_try;

import javafx.animation.AnimationTimer;

import java.io.Serializable;

public abstract class Weapon extends Game_objects{
    protected int lifeReduced;


    Weapon(Hero hero,String image) {
        super(hero.coordinate,image );
    }

    public int getLifeReduced() {
        return lifeReduced;
    }

    public abstract void upgrade(Hero hero);

    public void attack(Characters characters){
        characters.reduceLife(lifeReduced);
    }



}
