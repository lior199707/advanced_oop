package com.food.plants;

/**
 * Lettuce class, a Plant descendant.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class Lettuce extends Plant {
	private static Lettuce instance = null;
	/**
	 * Lettuce constructor.
	 */
	private Lettuce() { }

	public static Lettuce getInstance() {
		if (instance == null){
			instance = new Lettuce();
		}
		return instance;
	}

	/**
	 * override abstract class Food animalShortPathName().
	 * @return String representation of the short path name for loading Lettuce image.
	 */
	@Override
	public String foodShortPathName() {
		return "Lettuce";
	}

}