package animals;

public class Lion extends Animal {
    private static final double DEFAULT_WEIGHT = 408.2;
    private static final Point DEFAULT_STARTING_LOCATION = new Point(20, 0);

    private int scarCount;

    public Lion(String name) {
        super(name, DEFAULT_WEIGHT, DEFAULT_STARTING_LOCATION);
        scarCount = 0;
    }

    private void roar() {
        MessageUtility.logSound(this.getName(), "Roars, then stretches and shakes its mane");
    }

    @Override
    public void makeSound() {
        roar();
    }

    @Override
    public boolean eat(IEdible food) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EFoodType getFoodType() {

    }
}
