package com.graphics;

import com.animals.Animal;

import java.util.ArrayList;

public class AnimalModel {
    private ArrayList<Animal> animals;
    private static final int MAX_SIZE = 10;

    AnimalModel(){
        animals = new ArrayList<>();
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

    public ArrayList<Animal> getModel(){
        return animals;
    }

    public int getModelSize() {
        return animals.size();
    }

    public String[] getAnimalNames() {
        String[] names = new String[animals.size()];

        for (int i=0; i < getModelSize(); i++){
            names[i] = animals.get(i).toString();
        }

        return names;
    }


    public boolean NameExistInModel(String name){
        for (Animal animal: animals){
            if (animal.getName().equals(name))
                return true;
        }
        return false;
    }

    public static int getModelMaxSize() {
        return MAX_SIZE;
    }

}
