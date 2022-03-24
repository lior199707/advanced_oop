package mobility;

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
     * Copy constructor
     * 
     * @param point - Point object to copy.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * Parameterized constructor
     * 
     * @param x - int value of x coordinate.
     * @param y - int value of y coordinate.
     *          if not viable values, the constructor will initialize them with 0.
     */
    public Point(int x, int y) {
        // see usage of point and figure out the default values.
        if (isViable(x, y)) {
            this.x = x;
            this.y = y;
        } else {
            this.x = 0;
            this.y = 0;
        }
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
     * isViable checks if a point is within valid boundries.
     * 
     * @param x - int value of x coordinate.
     * @param y - int value of y coordinate.
     * @return boolean value if the values are valid.
     */
    public boolean isViable(int x, int y) {
        return ((MIN_XY <= x && x <= MAX_X) && (MIN_XY <= y && y <= MAX_Y));
    }

    /**
     * x setter.
     * 
     * @param x - int value of x coordinate.
     * @return boolean if x value is valid.
     */
    public boolean setX(int x) {
        if (MIN_XY <= x && x <= MAX_X) {
            this.x = x;
            return true;
        }
        return false;

    }

    /**
     * y setter.
     * 
     * @param y - int value of y coordinate.
     * @return boolean if y value is valid.
     */
    public boolean setY(int y) {
        if (MIN_XY <= y && y <= MAX_Y) {
            this.y = y;
            return true;
        } else {
            return false;
        }
    }

    /**
     * toString implementation of Point object.
     * 
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Point))
            return false;
        else {
            Point point = (Point) obj;
            return (this.getX() == point.getX() && this.getY() == point.getY());
        }

    }
}
