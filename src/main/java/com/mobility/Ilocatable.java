package main.java.com.mobility;

/**
 * Ilocatable interface describes the functionality of a location.
 */
public interface Ilocatable {
    public Point getLocation();

    public boolean setLocation(Point point);
}
