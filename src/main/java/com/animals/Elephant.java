package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Elephant class representing the elephant animal. It can chew!
 * @see com.animals.AnimalChew
 */
public class Elephant extends AnimalChew {
    /**
     * constant double value of the default weight of an elephant.
     */
    private static final double DEFAULT_WEIGHT = 500.00;

    private static final double SIZE_COEFFICIENT = 10;
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * constant Point coordinates of the default starting location of an elephant.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 90);
    /**
     * constant double value of the default trunk length of an elephant.
     */
    private static final double DEFAULT_TRUNK_LENGTH = 1.0;
    /**
     * double value of the trunk length of an elephant.
     */
    private double trunkLength;

    /**
     * Elephant constructor.
     * setting default weight, default trunk length and diet!
     * passing name and location to super.
     * @see com.animals.AnimalChew
     * @param name String representation of elephant name.
     */

    public Elephant(String name, int size, int horSpeed, int verSpeed, String col) {
        super(name, DEFAULT_STARTING_LOCATION, size, horSpeed, verSpeed, col);
        setWeight(getSize() * 10);
        setTrunkLength(DEFAULT_TRUNK_LENGTH);
        setDiet(new Herbivore());
        loadImages(animalShortPathName());
        MessageUtility.logConstractor(getClass().getSimpleName(), getName());
    }

    /**
     * Elephant constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of elephant name.
     */
    public Elephant(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }


    /**
     * chew implementation of an elephant.
     */
    @Override
    public void chew() {
        MessageUtility.logSound(this.getName(), "Trumpets with joy while flapping its ears, then chews");
    }

    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }
    /**
     * trunk length getter.
     * @return double value of the trunk length.
     */
    public double getTrunkLength() {
        MessageUtility.logGetter(this.getName(), "getTrunkLength", this.trunkLength);
        return trunkLength;
    }

    public static double getDefaultTrunkLength() {
        return DEFAULT_TRUNK_LENGTH;
    }
    /**
     * trunk length setter.
     * checks if the trunk length is valid (between min/max values).
     * if so, assigns the parameter value to the trunkLength field.
     * otherwise, it will not update it.
     *
     * @param trunkLength double value to set.
     * @return boolean value if set was successful or not.
     */
    public boolean setTrunkLength(double trunkLength) {
        boolean isSuccess = false;
        double MIN_LENGTH = 0.5, MAX_LENGTH = 3.0;
        if (trunkLength >= MIN_LENGTH && trunkLength <= MAX_LENGTH) {
            this.trunkLength = trunkLength;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setTrunkLength", trunkLength, isSuccess);
        return isSuccess;
    }

    /**
     * equals method of Elephant object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Elephant elephant = (Elephant) o;

        return Double.compare(elephant.getTrunkLength(), getTrunkLength()) == 0;
    }

    /**
     * toString of elephant.
     * @return String representation of Elephant.
     */
    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public String getAnimalName() {
        return "Elephant";
    }

    @Override
    public String animalShortPathName() {
        return "elf";
    }

    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }
}
