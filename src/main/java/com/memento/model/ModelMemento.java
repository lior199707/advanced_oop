package com.memento.model;

import com.graphics.AnimalModel;

public class ModelMemento {
    private AnimalModel model;

    public ModelMemento(AnimalModel model){
        this.model = model;
    }

    public AnimalModel getModel() { return model; }
}
