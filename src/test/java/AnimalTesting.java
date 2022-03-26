import com.animals.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AnimalTesting {
    private Animal raf = new Turtle("Rafael");
    private Animal dumbo = new Elephant("Dumbo");
    private Animal mel = new Giraffe("Melman");
    private Animal yogi = new Bear("Yogi");
    private Animal leo = new Lion("Leo");

    @Test
    public void otherAnimalTryToEatLion(){
        // Other animals attempting to eat lion.
        assertFalse(raf.eat(leo));
        assertFalse(dumbo.eat(leo));
        assertFalse(mel.eat(leo));
        assertFalse(yogi.eat(leo));
    }

    @Test
    public void lionTriesToEatOtherAnimal(){
        // Lion eats
        assertTrue(leo.eat(raf));
        assertTrue(leo.eat(dumbo));
        assertTrue(leo.eat(mel));
        assertTrue(leo.eat(yogi));

    }

    @Test
    public void Cannibalism(){
        assertFalse(leo.eat(leo));
        assertFalse(raf.eat(raf));
        assertFalse(dumbo.eat(dumbo));
        assertFalse(mel.eat(mel));
        assertFalse(yogi.eat(yogi));
    }
}
