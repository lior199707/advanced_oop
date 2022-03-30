package com.animals;

import com.diet.Herbivore;
import com.food.EFoodType;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Bear
 */
public class Elephant extends AnimalChew {
    private static final double DEFAULT_WEIGHT = 500.00;
    private static final double DEFAULT_TRUNK_LENGTH = 1.0;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(50, 90);
    private double trunkLength;

    public Elephant(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setTrunkLength(DEFAULT_TRUNK_LENGTH);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(this.getClass().getSimpleName(), name);
    }

    public Elephant(String name) {
        super(name, DEFAULT_STARTING_LOCATION);
        setWeight(DEFAULT_WEIGHT);
        setTrunkLength(DEFAULT_TRUNK_LENGTH);
        setDiet(new Herbivore());
        MessageUtility.logConstractor(this.getClass().getSimpleName(), name);
    }

    @Override
    public void chew() {
        MessageUtility.logSound(this.getName(), "Trumpets with joy while flapping its ears, then chews");
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getName(), "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    public double getTrunkLength() {
        MessageUtility.logGetter(this.getName(), "getTrunkLength", this.trunkLength);
        return trunkLength;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Elephant elephant = (Elephant) o;

        return Double.compare(elephant.getTrunkLength(), getTrunkLength()) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + getClass().getSimpleName();
    }
}
