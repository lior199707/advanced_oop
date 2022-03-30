package com.mobility;

/**
 * Point class
 * 
 * @author Sagie Baram
 * @author Lior Shilon
 */
public class Point {
    private static final int MAX_X = 800;
    private static final int MAX_Y = 600;
    private static final int MIN_XY = 0;
    private int x;
    private int y;

    /**
     * Parameterized constructor
     * 
     * @param x - int value of x coordinate.
     * @param y - int value of y coordinate.
     *          if not viable values, the constructor will initialize them with 0.
     */
    public Point(int x, int y) {
        // see usage of point and figure out the default values.
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
     * isViable checks if a point is within valid boundaries.
     *
     * @param newLocation Point reference to examine.
     * @return boolean value if the values are valid.
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
     * @param x - int value of x coordinate.
     * @return boolean if x value is valid.
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
     * @param y - int value of y coordinate.
     * @return boolean if y value is valid.
     */
    public boolean setY(int y) {
        boolean isSuccess = false;
        if (MIN_XY <= y && y <= MAX_Y) {
            this.y = y;
            isSuccess = true;
        }
        return isSuccess;
    }

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
