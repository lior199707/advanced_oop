package com.food;

/**
 * class Meat, handles food of type meat, extends abstract class Food.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
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
