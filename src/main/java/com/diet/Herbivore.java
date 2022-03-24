package diet;

import food.IEdible;

/**
 * Plant eating animals.
 */
public class Herbivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        // eatting veggies increase animal weight by 7%
        if (canEat(food.getFoodtype())) {
            if (food.getFoodtype() == EFoodType.MEAT) {
                double newWeight = (animal.getWeight() * 0.1) + animal.getWeight();
            } else {
                double newWeight = (animal.getWeight() * 0.07) + animal.getWeight();
            }
            animal.setWeight(newWeight);
        }
        return animal.getWeight();
    }

    @Override
    public boolean canEat(EFoodType food) {
        // can eat VEGETABLE.
        return food == EFoodType.VEGETABLE;
    }
}
