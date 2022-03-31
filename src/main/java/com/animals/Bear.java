package com.animals;

import com.diet.Omnivore;
import com.food.EFoodType;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Bear class representing the Bear animal. It can roar!
 * @see com.animals.AnimalRoar
 */
public class Bear extends AnimalRoar {
    /**
     * constant double value of the default weight of a bear.
     */
    private static final double DEFAULT_WEIGHT = 308.2;
    /**
     * constant Point coordinates of the default starting location of a bear.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(100, 5);
    /**
     * FurColors enum indicating the fur color of a bear.
     */
    private FurColors furColor;

    /**
     * private enum class to hold different fur colors
     */
    private enum FurColors {
        WHITE("WHITE"),
        GRAY("GRAY"),
        BLACK("BLACK");

        /**
         * String representation of the fur color.
         */
        private final String color;

        /**
         * FurColor constructor.
         * can be initialized with a String representation of the fur color.
         * @param color String representation of the fur color.
         */
        FurColors(String color) {
            this.color = color;
        }
    }

    /**
     * Bear constructor.
     * setting default weight, fur color and diet!
     * passing name and location to super.
     * @see com.animals.AnimalRoar
     * @param name String representation of bear name.
     * @param location Point coordinates indicating bear location.
     */
    public Bear(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setFurColor(FurColors.GRAY);
        setDiet(new Omnivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }


    /**
     * Bear constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of bear name.
     */
    public Bear(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    /**
     * furColor  setter.
     * traverses the FurColors enum, if value of given param is equal it updates the furColor field.
     * leaving the field as is.
     *
     * @param furColor the color to set.
     * @return boolean value if the color was found & set or not.
     */
    public boolean setFurColor(FurColors furColor) {
        boolean isSuccess = false;
        for (FurColors f : FurColors.values()) {
            if (f.name().equals(furColor.toString())) {
                this.furColor = furColor;
                isSuccess = true;
                break;
            }
        }
        MessageUtility.logSetter(getName(), "setFurColor", getFurColor(), isSuccess);
        return isSuccess;
    }

    /**
     * furColor getter.
     * @return furColor value of bear.
     */
    public FurColors getFurColor() {
        MessageUtility.logGetter(getName(), "getFurColor", furColor);
        return furColor;
    }

    /**
     * roar implementation of a bear.
     */
    @Override
    public void roar() {
        MessageUtility.logSound(getName(), "Stands on its hind legs, roars and scratches its belly");
    }

    /**
     * food type getter.
     * @return EFoodType MEAT.
     */
    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    /**
     * equals method of Bear object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bear bear = (Bear) o;

        return getFurColor() == bear.getFurColor();
    }

    /**
     * toString of bear.
     * @return String representation of Bear.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
