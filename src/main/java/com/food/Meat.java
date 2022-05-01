package com.food;

/**
 * class Meat, handles food of type meat, extends abstract class Food.
 */
public class Meat extends Food{

    /**
     * Meat constructor.
     */
    public Meat() { }

    /**
     * @see com.food.IEdible#getFoodType()
     * @return Enum representing the food type of meat object.
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.MEAT;
    }

    /**
     * @return String representation of the short path name for loading Meat image.
     */
    @Override
    public String foodShortPathName() {
        return "Meat";
    }
}
