package com.privateutil;

import com.animals.*;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * PrivateUtils class holds utility methods used within the project.
 */
public class PrivateUtils {
    /**
     * Scanner object using System.in to request input from user.
     */
    static final Scanner sc = new Scanner(System.in);
    /**
     * userInput handles integer input.
     * @param MIN_RANGE integer value representing the minimum range.
     * @param MAX_RANGE integer value representing the maximum range.
     * @return integer value of the user choice.
     */
    public static int userChoiceInputRange(int MIN_RANGE, int MAX_RANGE) {
        int userChoice = 0;

        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Choose an option: ");
                userChoice = sc.nextInt();
                // if userChoice not in range
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.println("Choice out of range, please try again! ");
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
     * userChoiceInput handles integer input.
     * @param MIN_VALUE integer value representing the minimum allowed for input.
     * @return integer value of the user choice.
     */
    public static int userChoiceInput(int MIN_VALUE) {
        int userChoice = 0;
        // infinitely request for user input until successful.
        while (true) {
            try {
                System.out.println("Enter value: ");
               userChoice = sc.nextInt();
                if (userChoice < MIN_VALUE)
                    System.out.println("Value is out of range!");
                else
                    return userChoice;
            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.err.println(e + "Invalid input");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        return userChoice;
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
     * loadAnimal is a reflection API method to instantiate an Animal object.
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
     * @return Animal[], array containing Animal references.
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
            userChoice = userChoiceInputRange(1, 6);

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

            System.out.println("Do you wish to enter the animal's spawn point?");
            System.out.println("1. No");
            System.out.println("2. Yes");

            // requesting choice input from user.
            userChoice = userChoiceInputRange(1,2);

            // if user does not wish to initiate spawn point.
            if (userChoice == 1) {
                animals.add(loadAnimal(type, name, null));
            } else {
                // otherwise, instantiating Point object based on user input.
                Point location = pointInput();
                animals.add(loadAnimal(type,name,location));
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
