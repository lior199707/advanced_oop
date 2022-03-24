package diet;

import food.IEdible;
import animals.Animal;

/**
 * Meat eating animals.
 */
public class Carnivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        // increases animal weight by 10%
        if (canEat(food.getFoodtype())) {
            double newWeight = (animal.getWeight() * 0.1) + animal.getWeight();
            animal.setWeight(newWeight);
        }
        return animal.getWeight();
    }

    @Override
    public boolean canEat(EFoodType food) {
        // can eat MEAT
        return food == EFoodType.MEAT;
    }
}
