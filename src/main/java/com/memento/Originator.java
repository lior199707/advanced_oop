package com.memento;

import com.graphics.AnimalModel;

public class Originator {
    private AnimalModel model;

    public void setModel(AnimalModel model) { this.model = model; }
    public AnimalModel getModel() { return model; }

    public Memento createMemento() {
        return new Memento(model.clone());
    }

    public void setMemento(Memento memento) {
        model = memento.getModel();
    }
}
