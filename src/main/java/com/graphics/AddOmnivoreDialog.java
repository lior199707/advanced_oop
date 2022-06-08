package com.graphics;

public class AddOmnivoreDialog extends AddAnimalDialog {
    private static final String[] types = {"Bear"};
    public AddOmnivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, "Omnivore");
    }
}
