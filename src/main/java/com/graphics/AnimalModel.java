package com.graphics;

import com.animals.Animal;

import java.util.ArrayList;

public class AnimalModel {
    private ArrayList<Animal> animals;
    private static final int MAX_SIZE = 10;
    private boolean isChanged;

    AnimalModel(){
        animals = new ArrayList<>();
        isChanged = false;
    }

    public boolean addAnimal(Animal animal){
        boolean isSuccess = false;
        if (animals.size() < MAX_SIZE){
            animals.add(animal);
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean removeAnimal(Animal animal){
        boolean isSuccess = false;
        if (animals.size() > 0){
            animals.remove(animal);
            isSuccess = true;
        }
        return isSuccess;
    }

    public void removeAllAnimals(){
        animals = new ArrayList<>();
    }

    public ArrayList<Animal> getAnimalModel(){
        return animals;
    }

    public int getAnimalModelSize() {
        return animals.size();
    }

    public String[] getAnimalNames() {
        String[] names = new String[animals.size()];

        for (int i = 0; i < getAnimalModelSize(); i++){
            names[i] = animals.get(i).toString();
        }

        return names;
    }


    public boolean containsAnimalName(String name){
        for (Animal animal: animals){
            if (animal.getName().equals(name))
                return true;
        }
        return false;
    }

    public static int getModelMaxSize() {
        return MAX_SIZE;
    }

    public boolean getChangesState(){
        return isChanged;
    }

    public void setChangesState(boolean state){
        isChanged = state;
    }

}
