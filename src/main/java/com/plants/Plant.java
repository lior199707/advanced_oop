package com.plants;

import com.food.EFoodType;
import com.food.IEdible;
import com.graphics.IDrawable;
import com.graphics.ZooPanel;
import com.mobility.Ilocatable;
import com.mobility.Point;
import com.privateutil.PrivateGraphicUtils;
import com.utilities.MessageUtility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author baroh
 *
 */
public abstract class Plant implements IEdible, Ilocatable, IDrawable {
	/**
	 * 
	 */
	private double height;
	/**
	 * 
	 */
	private Point location;
	/**
	 * 
	 */
	private double weight;

	private ZooPanel pan;

	private BufferedImage img;
	/**
	 * 
	 */
	public Plant() {
		Random rand = new Random();
		int x = rand.nextInt(30);
		int y = rand.nextInt(12);
		this.location = new Point(x, y);
		this.height = rand.nextInt(30);
		this.weight = rand.nextInt(12);
		loadImages(foodShortPathName());
		MessageUtility.logConstractor("Plant", "Plant");
	}

	public abstract String foodShortPathName();
	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return double value representing plant height.
	 */
	public double getHeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getHeight", this.height);
		return this.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#getLocation()
	 */
	@Override
	public Point getLocation() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getLocation", this.location);
		return this.location;
	}

	/**
	 * @return double value representing plant weight.
	 */
	public double getWeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getWeight", this.weight);
		return weight;
	}

	/**
	 * @param height double value representing plant height.
	 * @return boolean value indicating if set was successful or not.
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setHeight", height, isSuccess);
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#setLocation(mobility.Point)
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.checkBoundaries(newLocation);
		if (isSuccess) {
			this.location = newLocation;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setLocation", newLocation, isSuccess);
		return isSuccess;
	}

	/**
	 * @param weight double value representing plant weight.
	 * @return boolean value indicating if the set was succcesful or not.
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setWeight", weight, isSuccess);

		return isSuccess;
	}

	@Override
	public void drawObject(Graphics g) {
		// need to draw the object at the center of the screen.
		int centerX = pan.getWidth() / 2;
		int centerY = pan.getHeight() / 2;
		g.drawImage(img, centerX, centerY, null);
	}

	@Override
	public void loadImages(String shortPathName) {
		String path = PrivateGraphicUtils.findFoodImagePath(shortPathName);
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			JOptionPane.showOptionDialog(pan, "Food image failed to load", "ERROR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, null, null);
		}
	}

	public BufferedImage getImg() {
		return img;
	}

	@Override
	public String getColor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

}
