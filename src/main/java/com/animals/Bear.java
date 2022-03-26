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

        private FurColors(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    public Bear(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setFurColor(FurColors.GRAY.toString());
        setDiet(new Omnivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public Bear(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        setFurColor(FurColors.GRAY.toString());
        setDiet(new Omnivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public boolean setFurColor(String furColor) {
        boolean isSuccess = false;
        for (FurColors f : FurColors.values()) {
            if (f.name().equals(furColor)) {
                isSuccess = true;
            }
        }
        MessageUtility.logSetter(getName(), "setFurColor", getFurColor(), isSuccess);
        return isSuccess;
    }

    public FurColors getFurColor() {
        MessageUtility.logGetter(getName(), "getFurColor", furColor.getColor());
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
}
