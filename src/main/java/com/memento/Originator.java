package com.memento;

import com.graphics.AnimalModel;

public class Originator {
    private AnimalModel model;

    // the originator stores the model that is changing dynamically.
    public void setModel(AnimalModel model) { this.model = model; }
    public AnimalModel getModel() { return model; }
    // returns a memento with the cloned model(should not change dynamically)
    public Memento createMemento() {
        return new Memento(model.clone());
    }

//    public void setMemento(Memento memento) {
//        model = memento.getModel();
//    }
}
