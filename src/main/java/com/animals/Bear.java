package com.animals;

import com.diet.Omnivore;
import com.food.EFoodType;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Bear
 */
public class Bear extends AnimalRoar {
    private static final double DEFAULT_WEIGHT = 308.2;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(100, 5);
    private FurColors furColor;

    private enum FurColors {
        WHITE("WHITE"),
        GRAY("GRAY"),
        BLACK("BLACK");

        private final String color;

        FurColors(String color) {
            this.color = color;
        }
    }

    public Bear(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setFurColor(FurColors.GRAY);
        setDiet(new Omnivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public Bear(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        setFurColor(FurColors.GRAY);
        setDiet(new Omnivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

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

    public FurColors getFurColor() {
        MessageUtility.logGetter(getName(), "getFurColor", furColor);
        return furColor;
    }

    @Override
    public void roar() {
        MessageUtility.logSound(getName(), "Stands on its hind legs, roars and scratches its belly");
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bear bear = (Bear) o;

        return getFurColor() == bear.getFurColor();
    }

    @Override
    public String toString() {
        return super.toString() + " : " + getClass().getSimpleName();
    }
}
