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
    public void setWeight(){
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
    public void setFurForBear(){
        Bear bear1 = (Bear) bear;
        assertTrue(bear1.setFurColor("WHITE"));
        assertTrue(bear1.setFurColor("GRAY"));
        assertTrue(bear1.setFurColor("BLACK"));
        assertFalse(bear1.setFurColor("Purple"));
    }

    @Test
    public void setTrunkLengthForElephant(){
        Elephant elph = (Elephant) elephant;
        assertTrue(elph.setTrunkLength(0.5));
        assertFalse(elph.setTrunkLength(0.49));
        assertTrue(elph.setTrunkLength(3.0));
        assertFalse(elph.setTrunkLength(3.1));
        assertFalse(elph.setTrunkLength(-1));
    }

    @Test
    public void setNeckLengthForGiraffe(){
        Giraffe giraffe1 = (Giraffe) giraffe;
        assertTrue(giraffe1.setNeckLength(1));
        assertFalse(giraffe1.setNeckLength(0.99));
        assertTrue(giraffe1.setNeckLength(2.5));
        assertFalse(giraffe1.setNeckLength(2.6));
        assertFalse(giraffe1.setNeckLength(-1));
    }

    @Test public void setAgeForTurtle(){
        Turtle torty = (Turtle) turtle;
        assertTrue(torty.setAge(0));
        assertFalse(torty.setAge(-1));
        assertTrue(torty.setAge(500));
        assertFalse(torty.setAge(501));
        assertFalse(torty.setAge(-1));
    }
}
