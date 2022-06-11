package com.memento.animal;

import java.util.Stack;

public class AnimalCaretaker implements Cloneable {
    private Stack<AnimalMemento> stateList = new Stack<>();
    private static final int MAX_SIZE = 3;

    public void addMemento(AnimalMemento m) {
        stateList.push(m);
    }

    public AnimalMemento getMemento() {
        return stateList.pop();
    }

    public boolean isEmpty(){
        return stateList.empty();
    }

    public boolean isFull(){
        return  stateList.size() == MAX_SIZE;
    }

    @Override
    public AnimalCaretaker clone() {
        AnimalCaretaker clone = new AnimalCaretaker();
        Stack<AnimalMemento> tempStack = new Stack<>();
        for(AnimalMemento m : this.stateList){
            tempStack.push(m);
        }
        clone.stateList = tempStack;
        return clone;
    }

}
