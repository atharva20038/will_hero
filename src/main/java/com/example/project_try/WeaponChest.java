package com.example.project_try;

public class WeaponChest extends Chest{
    private int verbose;

    WeaponChest(Coordinate coordinate, String image) {
        super(coordinate, image);
        verbose = (int)(Math.random()*100)%2;
    }

    @Override
    public void afterOpen(Hero hero) {
        //weapon upgrade
        hero.getHelmet().getWeapons().get(verbose).upgrade(hero);

        if(hero.getHelmet().getActiveWeapon()==null){
            hero.getHelmet().setActiveWeapon(hero.getHelmet().getWeapons().get(verbose));
        }
    }
}
