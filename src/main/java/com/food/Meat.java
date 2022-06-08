package com.food;

/**
 * class Meat, handles food of type meat, extends abstract class Food.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class Meat extends Food{
    private static Meat instance = null;
    /**
     * Meat constructor.
     */
    private Meat() { }

    public static Meat getInstance() {
        if (instance == null){
            instance = new Meat();
        }
        return instance;
    }

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
