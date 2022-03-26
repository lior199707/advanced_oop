import com.animals.Giraffe;
import com.animals.Lion;
import com.food.IEdible;
import com.mobility.Point;
import com.zoo.ZooActions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZooActionsTesting {
    @Test
    public void testEatAnimal(){
        Object animal = new Lion("Simba");
        IEdible animal2 = new Giraffe("Sendi");
        assertTrue(ZooActions.eat(animal, animal2));
    }

    @Test
    public void testMoveAnimal(){
        Object animal = new Lion("Simba");
        Point location = new Point(30,0);
        assertTrue(ZooActions.move(animal, location));
    }
}
