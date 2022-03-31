package com.animals;

import com.diet.Herbivore;
import com.food.EFoodType;
import com.utilities.MessageUtility;
import com.mobility.Point;

/**
 * Giraffe class representing the Giraffe animal. It can chew!
 * @see com.animals.AnimalChew
 */
public class Giraffe extends AnimalChew {
    /**
     * constant double value of the default weight of a giraffe.
     */
    private static final double DEFAULT_WEIGHT = 450.00;
    /**
     * constant double value of the default neck length of a giraffe.
     */
    private static final double DEFAULT_NECK_LENGTH = 1.5;
    /**
     * constant Point coordinates of the default starting location of a giraffe.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 0);
    /**
     * double value of the neck length of a giraffe.
     */
    private double neckLength;



    /**
     * Giraffe constructor.
     * setting default weight, default neck length and diet!
     * passing name and location to super.
     * @see com.animals.AnimalChew
     * @param name String representation of giraffe name.
     * @param location Point coordinates indicating giraffe location.
     */
    public Giraffe(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setNeckLength(DEFAULT_NECK_LENGTH);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    /**
     * Giraffe constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of giraffe name.
     */
    public Giraffe(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    /**
     * chew implementation of a giraffe.
     */
    @Override
    public void chew() {
        MessageUtility.logSound(this.getName(), "Bleats and Stomps its legs, then chews");
    }

    /**
     * food type getter.
     * @return EFoodType.MEAT
     */
    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }


    /**
     * neck length getter.
     * @return double value of the neck length.
     */
    public double getNeckLength() {
        MessageUtility.logGetter(this.getName(), "getNeckLength", this.neckLength);
        return neckLength;
    }

    /**
     * neck length setter.
     * checks if the neck length is valid (between min/max values).
     * if so, assigns the parameter value to the neckLength field.
     * otherwise, it will not update it.
     *
     * @param neckLength double value to set.
     * @return boolean value if set was successful or not.
     */
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

    /**
     * equals method of Giraffe object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Giraffe giraffe = (Giraffe) o;

        return Double.compare(giraffe.getNeckLength(), getNeckLength()) == 0;
    }

    /**
     * toString of giraffe.
     * @return String representation of Giraffe.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
