package com.zoo;

import com.animals.Animal;
import com.food.IEdible;
import com.mobility.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

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
    
    public static Animal loadAnimalWithName(String type, String name){
        Animal animal = null;
        try {
            ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
            Class c = clsLoader.loadClass("com.animals." + type);
            Constructor<Animal> con = c.getConstructor(String.class);
            animal = con.newInstance(name);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            System.out.println(e + " unable to load animal");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return animal;
    }

    public static Animal loadAnimalWithNameAndPoint(String type, String name, Point location){
        Animal animal = null;
        try {
            ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
            Class c = clsLoader.loadClass("com.animals." + type);
            Constructor<Animal> con = c.getConstructor(String.class, Point.class);
            animal = con.newInstance(name, location);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            System.out.println(e + " unable to load animal");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return animal;
    }
    private static Animal[] createAnimalArray(int size) {
        Animal[] animals = new Animal[size];
        int userChoice;
        int pointChoice;
        for (int i = 0; i < size; i++) {
            System.out.println("Selecting animal number " + (i + 1) + " or exit program\n");
            System.out.println("1. Lion");
            System.out.println("2. Bear");
            System.out.println("3. Giraffe");
            System.out.println("4. Turtle");
            System.out.println("5. Elephant");
            System.out.println("6. Exit Program");
            System.out.print("Choose an option: ");
            userChoice = sc.nextInt();

            // checking if userChoice is in boundaries.
            while (userChoice < 0 || userChoice > 6) {
                System.out.println("Invalid option, please try again");
                userChoice = sc.nextInt();
            }

            // exiting before initiation of array.
            if (userChoice == 6) {
                System.out.println("Goodbye");
                System.exit(1);
            }

            String type = null;
            switch (userChoice) {
                case 1 -> type = "Lion";
                case 2 -> type = "Bear";
                case 3 -> type = "Giraffe";
                case 4 -> type = "Turtle";
                case 5 -> type = "Elephant";
            }


            // input animal name.
            System.out.print("Please enter the animal's name: ");
            String name = sc.next();

            System.out.println("Do you wish to enter the animal's spawn point?");
            System.out.println("1. No");
            System.out.println("2. Yes");
            pointChoice = sc.nextInt();

            while (pointChoice < 0 || pointChoice > 2) {
                System.out.println("The number you chose isn't valid, please try again");
                pointChoice = sc.nextInt();
            }

            if (pointChoice == 1) {
                animals[i] = loadAnimalWithName(type,name);
            } else {
                Point location = Point.pointInput();
                animals[i] = loadAnimalWithNameAndPoint(type,name,location);
            }

        }
        return animals;
    }

    public static void main(String[] args) {
        final int MIN_NUM_OF_ANIMALS = 1;
        Random rand = new Random();
        int size = 0;
        while(size < MIN_NUM_OF_ANIMALS){
            System.out.println("Please enter the number of animals (MINIMUM OF 3): ");
            size = sc.nextInt();
        }

        Animal[] animals = createAnimalArray(size);

        for (Animal animal : animals){
            System.out.println(animal.getLocation());
        }

        for (Animal animal : animals){
            Point nextLocation = Point.pointInput();
            if (ZooActions.move(animal,nextLocation)){
                animal.getWeight();
            }
        }

        int raffle = size/2;
        for (int i = 0; i < raffle; i++){
            int one = rand.nextInt(size);
            int two = rand.nextInt(size);
            ZooActions.eat(animals[one], animals[two]);
        }
        sc.close();
    }
}
