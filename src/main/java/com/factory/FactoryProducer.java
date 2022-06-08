package com.factory;

public class FactoryProducer {
    public IAnimalFactory getFactory(String factoryType) {
        switch (factoryType){
            case "Herbivore" -> {
                System.out.println("creating herbivore factory");
                return new HerbivoreFactory();
            }
            case "Omnivore" -> {
                return new OmnivoreFactory();
            }
            case "Carnivore" -> {
                return new CarnivoreFactory();
            }
        }
        return null;
    }
}
