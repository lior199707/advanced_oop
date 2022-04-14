package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;
import com.utilities.MessageUtility;

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

    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / 2.2);
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
     */
    public Giraffe(String name, int size, int horSpeed, int verSpeed, String col){
        super(name, DEFAULT_STARTING_LOCATION,size, horSpeed, verSpeed, col);
        setWeight(getSize() * 2.2);
        setNeckLength(DEFAULT_NECK_LENGTH);
        setDiet(new Herbivore());
        loadImages(animalShortPathName());
        MessageUtility.logConstractor(getClass().getSimpleName(), getName());
    }

    /**
     * Giraffe constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of giraffe name.
     */
    public Giraffe(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * chew implementation of a giraffe.
     */
    @Override
    public void chew() {
        MessageUtility.logSound(this.getName(), "Bleats and Stomps its legs, then chews");
    }

    /**
     * neck length getter.
     * @return double value of the neck length.
     */
    public double getNeckLength() {
        MessageUtility.logGetter(this.getName(), "getNeckLength", this.neckLength);
        return neckLength;
    }

    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    public static double getDefaultNeckLength() {
        return DEFAULT_NECK_LENGTH;
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

    @Override
    public String getAnimalName() {
        return "Giraffe";
    }

    @Override
    public String animalShortPathName() {
        return "grf";
    }
}
