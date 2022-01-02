package com.example.project_try;

import java.io.Serializable;

public class Lance extends Weapon{
    Lance(int lifeReduced,Hero hero) {
        super(hero,"src/main/java/com/example/project_try/assets/WeaponChampionLance.png");
        imageView.setFitWidth(5);
        imageView.setFitHeight(20);
        this.lifeReduced = lifeReduced;
    }

    @Override
    public void upgrade(Hero hero) {
        lifeReduced += 15;
    }
}
