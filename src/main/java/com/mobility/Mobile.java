package com.mobility;

/**
 * Mobile is an abstract class implementing Ilocatable.
 * this class defines movement in a two dimentional space.
 *
 * @author Sagie Baram
 * @author Lior Shilon
 */
public abstract class Mobile implements Ilocatable {
    private Point location;
    private double totalDistance;

    /**
     *
     * Mobile constructor.
     * 
     * @param location - Point object.
     */
    public Mobile(Point location) {
        setLocation(location);
        totalDistance = 0;

    }

    /**
     * adding double value of parameter to total distance.
     * 
     * @param distance - double value to add.
     */
    public void addTotalDistance(double distance) {
        totalDistance += distance;
    }

    /**
     * calculating the distance between the current location to the next location
     * using Pythagoras.
     * 
     * @param nextLocation - Point object to measure it's distance with.
     * @return the distance between two point objects.
     */
    public double calcDistance(Point nextLocation) {
        return Math.sqrt(Math.pow((location.getX() - nextLocation.getX()), 2)
                + Math.pow((location.getY() - nextLocation.getY()), 2));
    }

    /**
     *
     * Updating the location of this object to the next location if valid.
     * 
     * @param nextLocation - Point object to change location to.
     * @return distance made between points or 0 if no distance was made.
     *
     */
    public double move(Point nextLocation) {
        double distance = calcDistance(nextLocation);
        if (setLocation(nextLocation)) {
            addTotalDistance(distance);
            return distance;
        }
        // if no distance was traveled or point values are out of bounds.
        return 0;
    }

    /**
     * totalDistance getter.
     * 
     * @return totalDistance - double value of the total distance made.
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * implementation of Ilocatable interface setLocation.
     * 
     * @param point - Point object to set.
     * @return boolean value if the set was successful or not.
     */
    @Override
    public boolean setLocation(Point point) {
        boolean isSuccess = false;
        if (Point.checkBoundaries(point)) {
            this.location = new Point(point);
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * implementation of Ilocatable interface setLocation.
     * location getter.
     * 
     * @return location - Point object.
     */
    @Override
    public Point getLocation() {
        return location;
    }
}
