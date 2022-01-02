package com.example.project_try;

import javafx.animation.AnimationTimer;

public abstract class Weapon extends Game_objects{
    protected int lifeReduced;
    AnimationTimer animationTimer;

    Weapon(Hero hero) {
        super(hero.coordinate, "src/main/java/com/example/project_try/assets/WeaponSword.png");
    }

    public int getLifeReduced() {
        return lifeReduced;
    }

    public abstract void upgrade(Hero hero);

    public void attack(Characters characters){
        characters.reduceLife(lifeReduced);
    }



}
