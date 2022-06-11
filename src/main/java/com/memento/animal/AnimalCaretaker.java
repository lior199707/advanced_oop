package com.memento.animal;

import java.util.Stack;

/**
 * AnimalCaretaker contains a stack of AnimalMemento's.
 * it can push or pop AnimalMemento's into the stack.
 * used to save animal states during the software's lifecycle.
 */
public class AnimalCaretaker implements Cloneable {
    /**
     * Stack of AnimalMemento's indicating the amount of saved states.
     */
    private Stack<AnimalMemento> stateList = new Stack<>();
    /**
     * integer value representing the maximum size of the stack - the amount of saved states.
     */
    private static final int MAX_SIZE = 3;

    /**
     * pushing mementos to the stack.
     * @param m AnimalMemento object to push.
     */
    public void addMemento(AnimalMemento m) {
        stateList.push(m);
    }

    /**
     * popping memento from the stack.
     * @return AnimalMemento indicating the last saved state.
     */
    public AnimalMemento getMemento() {
        return stateList.pop();
    }

    /**
     * isEmpty
     * @return boolean value indicating if the stack is empty or not.
     */
    public boolean isEmpty(){
        return stateList.empty();
    }

    public boolean isFull(){
        return  stateList.size() == MAX_SIZE;
    }

    /**
     * AnimalCaretaker clone method.
     * @return AnimalCaretaker cloned object.
     */
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
