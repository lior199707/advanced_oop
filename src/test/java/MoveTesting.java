import com.mobility.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTesting extends AnimalTesting {

    @Test
    public void moveTestToInvalidLocation() {
        // assert all false values
        assertEquals(lion.move(new Point(-50, -50)), 0);
        assertEquals(giraffe.move(new Point(-50, -50)), 0);
        assertEquals(bear.move(new Point(-50, -50)), 0);
        assertEquals(turtle.move(new Point(-50, -50)), 0);
        assertEquals(elephant.move(new Point(-50, -50)), 0);
        assertEquals(lion.move(new Point(-50, -50)), 0);
    }

    @Test
    public void moveTestToInvalidLocationX() {
        // assert all false values
        assertEquals(lion.move(new Point(-50, 50)), 0);
        assertEquals(giraffe.move(new Point(-50, 50)), 0);
        assertEquals(bear.move(new Point(-50, 50)), 0);
        assertEquals(turtle.move(new Point(-50, 50)), 0);
        assertEquals(elephant.move(new Point(-50, 50)), 0);
        assertEquals(lion.move(new Point(-50, 50)), 0);
    }

    @Test
    public void moveTestToInvalidLocationY() {
        // assert all false values
        assertEquals(lion.move(new Point(50, -50)), 0);
        assertEquals(giraffe.move(new Point(50, -50)), 0);
        assertEquals(bear.move(new Point(50, -50)), 0);
        assertEquals(turtle.move(new Point(50, -50)), 0);
        assertEquals(elephant.move(new Point(50, -50)), 0);
        assertEquals(lion.move(new Point(50, -50)), 0);
    }


    @Test public void moveTestDefaultLocation() {
        assertEquals(lion.move(new Point(20,0)), 0);
        assertEquals(giraffe.move(new Point(50,0)), 0);
        assertEquals(bear.move(new Point(100,5)), 0);
        assertEquals(turtle.move(new Point(80,0)), 0);
        assertEquals(elephant.move(new Point(50,90)), 0);
    }
}
