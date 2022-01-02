package com.example.project_try;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Helmet implements Serializable {
    private static Helmet helmet;
    final private ArrayList<Weapon> weapons;
    private Weapon activeWeapon;
    private transient AnimationTimer animationTimer;
    private transient AnchorPane root;

    private Helmet(Hero hero, AnchorPane root) {
        this.root = root;
        weapons = new ArrayList<>();
        weapons.add(new Sword(0, hero));
        weapons.add(new Lance(0, hero));
        activeWeapon = null;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (activeWeapon != null) {
                    Coordinate temp = new Coordinate((int)hero.getImageView().getBoundsInParent().getMinX() - 8, (int) hero.imageView.getBoundsInParent().getMinY() - 10, 20, 50);
                    activeWeapon.setCoordinate(temp);
                }
            }

        };

        animationTimer.start();
    }

    public static Helmet getInstance(Hero hero,AnchorPane root) {
        if(helmet==null){
            helmet = new Helmet(hero,root);
        }

        return helmet;
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
