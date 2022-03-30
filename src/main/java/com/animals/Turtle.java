package com.animals;

import com.diet.Herbivore;
import com.food.EFoodType;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Bear
 */
public class Turtle extends AnimalChew {
    private static final double DEFAULT_WEIGHT = 1.0;
    private static final int DEFAULT_AGE = 1;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(80, 0);
    private int age;

    public Turtle(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setAge(DEFAULT_AGE);
        setDiet(new Herbivore());
    }

    public Turtle(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    @Override
    public void chew() {
        MessageUtility.logSound(getName(), "Retracts its head in then eats quietly");
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    public int getAge() {
        MessageUtility.logGetter(getName(), "getAge", age);
        return age;
    }

    public boolean setAge(int age) {
        boolean isSuccess = false;
        int MIN_LENGTH = 0, MAX_LENGTH = 500;
        if (age >= MIN_LENGTH && age <= MAX_LENGTH) {
            this.age = age;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setAge", getAge(), isSuccess);
        return isSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Turtle turtle = (Turtle) o;

        return getAge() == turtle.getAge();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getClass().getSimpleName();
    }
}
