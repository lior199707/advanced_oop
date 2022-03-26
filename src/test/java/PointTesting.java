import com.mobility.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTesting {
    @Test
    public void testPointConstruction(){
        Point p = new Point(-1,-1);
        Point p2 = new Point(-1,10);
        Point p3 =  new Point(10,-1);
        Point p4 = new Point(10,10);

        assertEquals(p, new Point(0, 0));
        assertEquals(p2, new Point(0, 0));
        assertEquals(p3, new Point(0, 0));
        assertEquals(p4, new Point(10, 10));
    }
}
