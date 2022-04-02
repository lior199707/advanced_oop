package com.zoo;

import com.animals.*;
import com.food.IEdible;
import com.mobility.Point;
import com.plants.Cabbage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author baroh
 *
 */
public class Program {

	private static List<Animal> animals;
	private static Bear bear;
	private static Elephant elephant;
	private static List<IEdible> food;
	private static Giraffe giraffe;
	private static Lion lion;
	private static Turtle turtle;

	private static void changeValues() {
		System.out.println("********************************************");
		System.out.println("\t\tChange Values");
		System.out.println("********************************************");
		Random rand = new Random();
		for (Animal animal : animals) {
//			animal.setWeight(-857);
			System.out.println(animal + " moving to (1,600)");
//			animal.move(new Point(1, 600));
			animal.move(new Point(-1, 50));
		}

		bear.setName("GreenBaloo");
		((Bear) animals.get(1)).setFurColor("GREEN");
		giraffe.setNeckLength(4356);
		giraffe.setNeckLength(1.58);
		elephant.setTrunkLength(0.75);
		elephant.setTrunkLength(5.76);
		turtle.setAge(-8);
		turtle.setAge(456);

	}

	private static void feed() {
		System.out.println("********************************************");
		System.out.println("\t\tFeed");
		System.out.println("********************************************");
		for (Animal animal : animals) {
			for (IEdible f : food) {
				animal.eat(f);
			}
		}
	}

	private static void init() {
		System.out.println("********************************************");
		System.out.println("\t\tInitilize");
		System.out.println("********************************************");

		animals = new ArrayList<>();
		lion = new Lion("");
		animals.add(lion);
		bear = new Bear("Baloo");
		animals.add(bear);
		elephant = new Elephant("Dumbo");
		animals.add(elephant);
		giraffe = new Giraffe("Melman");
		animals.add(giraffe);
		turtle = new Turtle("Rafael");
		animals.add(turtle);
		animals.add(new Bear("Ted"));

		food = new LinkedList<>();
		food.add(new Cabbage());
		food.add(animals.get(0)); // Lion
		food.add(animals.get(3)); // Giraffe

	}

	public static void main(String[] args) {

		init();
		testSound();
		feed();
		move();
		changeValues();

	}

	private static void move() {
		System.out.println("********************************************");
		System.out.println("\t\tMove");
		System.out.println("********************************************");
		int x = 10, y = 10;
		for (Animal animal : animals) {
			animal.move(new Point(x, y));
			x += 5;
			y += 10;
		}

		animals.get(0).move(new Point(10, 500));

	}

	private static void testSound() {
		System.out.println("********************************************");
		System.out.println("\t\tTest Sounds");
		System.out.println("********************************************");
		for (Animal animal : animals) {
			animal.makeSound();
		}

	}

}
