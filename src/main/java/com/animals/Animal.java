package com.animals;

import com.diet.IDiet;
import com.food.EFoodType;
import com.food.Food;
import com.food.IEdible;
import com.graphics.IAnimalBehavior;
import com.graphics.IDrawable;
import com.graphics.ZooPanel;
import com.mobility.Mobile;
import com.mobility.Point;
import com.privateutil.PrivateGraphicUtils;
import com.utilities.MessageUtility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Animal class is an abstract representing a mobile animal.
 * Every animal type is also edible.
 * @see com.mobility.Mobile
 * @see com.food.IEdible
 */
public abstract class Animal extends Mobile implements IEdible, IDrawable, IAnimalBehavior {
    /**
     * String value of the animal name.
     */
    private String name;
    /**
     * double value of the animal weight.
     */
    private double weight;
    /**
     * IDiet reference indicating the diet type of animal.
     */
    private IDiet diet;

    private Thread thread;
    private static final String DEFAULT_COLOR = "NATURAL";
    private static final int EAT_DISTANCE = 10;
    private boolean coordChanged;
    private int size;
    private int horSpeed;
    private int verSpeed;
    private String col;

    private final int x_dir = 1;
    private final int y_dir = 1;
    private int eatCount;

    private ZooPanel pan;
    private BufferedImage img1, img2;


    public static String getDefaultColor(){
        return DEFAULT_COLOR;
    }
    public static int getEatDistance() {
        return EAT_DISTANCE;
    }

    public Animal(String name, Point location, int size, int horSpeed, int verSpeed, String col){
        super(location);
        setName(name);
        if (location != null) {
            getLocation();
        }
        setSize(size);
        setHorSpeed(horSpeed);
        setVerSpeed(verSpeed);
        setColor(col);
        loadImages(getAnimalName());

        this.eatCount = 0;
        this.coordChanged = false;
        MessageUtility.logConstractor("Animal", getName());
    }

    /**
     * Animal constructor.
     * @param name - String value of animal name.
     * @param location - Point object of the current location.
     */
    public Animal(String name, Point location, int size, int horSpeed, int verSpeed) {
        this(name,location,size, horSpeed,verSpeed,DEFAULT_COLOR);
    }

    public abstract String animalShortPathName();

    /**
     * abstract method, animals will have to implement their own sounds.
     */
    public abstract void makeSound();

