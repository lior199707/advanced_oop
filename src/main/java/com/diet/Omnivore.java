/**
 * All-type eating animals.
 */
public class Omnivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        // eatting meat type increase animal weight by 10%
        // eatting veggies increase animal weight by 7%

        return 0;
    }

    @Override
    public boolean canEat(EFoodType food) {
        // can eat all except NOTFOOD.
        return false;
    }
}
