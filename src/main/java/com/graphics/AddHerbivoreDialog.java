package com.graphics;

/**
 * AddHerbivoresDialog is an extension of AddAnimalDialog
 * opens a dialog with Herbivores only.
 */
public class AddHerbivoreDialog extends AddAnimalDialog {
    /**
     * String array of herbivore animal types.
     */
    private static String[] types = {"Giraffe", "Turtle", "Elephant"};

    /**
     * AddHerbivoreDialog constructor.
     * passes the animal types string array and the factory type to the constructor of
     * AddAnimalDialog.
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @see AddAnimalDialog
     */
    public AddHerbivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, "Herbivore");
    }
}
