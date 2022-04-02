import com.animals.*;
import com.privateutil.PrivateUtils;

public abstract class AnimalTesting {
    protected final Animal lion = PrivateUtils.loadAnimal("Lion","Simba" , null);
    protected final Animal bear = PrivateUtils.loadAnimal("Bear","Beary" , null);
    protected final Animal giraffe = PrivateUtils.loadAnimal("Giraffe","Melman" , null);
    protected final Animal turtle = PrivateUtils.loadAnimal("Turtle","Rafael" , null);
    protected final Animal elephant = PrivateUtils.loadAnimal("Elephant","Dumbo" , null);
}