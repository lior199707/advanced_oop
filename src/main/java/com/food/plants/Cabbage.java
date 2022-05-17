package com.food.plants;

/**
 * Cabbage class, a Plant descendant.
 */
public class Cabbage extends Plant {
    /**
     * Cabbage constructor.
     */
    public Cabbage() { }

    /**
     * override abstract class Food animalShortPathName().
     * @return String representation of the short path name for loading Cabbage image.
     */
    @Override
    public String foodShortPathName() {
        return "Cabbage";
    }

}
