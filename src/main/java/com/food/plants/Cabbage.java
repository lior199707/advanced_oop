package com.food.plants;

import com.utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {

    //Ctor

    /**
     * Cabbage constructor.
     */
    public Cabbage() {
        MessageUtility.logConstractor("Cabbage", "Cabbage");
    }

    //end Ctor


    //override class Food
    /**
     * override abstract class Food animalShortPathName().
     * @return String representation of the short path name for loading Cabbage image.
     */
    @Override
    public String foodShortPathName() {
        return "Cabbage";
    }

    //end override class Food

}
//end class Cabbage
