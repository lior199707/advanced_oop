import com.privateutil.PrivateUtils;
import org.junit.Test;
import com.animals.*;

import static org.junit.jupiter.api.Assertions.*;

public class SettersTesting {
    @Test
    public  void setNameTesting(){
        Animal lion = PrivateUtils.loadAnimal("Lion", "simba", null);
        Animal turtle = PrivateUtils.loadAnimal("Turtle", "Refael", null);
        Animal giraffe = PrivateUtils.loadAnimal("Giraffe", "Melman", null);
        Animal bear = PrivateUtils.loadAnimal("Bear", "Baloo", null);
        Animal elephant = PrivateUtils.loadAnimal("Elephant", "Dambo", null);
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
        Animal lion = PrivateUtils.loadAnimal("Lion", "simba", null);
        Animal turtle = PrivateUtils.loadAnimal("Turtle", "Refael", null);
        Animal giraffe = PrivateUtils.loadAnimal("Giraffe", "Melman", null);
        Animal bear = PrivateUtils.loadAnimal("Bear", "Baloo", null);
        Animal elephant = PrivateUtils.loadAnimal("Elephant", "Dambo", null);
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
        Bear bear = (Bear) PrivateUtils.loadAnimal("Bear", "Baloo", null);
        bear.setFurColor("Green");


    }
}
