package com.food;

import com.utilities.MessageUtility;

public class Meat extends Food{

    public Meat() { MessageUtility.logConstractor("Meat", "Meat");}
    /*
     * (non-Javadoc)
     *
     * @see food.IFood#getFoodtype()
     */
    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
        return EFoodType.MEAT;
    }

    @Override
    public String foodShortPathName() {
        return "Meat";
    }
}
