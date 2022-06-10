package com.memento;

import com.animals.Animal;

import java.util.ArrayList;

public class Originator {
    private ArrayList<Animal> model;

    // the originator stores the model that is changing dynamically.
    public void setModel(ArrayList<Animal> model) { this.model = model; }
    public ArrayList<Animal> getModel() { return model; }
    // returns a memento with the cloned model(should not change dynamically)
    public Memento createMemento() {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : model){
            System.out.println("create Memento : cloning " + animal.getName());
            animals.add(animal.clone());
        }

        return new Memento(animals);
    }

//    public void setMemento(Memento memento) {
//        model = memento.getModel();
//    }
}
