package com.zoo;

import com.animals.Animal;
import com.food.IEdible;
import com.mobility.Point;

import java.util.Random;

import static com.privateutil.PrivateUtils.*;

public class ZooActions {
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
    }
}
