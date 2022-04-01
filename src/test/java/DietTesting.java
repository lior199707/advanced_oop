import com.animals.Lion;
import com.diet.Carnivore;
import com.diet.Herbivore;
import com.diet.IDiet;
import com.diet.Omnivore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DietTesting {
    private final IDiet carni = new Carnivore();
    private final IDiet herbi = new Herbivore();
    private final IDiet omni = new Omnivore();

    @Test
    public void testDietEquality(){
        assertNotEquals(carni,herbi);
        assertNotEquals(carni,omni);
        assertNotEquals(herbi,omni);
    }

    @Test
    public void testToString(){
        Lion lion = new Lion("Simba");
        System.out.println(lion);
    }






}
