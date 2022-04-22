import com.food.plants.Cabbage;
import com.food.plants.Lettuce;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EatTesting extends AnimalTesting {

    private final Lettuce lettuce = new Lettuce();
    private final Cabbage cabbage = new Cabbage();

    @Test
    public void Cannibalism() {
        assertFalse(lion.eat(lion));
        assertFalse(bear.eat(bear));
        assertFalse(giraffe.eat(giraffe));
        assertFalse(turtle.eat(turtle));
        assertFalse(elephant.eat(elephant));
    }

    @Test
    public void cannotEatLion() {
       // Other animals attempting to eat lion.
        assertFalse(bear.eat(lion));
        assertFalse(giraffe.eat(lion));
        assertFalse(turtle.eat(lion));
        assertFalse(elephant.eat(lion));
    }

    @Test
    public void omnivoreEatAll() {
        assertFalse(bear.eat(lion));
        assertTrue(bear.eat(giraffe));
        assertTrue(bear.eat(turtle));
        assertTrue(bear.eat(elephant));
        assertTrue(bear.eat(lettuce));
        assertTrue(bear.eat(cabbage));
    }

    @Test
    public void carnivoreEatMeat() {
        assertTrue(lion.eat(bear));
        assertTrue(lion.eat(giraffe));
        assertTrue(lion.eat(turtle));
        assertTrue(lion.eat(elephant));
    }

    @Test
    public void carnivoreCannotEatVegetable(){
        assertFalse(lion.eat(lettuce));
        assertFalse(lion.eat(cabbage));
    }

    @Test
    public void herbivoreEatVegetable() {
        assertTrue(turtle.eat(lettuce));
        assertTrue(turtle.eat(cabbage));
        assertTrue(giraffe.eat(lettuce));
        assertTrue(giraffe.eat(cabbage));
        assertTrue(elephant.eat(lettuce));
        assertTrue(elephant.eat(cabbage));
    }

    @Test
    public void herbivoreCannotEatMeat() {
        assertFalse(turtle.eat(lion));
        assertFalse(turtle.eat(bear));
        assertFalse(turtle.eat(giraffe));
        assertFalse(turtle.eat(elephant));
        assertFalse(giraffe.eat(lion));
        assertFalse(giraffe.eat(bear));
        assertFalse(giraffe.eat(giraffe));
        assertFalse(giraffe.eat(elephant));
        assertFalse(elephant.eat(lion));
        assertFalse(elephant.eat(bear));
        assertFalse(elephant.eat(giraffe));
        assertFalse(elephant.eat(elephant));
    }
}
