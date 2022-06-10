package com.graphics;

import com.animals.Animal;
import com.memento.animal.AnimalCaretaker;
import com.memento.animal.AnimalMemento;
import com.memento.animal.AnimalOriginator;
import com.observer.Controller;
import com.observer.Observer;
import com.privateutil.PrivateGraphicUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * AnimalModel represents the model used for the zoo.
 * it consists of array list with default set size.
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class AnimalModel implements Cloneable {
    /**
     * ArrayList of animals.
     */
    private ArrayList<Animal> animals;
    /**
     * integer value representing the maximum size of the animal array.
     */
    private static final int MAX_SIZE = 10;
    private static final int MAX_QUEUE_SIZE = 5;
    /**
     * boolean value indicating if the model is changed or not.
     */
    private boolean isChanged;
    /**
     * boolean value indicating the current model sleep state.
     */
    private boolean sleepState = false;


    private ExecutorService pool;

    private Observer controller;

    private LinkedBlockingDeque<Runnable> animalQueue;

    private AnimalCaretaker caretaker = new AnimalCaretaker();
    private AnimalOriginator animalOriginator = new AnimalOriginator();

    /**
     * AnimalModel constructor.
     * initiating the array list and default state to false.
     */
    public AnimalModel(){
        pool = Executors.newFixedThreadPool(MAX_SIZE);
        animalQueue = new LinkedBlockingDeque<>(MAX_QUEUE_SIZE);
        animals = new ArrayList<>();
        isChanged = false;
        controller = new Controller();
        animalOriginator.setModel(animals);
    }

    public AnimalModel(AnimalModel model){
        AnimalModel modelCopy = new AnimalModel();
        for (Animal animal : model.getAnimalModel()){
            modelCopy.addAnimal(animal.clone());
        }
    }

    /**
     * addAnimal will add an animal if the current only if the current size does not exceed the maximum.
     * @param animal Animal object to add.
     * @return boolean value indicating if the animal addition was successful or not.
     */
    public boolean addAnimal(Animal animal){
        boolean isSuccess = false;
        animal.setObserver(controller);
        if (animals.size() < MAX_SIZE){
            animal.setThreadSuspended(isAsleep());
            animals.add(animal);
            pool.execute(animal);
            isSuccess = true;
        } else {
            animal.setThreadSuspended(true);
            animalQueue.add(animal);
        }
        return isSuccess;
    }

    public void pullFromQueue(){
        if (animalQueue.size() > 0 && animals.size() < MAX_SIZE){
            Animal animal = (Animal) animalQueue.pop();
            animal.setThreadSuspended(isAsleep());
            animals.add(animal);
            pool.execute(animal);
        }
    }

    public  boolean getModelSleepState(){return sleepState;}

    public int getAnimalQueueSize(){
        return animalQueue.size();
    }

    public static int getMaxQueueSize() {
        return MAX_QUEUE_SIZE;
    }

    /**
     * removeAllAnimals assigns an empty array list.
     * the removal of the previous array list is handled via the garbage collector.
     */
    public void removeAllAnimals(){
        animals = new ArrayList<>();
    }

    /**
     * AnimalModel getter
     * @return ArrayList of Animals.
     */
    public ArrayList<Animal> getAnimalModel(){
        return animals;
    }

    /**
     * Model size getter.
     * @return integer representation of the animal model size.
     */
    public int getAnimalModelSize() {
        return animals.size();
    }

    /**
     * the sleep method, setting all animals in model to suspended state
     * sleep state of the model is set to true.
     */
    public void sleep(){
//        sleepState = true;
        for (Animal animal : animals){
            animal.setSuspended();
            sleepState = true;
        }

    }

    public void setSleepState(boolean state){
        sleepState = state;
    }

    /**
     * Animal names getter
     * @return String array of the animal names of all the animals in the animal model.
     */
    public String[] getAnimalNames() {
        String[] names = new String[animals.size()];

        for (int i = 0; i < getAnimalModelSize(); i++){
            names[i] = animals.get(i).toString();
        }

        return names;
    }

    /**
     * the wakeUp method, setting all animals in model to resumed state
     * sleep state of the model is set to false.
     */
    public void wakeUp(){
        //sleepState = false;
        for (Animal animal : animals){
            animal.setResumed();
            animal.setThreadAlive(true);
//            animal.run();
        }
        sleepState = false;
    }

    /**
     * stopping all animal threads in the model.
     * allowing safe thread termination.
     */
    public void stopAll() {
        for (Animal animal : animals){
            animal.stop();
        }
        pool.shutdown();
    }

    public void  runModel(){
        for(Animal animal: this.getAnimalModel())
            animal.run();
    }

//    public void startAll() {
//        for (Animal animal : animals){
//            animal.start();
//        }
//    }

    /**
     * sleepState getter.
     * @return boolean representation of the sleepState, true if sleep is on false otherwise.
     */
    public boolean isAsleep() {
        return sleepState;
    }

    /**
     * containsAnimalName evaluates if the animal model contains a given name or not.
     * @param name String representation of the animal name.
     * @return boolean value indicating if the animal name is contained in the animal model or not.
     */
    public boolean containsAnimalName(String name){
        for (Animal animal: animals){
            if (animal.getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * MAX_SIZE getter
     * @return integer value representing the model maximum size.
     */
    public static int getModelMaxSize() {
        return MAX_SIZE;
    }

    /**
     * isChanged getter.
     * @return boolean value indicating the state of isChanged.
     */
    public boolean getChangesState(){
        return isChanged;
    }

    /**
     * isChanged setter.
     * @param state boolean value of the model state.
     */
    public void setChangesState(boolean state){
        isChanged = state;
    }

    @Override
    public AnimalModel clone() {
        AnimalModel modelCopy = new AnimalModel();
        modelCopy.setSleepState(isAsleep());
        modelCopy.setChangesState(getChangesState());
        for (Animal animal : this.getAnimalModel()){
            modelCopy.addAnimal(animal.clone());
        }
        return modelCopy;
    }

    public void saveModelState() {
        if (!caretaker.isFull()) {
            animalOriginator.setModel(animals);
            AnimalMemento animalMemento = animalOriginator.createMemento();
            caretaker.addMemento(animalMemento);
            System.out.println("Saving current state");
        } else {
            String message = "State list is full (3 states)";
            PrivateGraphicUtils.popInformationDialog(null, message);
        }
    }

    public void restoreModelState() {
        if (!caretaker.isEmpty()) {
            // animalMemento with cloned model
            this.stopAll();
            AnimalMemento animalMemento = caretaker.getMemento();
            animalOriginator.setModel(animalMemento.getModel());
            animals = new ArrayList<>();
            pool = Executors.newFixedThreadPool(MAX_SIZE);
            animalQueue = new LinkedBlockingDeque<>(MAX_QUEUE_SIZE);
            for (int i = 0; i < animalMemento.getModel().size(); i++){
                this.addAnimal(animalMemento.getModel().get(i));
            }
            System.out.println("Restoring current state");
        } else {
            String message = "No saved states";
            PrivateGraphicUtils.popInformationDialog(null, message);
        }
    }

    public AnimalCaretaker getCaretaker() {
        return caretaker;
    }

    public AnimalOriginator getOriginator() {
        return animalOriginator;
    }

    public void setCaretaker(AnimalCaretaker caretaker) {
        this.caretaker = caretaker;
    }

    public void setOriginator(AnimalOriginator animalOriginator) {
        this.animalOriginator = animalOriginator;
    }
}
