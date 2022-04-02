import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AnimalEquals extends AnimalTesting {
    @Test
    public void animalEquals(){
        assertFalse(lion.equals(turtle));
        assertFalse(lion.equals(bear));
        assertFalse(lion.equals(giraffe));
        assertFalse(lion.equals(elephant));
    }

}
