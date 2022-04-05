package com.privateutil;

import com.animals.*;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


/**
 * PrivateUtils class holds utility methods used within the project.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class PrivateUtils {
    /**
     * Scanner object using System.in to request input from user.
     */
    static final Scanner sc = new Scanner(System.in);

    /**
     * userInputIntegerRange handles integer input between set range.
     * @param MIN_RANGE integer value representing the minimum range.
     * @param MAX_RANGE integer value representing the maximum range.
     * @return integer value of the user choice.
     */
    public static int userInputIntegerRange(int MIN_RANGE, int MAX_RANGE) {
        int userChoice = 0;

        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Enter integer value between " + MIN_RANGE + " and " + MAX_RANGE + ":");
                userChoice = sc.nextInt();
                // if userChoice not in range
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.println("Value out of range, please try again! ");
                } else {
                    return userChoice;
                }

            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.err.println(e + " expecting integer!");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        return userChoice;
    }

    /**
     * userInputDoubleRange handles double input between set range.
     * @param MIN_RANGE double value representing the minimum range.
     * @param MAX_RANGE double value representing the maximum range.
     * @return double value of the user choice.
     */
    public static double userInputDoubleRange(double MIN_RANGE, double MAX_RANGE){
        double userChoice = 0;

        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Enter double value between " + MIN_RANGE + " and " + MAX_RANGE + ":");
                userChoice = sc.nextDouble();
                // if userChoice not in range
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.println("Value out of range, please try again! ");
                } else {
                    return userChoice;
                }

            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.err.println(e + " expecting double!");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        return userChoice;
    }
    /**
     * userInputInteger handles integer input.
     * @param MIN_RANGE integer value representing the minimum allowed for input.
     * @return integer value of the user choice.
     */
    public static int userInputInteger(int MIN_RANGE) {
        int userChoice = 0;
        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Enter value: ");
                userChoice = sc.nextInt();
                if (userChoice < MIN_RANGE)
                    System.out.println("Value is out of range!");
                else
                    return userChoice;
            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.err.println(e + " expecting integer");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        return userChoice;
    }
    /**
     * userInputChoiceDouble handles double input.
     * @param MIN_RANGE double value representing the minimum allowed for input.
     * @return double value of the user choice.
     */
    public static double userInputChoiceDouble(double MIN_RANGE){
        double userChoice = 0;
        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Enter value: ");
                userChoice = sc.nextDouble();
                if (userChoice < MIN_RANGE)
                    System.out.println("Value is out of range!");
                else
                    return userChoice;
            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.err.println(e + " expecting double!");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        return userChoice;
    }

    /**
     * userInputChoiceBoolean prints user message, it asks the user a binary question
     * and returns the boolean result.
     * @param message String representation of a user made message.
     * @return boolean value indicating if the user wishes to proceed or not.
     */
    public static boolean userInputChoiceBoolean(String message){
        int userChoice;
        System.out.println(message);
        System.out.println("1. No");
        System.out.println("2. Yes");
        userChoice = userInputIntegerRange(1, 2);
        return userChoice == 2;
    }

    /**
     * pointInput handles integer input from user of two variables.
     * after successfully reading two integer values x,y it creates a new Point object,
     * which is sent to checkBoundaries to determine if given values are valid.
     * otherwise, trying to input two variables again.
     * @see com.mobility.Point checkBoundaries() method.
     * @return Point object with set x,y coordinates.
     */
    public static Point pointInput() {
        Point point = new Point();
        boolean isValidX = false;
        boolean isValidY = false;
        int x,y;

        // infinitely request for user input until successful.
        while(true) {
            // while x value is not set.
            while (!isValidX) {
                try {
                    System.out.println("Please enter the x value of the point: ");
                    x = sc.nextInt();
                    isValidX = point.setX(x);
                    if (!isValidX) {
                        System.out.println("X value is out of range!");
                    }
                } catch (InputMismatchException e) {
                    System.err.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                }
            }

            // while y value is not set.
            while (!isValidY) {
                try {
                    System.out.println("Please enter the y value of the point: ");
                    y = sc.nextInt();
                    isValidY = point.setY(y);
                    if (!isValidY) {
                        System.out.println("Y value is out of range!");
                    }
                } catch (InputMismatchException e) {
                    System.err.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                }
            }


            // if point is valid, return it.
            if (Point.checkBoundaries(point)) {
                return point;
            } else {
                System.err.println(point + " values are out of bound");

                isValidX = false;
                isValidY = false;
            }
        }
    }

    /**
     * initAnimalUniqueDetails suggests the user to input values to unique fields of
     * animal descendants (references). It uses userInputChoiceBoolean with a set message
     * for each animal, if the user proceeds with the action on animal descendant it updates
     * the field using setters. otherwise, it does not perform the set action and the values are
     * set by default through the relevant constructor of each animal.
     * @param animal Animal object instantiated in loadAnimal.
     * @param type String representation of the animal type.
     */
    public static void initAnimalUniqueDetails(Animal animal, String type) {
        // Ask for third object.
        String message;

        message = "Do you wish to set the animal's weight?";
        if (userInputChoiceBoolean(message)) {
            System.out.println("Please enter the weight, it must be greater than 0: ");
            double weight = userInputChoiceDouble(0);
            animal.setWeight(weight);
        }

        switch (Objects.requireNonNull(type)) {
            case "Lion" -> {
                //
            }

            case "Turtle" -> {
                message = "Do you wish to set the age of the turtle?";
                if (userInputChoiceBoolean(message)) {
                    int age = userInputIntegerRange(0, 500);
                    ((Turtle) animal).setAge(age);
                }
            }

            case "Giraffe" -> {
                message = "Do you wish to set the neck's length of the giraffe?";
                if (userInputChoiceBoolean(message)){
                    double neckLength = userInputDoubleRange(1.0, 2.5);
                    ((Giraffe) animal).setNeckLength(neckLength);
                }
            }
            case "Elephant" -> {
                message = "Do you wish to set the trunk's length of the elephant?";
                if (userInputChoiceBoolean(message)){
                    double trunkLength = userInputDoubleRange(0.5, 3.0);
                    ((Elephant) animal).setTrunkLength(trunkLength);
                }
            }
            case "Bear" -> {
                message = "Do you wish to set the fur color of the bear?";
                if (userInputChoiceBoolean(message)){
                    String[] colors = { "WHITE", "GREY", "BLACK" };
                    System.out.println("Please choose the color:");
                    System.out.println("1. White");
                    System.out.println("2. Grey");
                    System.out.println("3. Black");
                    int color = userInputIntegerRange(1, 3);
                    ((Bear) animal).setFurColor(colors[color - 1]);
                }
            }
        }
    }

    /**
     * loadAnimal is a reflection API method to instantiate an Animal object.
     * after instantiating an animal it calls initAnimalUniqueDetails.
     * @see com.privateutil.PrivateUtils initAnimalUniqueDetails.
     * @param type String representation of an animal type.
     * @param name String representation of an animal name.
     * @param location Point coordinates of an animal location.
     * @return Animal reference loaded at runtime.
     */
    public static Animal loadAnimal(String type, String name, Point location) {
        Animal animal = null;
        try {
            // loading the relevant class based on package name.
            ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
            Class<?> c = clsLoader.loadClass("com.animals." + type);
            // if the location parameter is given, instantiate the (name,point) constructor.
            if (location != null) {
                Constructor<?> con = c.getConstructor(String.class, Point.class);
                animal = (Animal) con.newInstance(name, location);
            } else {
                // otherwise, instantiate the (name) constructor.
                Constructor<?> con = c.getConstructor(String.class);
                animal = (Animal) con.newInstance(name);
            }
            initAnimalUniqueDetails(animal, type);
        } catch (NoSuchMethodException | ClassNotFoundException | NullPointerException |
                InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.err.println(e + " unable to load animal");
        }
        return animal;
    }

    /**
     * createAnimalArray method creates an animal array based on user input.
     * the user will select different types of animals, input their name and location - (not required).
     * the animal objects will be instantiated via loadAnimal method.
     * @param size integer value of the array size.
     * @return ArrayList of Animals, array containing Animal references.
     */
    public static ArrayList<Animal> createAnimalArray(int size) {
        ArrayList<Animal> animals = new ArrayList<>(size);
        String type = null;
        int userChoice;

        // Menu
        for (int i = 0; i < size; i++) {
            System.out.println("--Select animal #" + (i + 1) + " or exit program--");
            System.out.println("1. Lion");
            System.out.println("2. Bear");
            System.out.println("3. Giraffe");
            System.out.println("4. Turtle");
            System.out.println("5. Elephant");
            System.out.println("6. Exit Program");

            // requesting choice input from user.
            userChoice = userInputIntegerRange(1, 6);

            // setting type based on user's choice or exiting program.
            switch (userChoice) {
                case 1 -> type = "Lion";
                case 2 -> type = "Bear";
                case 3 -> type = "Giraffe";
                case 4 -> type = "Turtle";
                case 5 -> type = "Elephant";
                case 6 -> {
                    System.out.println("Goodbye!");
                    System.exit(1);
                }
            }

            // input animal name.
            System.out.println("Please enter the animal's name: ");
            String name = sc.next();

            String message = "Do you wish to enter the animal's spawn point?";

            if (userInputChoiceBoolean(message)){
                // otherwise, instantiating Point object based on user input.
                Point location = pointInput();
                animals.add(loadAnimal(type,name,location));
            } else {
                animals.add(loadAnimal(type, name, null));
            }

            System.out.println();
        }
        return animals;
    }

    /**
     * animalType is performing down-casting to determine what type of animal
     * is the object at runtime.
     * returns null in case no object is found.
     * @param animal the Object type to examine.
     * @return a type of animal object (lion,bear etc..)
     * @throws NullPointerException throws null in case no animal type is found.
     */
    public static Animal animalType(Object animal) throws NullPointerException{
        if (animal instanceof Lion)
            return (Lion) animal;
        if (animal instanceof Bear)
            return (Bear) animal;
        if (animal instanceof Giraffe)
            return (Giraffe) animal;
        if (animal instanceof Elephant)
            return (Elephant) animal;
        if (animal instanceof Turtle)
            return (Turtle) animal;

        throw new NullPointerException("Given object to animalType method is not an animal!");
    }
}
