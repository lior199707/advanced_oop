package com.graphics;

public class AddHerbivoreDialog extends AddAnimalDialog {
    private static String[] types = {"Giraffe", "Turtle", "Elephant"};

    public AddHerbivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, "Herbivore");
        System.out.println("I'm in herbivore dialog");
    }
}
