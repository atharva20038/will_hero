package com.example.project_try;

public class Sword extends Weapon{
    Sword(int lifeReduced,Hero hero){
        super(hero);
        this.lifeReduced = lifeReduced;
    }

    @Override
    public void upgrade(Hero hero) {
        lifeReduced += 15;
    }
}
