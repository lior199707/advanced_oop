package com.zoo;

import com.animals.Animal;
import com.food.IEdible;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

import static com.privateutil.PrivateUtils.pointInput;
import static com.privateutil.PrivateUtils.userChoiceInput;

public class ZooActions {
    private static final Scanner sc = new Scanner(System.in);

    public static boolean eat(Object animal, IEdible food) {
        Animal creature = (Animal) animal;
        return creature.eat(food);
    }

    public static boolean move(Object animal, Point point) {
        Animal creature = (Animal) animal;
        double distanceTraveled = creature.move(point);
        return distanceTraveled != 0;
    }
//

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

    private static Animal[] createAnimalArray(int size) {
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
        return animals;
    }

    public static void main(String[] args) {
        final int MAX_NUM_OF_ANIMALS = 1000;
        final int MIN_NUM_OF_ANIMALS = 3;
        Random rand = new Random();

        System.out.print("Please enter the number of animals (MINIMUM OF 3): ");
        int size = userChoiceInput(MIN_NUM_OF_ANIMALS, MAX_NUM_OF_ANIMALS);

        Animal[] animals = createAnimalArray(size);

        for (Animal animal : animals){
            System.out.println(animal.getName() + " is planning to move! enter coordinates:");
            Point nextLocation = pointInput();
            if (ZooActions.move(animal,nextLocation)){
                System.out.println(animal.getName() + " moved! it's updated weight: " + animal.getWeight());
            } else {
                System.out.println(animal.getName() + " did not move.\n");
            }
            System.out.println();
        }

        int raffle = size/2;
        System.out.println("Animal eating contest! Raffle size: " + raffle);
        for (int i = 0; i < raffle; i++){
            int one = rand.nextInt(size);
            int two = rand.nextInt(size);
            if (ZooActions.eat(animals[one], animals[two])){
                System.out.println(animals[one].getName() + " ate! it's updated weight: " + animals[one].getWeight());
            } else {
                System.out.println(animals[one].getName() + " did not eat.\n");
            }
            System.out.println();
        }
        sc.close();
    }
}
