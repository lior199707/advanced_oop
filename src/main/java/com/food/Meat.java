package com.food;

import com.utilities.MessageUtility;

/**
 * class Meat, handles food of type meat, extends abstract class Food.
 */
public class Meat extends Food{

    //Ctor

    public Meat() { MessageUtility.logConstractor("Meat", "Meat");}

    //end Ctor


    //override interface IEdible

    /**
     * @see com.food.IEdible#getFoodType()
     * @return Enum representing the food type of meat object.
     */
    @Override
    public EFoodType getFoodType() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
        return EFoodType.MEAT;
    }

    //end override interface IEdible


    //override class Food

    /**
     * @return String representation of the short path name for loading Meat image.
     */
    @Override
    public String foodShortPathName() {
        return "Meat";
    }

    //end override class Food
}
//end class Meat
