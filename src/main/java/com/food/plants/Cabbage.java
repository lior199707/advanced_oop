package com.food.plants;

/**
 * Cabbage class, a Plant descendant.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class Cabbage extends Plant {
    private static Cabbage instance = null;
    /**
     * Cabbage constructor.
     */
    private Cabbage() { }

    public static Cabbage getInstance() {
        if (instance == null)
            instance = new Cabbage();
        return instance;
    }

    /**
     * override abstract class Food animalShortPathName().
     * @return String representation of the short path name for loading Cabbage image.
     */
    @Override
    public String foodShortPathName() {
        return "Cabbage";
    }

}
