package com.animals;

import com.diet.Omnivore;
import com.mobility.Point;
import com.utilities.MessageUtility;

/**
 * Bear class representing the Bear animal. It can roar!
 * @see com.animals.AnimalRoar
 */
public class Bear extends AnimalRoar {
    /**
     * constant double value of the default weight of a bear.
     */
    private static final double DEFAULT_WEIGHT = 308.2;
    /**
     * constant Point coordinates of the default starting location of a bear.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(100, 5);
    private static final FurColors DEFAULT_FUR_COLOR = FurColors.GRAY;

    private static final double SIZE_COEFFICIENT = 1.5;
    private static final int DEFAULT_SIZE = (int) (DEFAULT_WEIGHT / SIZE_COEFFICIENT);
    /**
     * FurColors enum indicating the fur color of a bear.
     */
    private FurColors furColor;

    /**
     * private enum class to hold different fur colors
     */
    private enum FurColors {
        /**
         * White fur color
         */
        WHITE("WHITE"),
        /**
         * Gray fur color
         */
        GRAY("GRAY"),
        /**
         * Black fur color
         */
        BLACK("BLACK");

        /**
         * String representation of the fur color.
         */
        private final String color;

        /**
         * FurColor constructor.
         * can be initialized with a String representation of the fur color.
         * @param color String representation of the fur color.
         */
        FurColors(String color) {
            this.color = color;
        }
    }

    /**
     * Bear constructor.
     * setting default weight, fur color and diet!
     * passing name and location to super.
     * @see com.animals.AnimalRoar
     * @param name String representation of bear name.
     */
    public Bear(String name, int size, int horSpeed, int verSpeed, String col){
        super(name,DEFAULT_STARTING_LOCATION,size,horSpeed,verSpeed,col);
        setWeight(getSize() * 1.5);
        setFurColor(String.valueOf(FurColors.GRAY));
        setDiet(new Omnivore());
        loadImages(animalShortPathName());
        MessageUtility.logConstractor(getClass().getSimpleName(), getName());
    }

    /**
     * Bear constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of bear name.
     */
    public Bear(String name,int horSpeed, int verSpeed) {
        this(name, DEFAULT_SIZE, horSpeed,verSpeed, getDefaultColor());
    }

    /**
     * furColor  setter.
     * traverses the FurColors enum, if value of given param is equal it updates the furColor field.
     * leaving the field as is.
     *
     * @param furColor the color to set.
     * @return boolean value if the color was found and set or not.
     */
    public boolean setFurColor(String furColor) {
        boolean isSuccess = false;
        for (FurColors f : FurColors.values()) {
            if (f.name().equals(furColor)) {
                this.furColor = FurColors.valueOf(furColor);
                isSuccess = true;
                break;
            }
        }
        MessageUtility.logSetter(this.toString(), "setFurColor", furColor, isSuccess);
        return isSuccess;
    }

    /**
     * furColor getter.
     * @return furColor value of bear.
     */
    public FurColors getFurColor() {
        MessageUtility.logGetter(getName(), "getFurColor", furColor);
        return furColor;
    }

    public static Point getDefaultStartingLocation() {
        return DEFAULT_STARTING_LOCATION;
    }

    public static FurColors getDefaultFurColor(){
        return DEFAULT_FUR_COLOR;
    }
    /**
     * roar implementation of a bear.
     */
    @Override
    public void roar() {
        MessageUtility.logSound(getName(), "Stands on its hind legs, roars and scratches its belly");
    }

    @Override
    public String animalShortPathName() {
        return "bea";
    }

    @Override
    public String getAnimalName() {
        return "Bear";
    }

    /**
     * equals method of Bear object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bear bear = (Bear) o;

        return getFurColor() == bear.getFurColor();
    }

    /**
     * toString of bear.
     * @return String representation of Bear.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    public static double getSizeCoefficient() {
        return SIZE_COEFFICIENT;
    }
}
