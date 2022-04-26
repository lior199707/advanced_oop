package com.animals;

import com.diet.Herbivore;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Turtle class representing the turtle animal. It can chew!
 * @see com.animals.AnimalChew
 */
public class Turtle extends AnimalChew {
    /**
     * constant double value of the default weight of a turtle.
     */
    private static final double DEFAULT_WEIGHT = 1.0;
    /**
     * constant integer value of the default age of a turtle.
     */
    private static final int DEFAULT_AGE = 1;
    /**
     * constant Point coordinates of the default starting location of a turtle.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(80, 0);
    private static final double SIZE_COEFFICIENT = 0.5;
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * integer value of the age of a turtle
     */
    private int age;

    /**
     * Turtle constructor.
     * setting default weight, default age and diet!
     * passing name and location to super.
     * @see com.animals.AnimalChew
     * @param name String representation of turtle name.
     */
    public Turtle(String name, int size, int horSpeed, int verSpeed, String col) {
        super(name, DEFAULT_STARTING_LOCATION,size,horSpeed,verSpeed,col);
        setWeight(0.5 * getSize());
        setAge(DEFAULT_AGE);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), getName());
    }

    /**
     * Turtle constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of turtle name.
     */
    public Turtle(String name, int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed, verSpeed, getDefaultColor());
    }

    /**
     * chew implementation of a turtle.
     */
    @Override
    public void chew() {
        MessageUtility.logSound(getName(), "Retracts its head in then eats quietly");
    }

    /**
     * age getter.
     * @return integer value of turtle age.
     */
    public int getAge() {
        MessageUtility.logGetter(getName(), "getAge", age);
        return age;
    }

    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    public static int getDefaultAge() {
        return DEFAULT_AGE;
    }
    /**
     * age setter.
     * checks if the age is valid (between min/max values).
     * if so, assigns the parameter value to the age field.
     * otherwise, it will not update it.
     *
     * @param age integer value indicating turtle age.
     * @return boolean value if set was successful or not.
     */
    public boolean setAge(int age) {
        boolean isSuccess = false;
        int MIN_AGE = 0, MAX_AGE = 500;
        if (age >= MIN_AGE && age <= MAX_AGE) {
            this.age = age;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setAge", age, isSuccess);
        return isSuccess;
    }

    /**
     * equals method of Turtle object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Turtle turtle = (Turtle) o;

        return getAge() == turtle.getAge();
    }

    /**
     * toString of elephant.
     * @return String representation of Turtle.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getAnimalName() {
        return "Turtle";
    }

    @Override
    public String animalShortPathName() {
        return "trt";
    }

    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }
}
