package com.example.project_try;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class Helmet{
    final private ArrayList<Weapon> weapons;
    private Weapon activeWeapon;
    private AnimationTimer animationTimer;

    Helmet(Hero hero){
        weapons = new ArrayList<>();
        weapons.add(new Sword(0, hero));
        weapons.add(new Lance(0, hero));
        activeWeapon = null;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(activeWeapon!=null){
                    Coordinate temp = new Coordinate(hero.getCoordinate().getXEnd()-8,(int)hero.imageView.getBoundsInParent().getMinY()-10,20,50);
                    activeWeapon.setCoordinate(temp);

                }
            }
        };

        animationTimer.start();
    }



    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }


    public void attack(Characters characters){
        characters.reduceLife(activeWeapon.getLifeReduced());
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void setActiveWeapon(Weapon activeWeapon) {
        this.activeWeapon = activeWeapon;
    }
}
