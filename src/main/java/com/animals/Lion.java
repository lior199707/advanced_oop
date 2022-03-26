package com.animals;

import com.food.EFoodType;
import com.food.IEdible;
import com.utilities.MessageUtility;
import com.mobility.Point;

import java.util.Random;

public class Lion extends AnimalRoar {
    private static final double DEFAULT_WEIGHT = 408.2;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(20, 0);
    private static final int DEFAULT_SCAR_COUNT = 0;

    private int scarCount;

    public Lion(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        scarCount = DEFAULT_SCAR_COUNT;
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public Lion(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        scarCount = DEFAULT_SCAR_COUNT;
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        if (super.eat(food)) {
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                this.scarCount++;
                isSuccess = true;
            }
        }
        MessageUtility.logBooleanFunction(getName(), "eat", food, isSuccess);
        return isSuccess;
    }

    @Override
    public void roar() {
        MessageUtility.logSound(getName(), "Roars, then stretches and shakes its mane");
    }

    @Override
    public EFoodType getFoodtype() {
        return EFoodType.NOTFOOD;
    }

    public int getScarCount() {
        MessageUtility.logGetter(getName(), "getScarCount", scarCount);
        return scarCount;
    }
}
