package com.zoo;

import com.animals.*;
import com.food.IEdible;
import com.mobility.Point;

import java.util.Scanner;

public class ZooActions
{

    private static Animal findAnimalInstance(Object animal) {
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
        return null;
    }



    public static boolean eat(Object animal, IEdible food){
        Animal creature = findAnimalInstance(animal);
        return creature.eat(food);
    }

    public static boolean move(Object animal, Point point){
        Animal creature = findAnimalInstance(animal);
        double distanceTraveled = creature.move(point);
        return distanceTraveled != 0;
    }

    private void createAnimalArray(Animal[] animals, int size){
        int userChoice;
        int pointChoise;
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < size; i++) {
            System.out.println("Please choose the " + (i+1) + "animal");
            System.out.println("1. Lion");
            System.out.println("2. Bear");
            System.out.println("3. Giraffe");
            System.out.println("4. Turtle");
            System.out.println("5. Elephant");
            System.out.println("6. Exit Program");
            userChoice = scanner.nextInt();
            while (userChoice < 0 || userChoice > 6){
                System.out.println("The number you chose isn't valid, please try again");
                userChoice = scanner.nextInt();
            }
            if(userChoice == 6){
                System.out.println("Goodbye");
                System.exit(1);
            }
            System.out.println("Please enter the animal's name:");
            String name = scanner.nextLine();
            System.out.println("Do you wish to enter the animal's spawn point?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            pointChoise = scanner.nextInt();
            while (pointChoise < 0 || pointChoise > 2) {
                System.out.println("The number you chose isn't valid, please try again");
                pointChoise = scanner.nextInt();
            }
            switch (pointChoise){
                case 1:
                    System.out.println("Please enter the x value:");
                    int x = scanner.nextInt();
                    System.out.println("Please enter the y value:");
                    int y = scanner.nextInt();
                    Point location = new Point(x,y);
                    switch (userChoice){
                        case 1 ->
                                // create Lion
                                animals[i] = new Lion(name,location);
                        case 2 ->
                                // create Bear
                                animals[i] = new Bear(name,location);
                        case 3 ->
                                // create Giraffe
                                animals[i] = new Giraffe(name,location);
                        case 4 ->
                                // create Turtle
                                animals[i] = new Turtle(name,location);
                        case 5 ->
                                // create Elephant
                                animals[i] = new Elephant(name,location);
                    }
                    break;
                case 2:
                    switch (userChoice) {
                        case 1 ->
                                // create Lion
                                animals[i] = new Lion(name);
                        case 2 ->
                                // create Bear
                                animals[i] = new Bear(name);
                        case 3 ->
                                // create Giraffe
                                animals[i] = new Giraffe(name);
                        case 4 ->
                                // create Turtle
                                animals[i] = new Turtle(name);
                        case 5 ->
                                // create Elephant
                                animals[i] = new Elephant(name);
                    }
            }
        }
    }


    public static void main(String[] args) {
        final int MIN_NUM_OF_ANIMALS = 3;
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        while(size < MIN_NUM_OF_ANIMALS){
            System.out.println("please enter the the number of animals, the minimum number of animals is 3");
            size = scanner.nextInt();
        }
        Animal[] animalArr = new Animal[size];


    }

}
