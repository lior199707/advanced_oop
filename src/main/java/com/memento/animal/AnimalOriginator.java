package com.memento.animal;

import com.animals.Animal;

import java.util.ArrayList;

public class AnimalOriginator implements Cloneable {
    private ArrayList<Animal> model;

    // the originator stores the model that is changing dynamically.
    public void setModel(ArrayList<Animal> model) { this.model = model; }
    public ArrayList<Animal> getModel() { return model; }
    // returns a memento with the cloned model(should not change dynamically)
    public AnimalMemento createMemento() {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            animals.add(animal.clone());
        }

        return new AnimalMemento(animals);
    }

    @Override
    public AnimalOriginator clone() {
        AnimalOriginator clone = new AnimalOriginator();
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            animals.add(animal.clone());
        }
        clone.setModel(animals);
        return clone;
    }

//    public void setMemento(ModelMemento memento) {
//        model = memento.getModel();
//    }
}
