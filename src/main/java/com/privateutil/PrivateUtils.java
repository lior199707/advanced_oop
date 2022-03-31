package com.privateutil;

import com.animals.Animal;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * PrivateUtils class holds utility methods used within the project.
 */
public class PrivateUtils {
    /**
     * userInput handles integer input.
     * @param MIN_RANGE integer value representing the minimum range.
     * @param MAX_RANGE integer value representing the maximum range.
     * @return integer value of the user choice.
     */
    public static int userChoiceInput(int MIN_RANGE, int MAX_RANGE) {
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;

        // infinitely request for user input until successful.
        while (true) {
            try {
                userChoice = sc.nextInt();
                // if userChoice not in range
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.print("Choice out of range, please try again: ");
                } else {
                    return userChoice;
                }
            } catch (InputMismatchException e) {
                // handles wrong values given to scanner.
                System.out.println(e + " expecting integer!");
                System.out.print("Invalid choice, please try again: ");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        sc.close();
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
        Scanner sc = new Scanner(System.in);
        boolean isValidX = false;
        boolean isValidY = false;
        int x = 0, y = 0;

        // infinitely request for user input until successful.
        while(true) {
            // while x value is not set.
            while (!isValidX) {
                try {
                    System.out.print("Please enter the x value of the point: ");
                    isValidX = true;
                    x = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                    isValidX = false;
                }
            }

            // while y value is not set.
            while (!isValidY) {
                try {
                    System.out.print("Please enter the y value of the point: ");
                    isValidY = true;
                    y = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                    isValidY = false;
                }
            }

            sc.close();

            // creating new point object.
            Point point = new Point(x,y);
            // if point is valid, return it.
            if (Point.checkBoundaries(point))
                return point;

            System.out.println(point + " values are out of bound");

            isValidX = false;
            isValidY = false;
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
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            System.out.println(e + " unable to load animal");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
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
    public static Animal[] createAnimalArray(int size) {
        Scanner sc = new Scanner(System.in);
        Animal[] animals = new Animal[size];
        String type = null;
        int userChoice;
        int pointChoice;

        // Menu
        for (int i = 0; i < size; i++) {
            System.out.println("--Select animal #" + (i + 1) + " or exit program--");
            System.out.println("1. Lion");
            System.out.println("2. Bear");
            System.out.println("3. Giraffe");
            System.out.println("4. Turtle");
            System.out.println("5. Elephant");
            System.out.println("6. Exit Program");
            System.out.print("Choose an option: ");

            // requesting choice input from user.
            userChoice = userChoiceInput(1, 6);

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
            System.out.print("Please enter the animal's name: ");
            String name = sc.next();

            System.out.println("Do you wish to enter the animal's spawn point?");
            System.out.println("1. No");
            System.out.println("2. Yes");
            System.out.print("Choose option: ");

            // requesting choice input from user.
            pointChoice = userChoiceInput(1,2);


            // if user does not wish to initiate spawn point.
            if (pointChoice == 1) {
                animals[i] = loadAnimal(type,name,null);
            } else {
                // otherwise, instantiating Point object based on use input.
                Point location = pointInput();
                animals[i] = loadAnimal(type,name,location);
            }
            System.out.println();
        }
        sc.close();
        return animals;
    }
}
