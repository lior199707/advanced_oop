package com.memento.animal;

import com.animals.Animal;

import java.util.ArrayList;

public class AnimalMemento {
    private ArrayList<Animal> model;

    public AnimalMemento(ArrayList<Animal> model){
        this.model = model;
    }

    public ArrayList<Animal> getModel() { return model; }
}
