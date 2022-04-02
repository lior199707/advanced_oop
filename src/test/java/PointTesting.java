import com.mobility.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTesting {
    @Test
    public void testPointConstruction(){
        assertTrue(Point.checkBoundaries(new Point(50, 50)));
        assertTrue(Point.checkBoundaries(new Point(800, 600)));
        assertTrue(Point.checkBoundaries(new Point(0, 0)));

        assertFalse(Point.checkBoundaries(new Point(-50, -50)));
        assertFalse(Point.checkBoundaries(new Point(50, -50)));
        assertFalse(Point.checkBoundaries(new Point(801, 601)));

        Point p1 = new Point();
        Point p2 = new Point(10,10);

        double calcDistance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        assertEquals(calcDistance, 14.142135623730951);
        assertNotEquals(calcDistance, 14);
    }
}
