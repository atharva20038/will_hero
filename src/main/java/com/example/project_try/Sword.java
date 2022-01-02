package com.example.project_try;

import java.io.Serializable;

public class Sword extends Weapon{
    Sword(int lifeReduced,Hero hero){
        super(hero,"src/main/java/com/example/project_try/assets/WeaponSword.png");
        this.lifeReduced = lifeReduced;
    }

    @Override
    public void upgrade(Hero hero) {
        lifeReduced += 15;
    }
}
