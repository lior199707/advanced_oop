package com.animals;

import com.diet.Herbivore;
import com.food.EFoodType;
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
     * @param location Point coordinates indicating turtle location.
     */
    public Turtle(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setAge(DEFAULT_AGE);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    /**
     * Turtle constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of turtle name.
     */
    public Turtle(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    /**
     * chew implementation of a turtle.
     */
    @Override
    public void chew() {
        MessageUtility.logSound(getName(), "Retracts its head in then eats quietly");
    }

    /**
     * food type getter.
     * @return EFoodType.MEAT
     */
    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    /**
     * age getter.
     * @return integer value of turtle age.
     */
    public int getAge() {
        MessageUtility.logGetter(getName(), "getAge", age);
        return age;
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
        MessageUtility.logSetter(getName(), "setAge", getAge(), isSuccess);
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
}
