import com.animals.*;
import com.mobility.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTesting {
    private final Animal raf = new Turtle("Rafael");
    private final Animal dumbo = new Elephant("Dumbo");
    private final Animal mel = new Giraffe("Melman");
    private final Animal yogi = new Bear("Yogi");
    private final Animal leo = new Lion("Leo");

    @Test
    public void otherAnimalTryToEatLion() {
        // Other animals attempting to eat lion.
        assertFalse(raf.eat(leo));
        assertFalse(dumbo.eat(leo));
        assertFalse(mel.eat(leo));
        assertFalse(yogi.eat(leo));
    }

    @Test
    public void lionTriesToEatOtherAnimal() {
        // Lion eats
        assertTrue(leo.eat(raf));
        assertTrue(leo.eat(dumbo));
        assertTrue(leo.eat(mel));
        assertTrue(leo.eat(yogi));
    }

    @Test
    public void Cannibalism() {
        assertFalse(leo.eat(leo));
        assertFalse(raf.eat(raf));
        assertFalse(dumbo.eat(dumbo));
        assertFalse(mel.eat(mel));
        assertFalse(yogi.eat(yogi));
    }

    @Test void moveAnimal() {
        assertEquals(10, leo.move(new Point(30,0)));
        assertEquals(407.17949999999996, leo.getWeight());
        assertEquals(50, raf.move(new Point(30,0)));
    }
}