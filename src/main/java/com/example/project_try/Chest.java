package com.example.project_try;


public abstract class Chest extends Game_objects {
    private boolean isOpen;

    Chest(Coordinate coordinate, String image) {
        super(coordinate, image);
        isOpen = false;
    }

    public void openChest(Hero hero){
        isOpen = true;
        afterOpen(hero);
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public abstract void afterOpen(Hero hero);

}
