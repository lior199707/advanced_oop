package com.memento.model;

import com.animals.Animal;
import com.graphics.AnimalModel;

public class ModelOriginator implements Cloneable {
    private AnimalModel model;

    // the originator stores the model that is changing dynamically.
    public void setModel(AnimalModel model) { this.model = model; }
    public AnimalModel getModel() { return model; }
    // returns a memento with the cloned model(should not change dynamically)
    public ModelMemento createMemento() {
        AnimalModel temp = model.clone();
        for(Animal animal:temp.getAnimalModel()){
            System.out.println("whyyyyyyyyyyyyyyyyyyyyyyyyyy: " + animal.getName());
        }
        temp.saveModelState();
        return new ModelMemento(temp);
    }

    @Override
    public ModelOriginator clone() {
        ModelOriginator clone = new ModelOriginator();
        AnimalModel animals = new AnimalModel();
        for (Animal animal : model.getAnimalModel()){
            animals.addAnimal(animal.clone());
        }
        clone.setModel(animals);
        return clone;
    }

//    public void setMemento(ModelMemento memento) {
//        model = memento.getModel();
//    }
}
