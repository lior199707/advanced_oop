package com.zoo;

import com.animals.Animal;
import com.food.IEdible;
import com.mobility.Point;

import java.util.ArrayList;
import java.util.Random;

import static com.privateutil.PrivateUtils.*;

/**
 * class ZooActions, runner class, contains the main method.
 */
public class ZooActions {
    /**
     * @param animal , the animal that is eating.
     * @param food, the edible object to eat (plant / animal).
     * @return true if the animal ate the food successfully, false otherwise.
     */
    public static boolean eat(Object animal, IEdible food) {
        try {
            Animal creature = animalType(animal);
            return creature.eat(food);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param animal , the animal that is moving.
     * @param point , the location the animal is moving to.
     * @return true if the animal has moved, false otherwise
     */
    public static boolean move(Object animal, Point point) {
        try {
            Animal creature = animalType(animal);
            double distanceTraveled = creature.move(point);
            return distanceTraveled != 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
//

    public static void main(String[] args) {
        final int MAX_NUM_OF_ANIMALS = 1000;
        final int MIN_NUM_OF_ANIMALS = 3;
        Random rand = new Random();

        System.out.print("Please enter the number of animals (MINIMUM OF 3): ");
        int size = userChoiceInput(MIN_NUM_OF_ANIMALS, MAX_NUM_OF_ANIMALS); // gets the size from the user

        Animal[] animals = createAnimalArray(size); // creates an animals array of given size

        for (Animal animal : animals){ // for every animal in the array
            System.out.println(animal.getName() + " is planning to move! enter coordinates:");
            Point nextLocation = pointInput(); // get the next location of the animal from the user
            if (ZooActions.move(animal,nextLocation)){ //try to move the animal to this location
                System.out.println(animal.getName() + " moved! it's updated weight: " + animal.getWeight());
            } else {
                System.out.println(animal.getName() + " did not move.\n");
            }
            System.out.println();
        }

        int raffle = size/2;
        System.out.println("Animal eating contest! Raffle size: " + raffle);
        for (int i = 0; i < raffle; i++){ // for every 2 random animals: animal1 and animal2
            int one = rand.nextInt(size);
            int two = rand.nextInt(size);
            if (ZooActions.eat(animals[one], animals[two])){ //anima1 tries to eat animal2
                System.out.println(animals[one].getName() + " ate! it's updated weight: " + animals[one].getWeight());
            } else {
                System.out.println(animals[one].getName() + " did not eat.\n");
            }
            System.out.println();
        }
    }
}
