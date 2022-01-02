package com.example.project_try;

public class Lance extends Weapon{
    Lance(int lifeReduced,Hero hero) {
        super(hero);
        this.lifeReduced = lifeReduced;
    }

    @Override
    public void upgrade(Hero hero) {
        lifeReduced += 15;
    }
}
