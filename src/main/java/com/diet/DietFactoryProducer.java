package com.diet;

public class DietFactoryProducer {
    public static AbstractDietFactory getFactory(String factory){
        switch (factory) {
            case "Herbivore" -> {
                return new Herbivore();
            }
            case "Omnivore" -> {
                return new Omnivore();
            }
            case "Carnivore" -> {
                return new Carnivore();
            }
        }
       return null;
    }
}
