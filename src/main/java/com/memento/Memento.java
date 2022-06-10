package com.memento;

import com.animals.Animal;

import java.util.ArrayList;

public class Memento {
    private ArrayList<Animal> model;

    public Memento(ArrayList<Animal> model){
        this.model = model;
    }

    public ArrayList<Animal> getModel() { return model; }
}
