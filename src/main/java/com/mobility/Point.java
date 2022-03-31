package com.mobility;

/**
 * class Point, representing a coordinate in a two-dimensional space.
 * 
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class Point {
    /**
     * the max value of a point's x coordinate.
     */
    private static final int MAX_X = 800;
    /**
     * the max value of a point's y coordinate.
     */
    private static final int MAX_Y = 600;
    /**
     * the min value of a point's x and y coordinate.
     */
    private static final int MIN_XY = 0;
    /**
     * the value of the point's x coordinate.
     */
    private int x;
    /**
     * the value of the point's y coordinate.
     */
    private int y;

    /**
     * Parameterized constructor
     * 
     * @param x - int value of x coordinate.
     * @param y - int value of y coordinate.
     *          if not viable values, the constructor will initialize them with 0.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor
     *
     * @param point - Point object to copy.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * checks if a point is within valid boundaries.
     *
     * @param newLocation Point reference to examine.
     * @return true if the values are between the boundaries, false otherwise.
     */
    public static boolean checkBoundaries(Point newLocation) {
        return ((MIN_XY <= newLocation.getX() && newLocation.getX() <= MAX_X) &&
                (MIN_XY <= newLocation.getY() && newLocation.getY() <= MAX_Y));
    }

    /**
     * x getter
     * 
     * @return int value of x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * y getter
     * 
     * @return int value of y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * x setter.
     * 
     * @param x - int value of the new x coordinate.
     * @return boolean if x value is valid(between boundaries).
     */
    public boolean setX(int x) {
        boolean isSuccess = false;
        if (MIN_XY <= x && x <= MAX_X) {
            this.x = x;
            isSuccess = true;
        }
        return isSuccess;

    }

    /**
     * y setter.
     * 
     * @param y - int value of new y coordinate.
     * @return boolean if y value is valid(between boundaries).
     */
    public boolean setY(int y) {
        boolean isSuccess = false;
        if (MIN_XY <= y && y <= MAX_Y) {
            this.y = y;
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * checks if this object and the other object 'o' are identical.
     *
     * @param o , the second object to check equality with.
     * @return true, if the both objects point to the same address in memory or the 2 objects have identical attributes
     * values, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return getX() == point.getX() && getY() == point.getY();
    }

    /**
     * toString implementation of Point object.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}
