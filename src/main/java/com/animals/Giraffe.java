package com.animals;

import com.diet.Herbivore;
import com.food.EFoodType;
import com.utilities.MessageUtility;
import com.mobility.Point;

/**
 * Bear
 */
public class Giraffe extends AnimalChew {
    private static final double DEFAULT_WEIGHT = 450.00;
    private static final double DEFAULT_NECK_LENGTH = 1.5;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 0);
    private double neckLength;

    public Giraffe(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setNeckLength(DEFAULT_NECK_LENGTH);
        setDiet(new Herbivore());
    }

    public Giraffe(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    @Override
    public void chew() {
        MessageUtility.logSound(this.getName(), "Bleats and Stomps its legs, then chews");
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    public double getNeckLength() {
        MessageUtility.logGetter(this.getName(), "getNeckLength", this.neckLength);
        return neckLength;
    }

    public boolean setNeckLength(double neckLength) {
        boolean isSuccess = false;
        double MIN_LENGTH = 1.0, MAX_LENGTH = 2.5;
        if (neckLength >= MIN_LENGTH && neckLength <= MAX_LENGTH) {
            this.neckLength = neckLength;
            isSuccess = true;
        }
        MessageUtility.logSetter(this.getName(), "setNeckLength", neckLength, isSuccess);
        return isSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Giraffe giraffe = (Giraffe) o;

        return Double.compare(giraffe.getNeckLength(), getNeckLength()) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + getClass().getSimpleName();
    }
}
