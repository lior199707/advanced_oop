import com.animals.Giraffe;
import com.animals.Lion;
import com.food.IEdible;
import com.zoo.ZooActions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZooActionsTesting {
    @Test
    public void testFindAnimalType(){
        Object animal = new Lion("Simba");
        IEdible animal2 = new Giraffe("Sendi");
        assertTrue(ZooActions.eat(animal, animal2));
    }

}
