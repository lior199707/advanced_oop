import com.mobility.Point;
import com.privateutil.PrivateUtils;
import org.junit.Test;
import com.animals.*;

import java.nio.FloatBuffer;

import static org.junit.jupiter.api.Assertions.*;

public class SettersTesting {
    private Animal lion = PrivateUtils.loadAnimal("Lion", "simba", null);
    private Animal turtle = PrivateUtils.loadAnimal("Turtle", "Refael", null);
    private Animal giraffe = PrivateUtils.loadAnimal("Giraffe", "Melman", null);
    private Animal bear = PrivateUtils.loadAnimal("Bear", "Baloo", null);
    private Animal elephant = PrivateUtils.loadAnimal("Elephant", "Dambo", null);

    @Test
    public  void setNameTesting(){
        assertTrue(lion.setName("Yura"));
        assertTrue(turtle.setName("Yura"));
        assertTrue(bear.setName("Yura"));
        assertTrue(giraffe.setName("Yura"));
        assertTrue(elephant.setName("Yura"));
        assertFalse(lion.setName("&*^$%#%$*"));
        assertFalse(turtle.setName("1234"));
        assertFalse(bear.setName("1234"));
        assertFalse(giraffe.setName("1234"));
        assertFalse(elephant.setName("1234"));
    }
    @Test
    public void setWeightTesting(){
        assertTrue(lion.setWeight(1));
        assertTrue(turtle.setWeight(2));
        assertTrue(bear.setWeight(100));
        assertTrue(giraffe.setWeight(30));
        assertTrue(elephant.setWeight(12));
        assertFalse(lion.setWeight(0));
        assertFalse(turtle.setWeight(0));
        assertFalse(bear.setWeight(0));
        assertFalse(giraffe.setWeight(-1));
        assertFalse(elephant.setWeight(-100));
    }
    @Test
    public void setFurForBearTesting(){
        Bear bear1 = (Bear) bear;
        assertTrue(bear1.setFurColor("WHITE"));
        assertTrue(bear1.setFurColor("GRAY"));
        assertTrue(bear1.setFurColor("BLACK"));
        assertFalse(bear1.setFurColor("Purple"));
    }

    @Test
    public void setTrunkLengthForElephantTesting(){
        Elephant elph = (Elephant) elephant;
        assertTrue(elph.setTrunkLength(0.5));
        assertFalse(elph.setTrunkLength(0.49));
        assertTrue(elph.setTrunkLength(3.0));
        assertFalse(elph.setTrunkLength(3.1));
        assertFalse(elph.setTrunkLength(-1));
    }

    @Test
    public void setNeckLengthForGiraffeTesting(){
        Giraffe giraffe1 = (Giraffe) giraffe;
        assertTrue(giraffe1.setNeckLength(1));
        assertFalse(giraffe1.setNeckLength(0.99));
        assertTrue(giraffe1.setNeckLength(2.5));
        assertFalse(giraffe1.setNeckLength(2.6));
        assertFalse(giraffe1.setNeckLength(-1));
    }

    @Test public void setAgeForTurtleTesting(){
        Turtle torty = (Turtle) turtle;
        assertTrue(torty.setAge(0));
        assertFalse(torty.setAge(-1));
        assertTrue(torty.setAge(500));
        assertFalse(torty.setAge(501));
        assertFalse(torty.setAge(-1));
    }

    @Test public void setLocationTesting(){  //x: 800 , y:600
        assertTrue(lion.setLocation(new Point(800, 600)));
        assertTrue(turtle.setLocation(new Point(800, 600)));
        assertTrue(elephant.setLocation(new Point(800, 600)));
        assertTrue(bear.setLocation(new Point(800, 600)));
        assertTrue(giraffe.setLocation(new Point(800, 600)));
        assertTrue(lion.setLocation(new Point(0, 0)));
        assertTrue(turtle.setLocation(new Point(0, 0)));
        assertTrue(elephant.setLocation(new Point(0, 0)));
        assertTrue(bear.setLocation(new Point(0, 0)));
        assertTrue(giraffe.setLocation(new Point(0, 0)));
        assertFalse(lion.setLocation(new Point(801, 10)));
        assertFalse(turtle.setLocation(new Point(10, 601)));
        assertFalse(elephant.setLocation(new Point(-1, -1)));
        assertFalse(bear.setLocation(new Point(801, 601)));
        assertFalse(giraffe.setLocation(new Point(1000, 900)));

    }
}
