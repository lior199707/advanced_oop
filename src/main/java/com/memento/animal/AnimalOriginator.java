package com.memento.animal;

import com.animals.Animal;

import java.util.ArrayList;

public class AnimalOriginator {
    private ArrayList<Animal> model;

    // the originator stores the model that is changing dynamically.
    public void setModel(ArrayList<Animal> model) { this.model = model; }
    public ArrayList<Animal> getModel() { return model; }
    // returns a memento with the cloned model(should not change dynamically)
    public AnimalMemento createMemento() {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            System.out.println("create AnimalMemento : cloning " + animal.getName());
            animals.add(animal.clone());
        }

        return new AnimalMemento(animals);
    }

//    public void setMemento(AnimalMemento memento) {
//        model = memento.getModel();
//    }
}
