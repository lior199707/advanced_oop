package com.memento.model;

import com.animals.Animal;

import java.util.Stack;

public class ModelCaretaker implements Cloneable {
    private Stack<ModelMemento> stateList = new Stack<>();
    private static final int MAX_SIZE = 3;

    public void addMemento(ModelMemento m) {
        stateList.push(m);
    }

    public ModelMemento getMemento() {
        ModelMemento tempModel = stateList.pop();
        for (Animal animal:tempModel.getModel().getAnimalModel()){
            System.out.println("storing from mementooooo:  " + animal.getName());
        }
        tempModel.getModel().restoreModelState();
        return tempModel;
    }

    public boolean isEmpty(){
        return stateList.empty();
    }

    public boolean isFull(){
        System.out.println("size of state list" + stateList.size());
        return  stateList.size() == MAX_SIZE;
    }

    @Override
    public ModelCaretaker clone() {
        ModelCaretaker clone = new ModelCaretaker();
        Stack<ModelMemento> tempStack = new Stack<>();
        for(ModelMemento m : this.stateList){
            tempStack.push(m);
        }
        clone.stateList = tempStack;
        return clone;
    }

}
