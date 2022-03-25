package main.java.com.animals;

import main.java.com.diet.Herbivore;
import main.java.com.food.EFoodType;
import main.java.com.utilities.MessageUtility;
import main.java.com.mobility.Point;

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
        MessageUtility.logConstractor(this.getClass().getSimpleName(), name);
    }

    public Giraffe(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        setNeckLength(DEFAULT_NECK_LENGTH);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(this.getClass().getSimpleName(), name);
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
        MessageUtility.logSetter(getName(), "setNeckLength", neckLength, isSuccess);
        return isSuccess;
    }
}
