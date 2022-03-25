package main.java.com.animals;

import main.java.com.diet.Herbivore;
import main.java.com.food.EFoodType;
import main.java.com.mobility.Point;
import main.java.com.utilities.MessageUtility;

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
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public Turtle(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        setAge(DEFAULT_AGE);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
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
}
