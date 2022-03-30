package com.privateutil;

import com.animals.Animal;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PrivateUtils {
    public static int userChoiceInput(int MIN_RANGE, int MAX_RANGE) {
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;

        while (true) {
            try {
                userChoice = sc.nextInt();
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.print("Choice out of range, please try again: ");
                } else {
                    return userChoice;
                }
            } catch (InputMismatchException e) {
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

    public static Point pointInput() {
        Scanner sc = new Scanner(System.in);
        boolean isValidX = false;
        boolean isValidY = false;
        int x = 0, y = 0;

        while(true) {
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

            Point point = new Point(x,y);
            if (Point.checkBoundaries(point))
                return point;

            System.out.println(point + " values are out of bound");

            isValidX = false;
            isValidY = false;
        }
    }

    public static Animal loadAnimal(String type, String name, Point location) {
        Animal animal = null;
        try {
            ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
            Class<?> c = clsLoader.loadClass("com.animals." + type);
            if (location != null) {
                Constructor<?> con = c.getConstructor(String.class, Point.class);
                animal = (Animal) con.newInstance(name, location);
            } else {
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

    public static Animal[] createAnimalArray(int size) {
        Scanner sc = new Scanner(System.in);
        Animal[] animals = new Animal[size];
        String type = null;
        int userChoice;
        int pointChoice;

        for (int i = 0; i < size; i++) {
            System.out.println("--Select animal #" + (i + 1) + " or exit program--");
            System.out.println("1. Lion");
            System.out.println("2. Bear");
            System.out.println("3. Giraffe");
            System.out.println("4. Turtle");
            System.out.println("5. Elephant");
            System.out.println("6. Exit Program");
            System.out.print("Choose an option: ");


            // checking if userChoice is in boundaries.
            userChoice = userChoiceInput(1, 6);

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
            pointChoice = userChoiceInput(1,2);


            if (pointChoice == 1) {
                animals[i] = loadAnimal(type,name,null);
            } else {
                Point location = pointInput();
                animals[i] = loadAnimal(type,name,location);
            }
            System.out.println();
        }
        sc.close();
        return animals;
    }

}
