package com.graphics;

public class AddCarnivoreDialog extends AddAnimalDialog {
    private static final String[] types = {"Lion"};

    public AddCarnivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, "Carnivore");
    }
}
