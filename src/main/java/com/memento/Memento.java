package com.memento;

import com.graphics.AnimalModel;

public class Memento {
    private AnimalModel model;

    public Memento(AnimalModel model){
        this.model = model;
    }

    public AnimalModel getModel() { return model; }
}