    /**
     * eat activates the appropriate eat method based on the current animal's diet.
     * if the current animal is able to eat the given animal it will activate makeSound
     * of the current animal and set the new weight.
     * @param food an edible type of animal to eat.
     * @return boolean value if this animal was able to eat the given animal or not.
     */
    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        double updatedWeight = this.diet.eat(this, food);
        if (updatedWeight != 0) {
            makeSound();
            setWeight(this.weight + updatedWeight);
            isSuccess = true;
        }
        MessageUtility.logBooleanFunction(this.name, "eat", food, isSuccess);
        return isSuccess;
    }

    /**
     * move uses Mobile.move() method to set the new location and calculate the distance between
     * the current and nextLocation.
     * It evaluates the new weight with the distance based on this formula:
     * current weight - (distance * current weight * CONST = 0.00025)
     *
     * @see com.mobility.Mobile move() for reference.
     * @param nextLocation - Point object to change location to.
     * @return distance traveled between two points.
     */
    @Override
    public double move(Point nextLocation) {
        final double CONST = 0.00025;
        double distance = super.move(nextLocation);
        if (distance > 0) {
            this.setWeight(this.weight - (distance * this.weight * CONST));
            this.coordChanged = true;
        }
        return distance;
    }

    /**
     * name setter.
     * this method checks if given name param is alphabetic, if so it updates the name field.
     * otherwise, it removes all non-alphabetic characters.
     * if the String is not empty assigns the remaining characters to name, otherwise sets "NaN",
     * indicating the animal has no name.
     *
     * @param name the String object to set animal name.
     * @return boolean value if name was set or not (NaN).
     */
    public boolean setName(String name) {
        boolean isSuccess = name.chars().allMatch(Character::isAlphabetic);
        if (isSuccess){
            this.name = name;
        } else {
            System.out.println("-- Removing non-alphabetic characters from name --");
            this.name = name.replaceAll("[^A-Za-z]", "");
            System.out.println("New name: " + this.name);
            isSuccess = !Objects.equals(this.name, "");
            if (!isSuccess){
                this.name = "NaN";
            }
        }
        MessageUtility.logSetter(this.name, "setName", name, isSuccess);
        return isSuccess;
    }

    /**
     * name getter.
     * @return String value representing animal name.
     */
    public String getName() {
        MessageUtility.logGetter(name, "getName", name);
        return name;
    }

    /**
     * weight getter.
     * @return double value representing animal weight.
     */
    public double getWeight() {
        MessageUtility.logGetter(this.name, "getWeight", weight);
        return weight;
    }

    /**
     * weight setter
     * checks if weight >= 0, if so it updates the weight field.
     * otherwise, set the weight to 0.  weight cannot be negative!
     *
     * @param weight double value representing weight.
     * @return boolean value if weight was set or not.
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = weight > 0;
        if (isSuccess) {
            this.weight = weight;
        }
        MessageUtility.logSetter(this.name, "setWeight", weight, isSuccess);
        return isSuccess;
    }

    /**
     * diet setter
     * @param diet the diet object to set.
     * @return always true.
     */
    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        MessageUtility.logSetter(this.name, "setDiet", diet, true);
        return true;
    }

    /**
     * diet getter
     * @return IDiet reference.
     */
    public IDiet getDiet() {
        MessageUtility.logGetter(this.name, "getDiet", this.diet);
        return diet;
    }

    public int getX_dir() {
        return x_dir;
    }

    public int getY_dir() {
        return y_dir;
    }

    public int getVerSpeed() {
        return verSpeed;
    }

    public int getHorSpeed() {
        return horSpeed;
    }

    public BufferedImage getImg2() {
        return img2;
    }

    public BufferedImage getImg1() {
        return img1;
    }

    public ZooPanel getPan(){
        return pan;
    }

    public boolean setSize(int size){
        boolean isSuccess = false;
        int MIN_RANGE = 50,MAX_RANGE = 300;
        if (MIN_RANGE <= size && size <= MAX_RANGE){
            this.size = size;
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean setVerSpeed(int verSpeed){
        boolean isSuccess = false;
        int MIN_RANGE = 1,MAX_RANGE = 10;
        if (MIN_RANGE <= verSpeed && verSpeed <= MAX_RANGE){
            this.verSpeed = verSpeed;
            isSuccess = true;
        } else this.verSpeed = 1;
        return isSuccess;
    }

    public boolean setHorSpeed(int horSpeed){
        boolean isSuccess = false;
        int MIN_RANGE = 1,MAX_RANGE = 10;
        if (MIN_RANGE <= horSpeed && horSpeed <= MAX_RANGE){
            this.horSpeed = horSpeed;
            isSuccess = true;
        } else this.horSpeed = 1;
        return isSuccess;
    }


    /**
     * location getter.
     * @see com.mobility.Mobile getLocation() for reference.
     * @return current location of the animal, based on Mobile.getLocation.
     */
    @Override
    public Point getLocation() {
        MessageUtility.logGetter(this.name, "getLocation", super.getLocation());
        return super.getLocation();
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.name, "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    @Override
    public void drawObject(Graphics g) {
        if (x_dir == 1) { // goes to the right side
//            ImageIcon image = new ImageIcon(img1);
//            Image toSizeImage = image.getImage();
//            Image modifiedImage = toSizeImage.getScaledInstance((int) (size * 8), size, Image.SCALE_SMOOTH);
//            g.drawImage(img1, (getLocation().getX() - size / 2) + 35, (getLocation().getY() - size / 10) + 18,
//                    (int) (size * 1.2), size, pan);
            g.drawImage(img1, (getLocation().getX() - size / 2) + (35 * (50 / size)), (getLocation().getY() - size / 10) + (18 * (50 / size)),
                    (int) (size * 1.2), size, pan);
        }
        else // goes to the left side
            g.drawImage(img2, getLocation().getX(), getLocation().getY() - size / 10, size / 2, size, pan);
    }

    @Override
    public boolean getChanges (){
        return this.coordChanged;
    }

    @Override
    public void setChanges (boolean state){
        this.coordChanged = state;
    }


    @Override
    public void loadImages(String shortPathName) {
        String path;
        if (getX_dir() == 1) {
            path = PrivateGraphicUtils.findAnimalImagePath(this.getAnimalName(),col, getX_dir());
            try {
                img1 = ImageIO.read(new File(path));
            } catch (IOException e) {
                JOptionPane.showOptionDialog(pan, "Img1 failed to load", "ERROR",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                        null, null, null);
            }
        } else {
            path = PrivateGraphicUtils.findAnimalImagePath(this.getAnimalName(),col, getY_dir());
            try {
                img2 = ImageIO.read(new File(path));
            } catch (IOException e) {
                JOptionPane.showOptionDialog(pan, "Img2 failed to load", "ERROR",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                        null, null, null);
            }
        }
    }

    public boolean setColor(String color){
        boolean isSuccess = true;
        String upperCaseColor  = color.toUpperCase();
        switch (upperCaseColor) {
            case "RED" -> this.col = "RED";
            case "BLUE" -> this.col = "BLUE";
            case "NATURAL" -> this.col = "NATURAL";
            default -> {
                this.col = "NATURAL";
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public void checkEatFood(Food food) {
        if (food != null && this.calcDistance(food.getLocation()) <= Animal.getEatDistance())
            if (this.eat(food)){
                pan.setPanelFood(null);
                pan.updateEatCount(this);
            }
    }


    public void setPan(ZooPanel pan) {
        this.pan = pan;
    }

    @Override
    public String getColor() {
        return this.col;
    }

    @Override
    public void eatInc() {
        this.eatCount++;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getEatCount() {
        return eatCount;
    }
    /**
     * equals method of animal object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Animal animal = (Animal) o;

        if (Double.compare(animal.getWeight(), getWeight()) != 0) return false;
        if (getName() != null ? !getName().equals(animal.getName()) : animal.getName() != null) return false;
        return getDiet() != null ? getDiet().equals(animal.getDiet()) : animal.getDiet() == null;
    }

    /**
     * @return String representation of the animal object.
     */
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "]: " + this.name;
    }
}
