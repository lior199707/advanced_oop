package com.food.plants;

import com.utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {
    /**
     * Cabbage constructor.
     */
    public Cabbage() {
        MessageUtility.logConstractor("Cabbage", "Cabbage");
    }

    @Override
    public String foodShortPathName() {
        return "Cabbage";
    }

}
