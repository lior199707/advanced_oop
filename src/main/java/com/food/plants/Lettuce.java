package com.food.plants;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {

	/**
	 * Lettuce constructor.
	 */
	public Lettuce() { }

	/**
	 * override abstract class Food animalShortPathName().
	 * @return String representation of the short path name for loading Lettuce image.
	 */
	@Override
	public String foodShortPathName() {
		return "Lettuce";
	}

}