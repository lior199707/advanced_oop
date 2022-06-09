package com.memento;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> stateList = new Stack<>();
    public void addMemento(Memento m) {
        stateList.push(m);
    }

    public Memento getMemento() {
        return stateList.pop();
    }

    public boolean isEmpty(){
        return stateList.empty();
    }
}
