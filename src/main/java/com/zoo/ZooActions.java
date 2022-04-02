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
     * @param animal  the animal that is eating.
     * @param food  the edible object to eat (plant / animal).
     * @return true if the animal ate the food successfully, false otherwise.
     */
    public static boolean eat(Object animal, IEdible food) {
        try {
            Animal creature = animalType(animal);
            return creature.eat(food);
        } catch (NullPointerException e) {
            System.err.println(e + animal.toString() + " is faulty!");
        }
        return false;
    }

    /**
     * @param animal the animal that is moving.
     * @param point  the location the animal is moving to.
     * @return true if the animal has moved, false otherwise
     */
    public static boolean move(Object animal, Point point) {
        try {
            Animal creature = animalType(animal);
            double distanceTraveled = creature.move(point);
            return distanceTraveled != 0;
        } catch (NullPointerException e) {
            System.err.println(e + animal.toString() + " is faulty!");
        }
        return false;
    }

    /**
     * main method!
     * initializing animals arraylist with a minimum amount of animals.
     * for each animal it makes an attempt to use the move method.
     *
     * it also raffles references of two animals at a time.
     * predator makes an attempt to eat its prey, if possible.
     *
     * @see com.privateutil.PrivateUtils methods for reference!
     * @see com.animals.Animal move() method for reference!
     * @param args set of arguments from the command line.
     */
    public static void main(String[] args) {
        final int MIN_NUM_OF_ANIMALS = 3;
        Random rand = new Random();

        System.out.println("Please enter the number of animals (MINIMUM OF 3)! ");
        int size = userChoiceInput(MIN_NUM_OF_ANIMALS);

        ArrayList<Animal> animals = createAnimalArray(size);

        for (Animal animal : animals) { // for every animal in the array
            System.out.println(animal + animal.getLocation().toString() + " is planning to move! enter coordinates:");
            Point nextLocation = pointInput(); // get the next location of the animal from the user
            if (ZooActions.move(animal, nextLocation)) { // try to move the animal to this location
                System.out.println(animal + " moved! it's updated weight: " + animal.getWeight());
            } else {
                System.out.println(animal + " did not move.\n");
            }
            System.out.println();
        }

        int raffle = size / 2;
        System.out.println("Animal eating contest! Raffle size: " + raffle);
        for (int i = 0; i < raffle; i++) { // for every 2 random animals: animal1 and animal2
            int predator = rand.nextInt(animals.size());
            int prey = rand.nextInt(animals.size());


            // ensure no cannibalism - cannot eat itself.
            if (predator == prey){
                if (prey == animals.size() - 1)
                    prey--;
                else
                    prey++;
                // ensure the prey is not the same element in array list.
            }

            System.out.println(animals.get(predator) + " will attempt to eat " + animals.get(prey) + " !");

            if (ZooActions.eat(animals.get(predator), animals.get(prey))) { // anima1 tries to eat animal2
                System.out.println(animals.get(predator) + " ate " + animals.get(prey) + "! it's updated weight: "
                        + animals.get(predator).getWeight());
                animals.remove(animals.get(prey));
            } else {
                System.out.println(animals.get(predator) + " could not eat" + animals.get(prey));
            }
            System.out.println(animals.size());
            System.out.println();
        }
    }
}
