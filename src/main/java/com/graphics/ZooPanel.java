package com.graphics;

import com.animals.Animal;
import com.food.Food;
import com.food.Meat;
import com.food.plants.Cabbage;
import com.food.plants.Lettuce;
import com.privateutil.PrivateGraphicUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ZooPanel is a panel contained in ZooFrame, it holds the action panel which has buttons that open different types
 * of dialogs to interact with the model.
 * Every ZooPanel object is a thread.
 *
 *
 * @author Sagie Baram 205591829
 * @author Lior Shilon 316126143
 */
public class ZooPanel extends JPanel implements ActionListener, IThread {
    private static ZooPanel instance = null;
    /**
     * static integer representing the total amount of eaten objects (animals or food).
     */
    private static int totalEatCount = 0;
    /**
     * AnimalModel object representing the animals of the zoo.
     */
    private final AnimalModel model;
    /**
     * InfoTableDialog object for interaction with the info table.
     */
    private InfoTableDialog infoTable;
    /**
     * Food object, will be placed at the center of the zoo panel.
     */
    private Food food;
    /**
     * BufferedImage object, will hold the background image of the zoo panel.
     */
    private BufferedImage backgroundImage;

    /**
     * Thread object indicating the controller thread.
     */
    private Thread controller;

    /**
     * Atomic boolean flag which indicates if the thread is alive or not.
     */
    private AtomicBoolean threadAlive = new AtomicBoolean(true);


    /**
     * ZooPanel constructor.
     */
    public ZooPanel() {
        model = new AnimalModel();
        infoTable = new InfoTableDialog(model);
        food = null;

        // instantiating action panel
        JPanel actionPanel = new JPanel();
        // instantiating buttons
        JButton addAnimal = new JButton("Add Animal");
        JButton sleep = new JButton("Sleep");
        JButton wakeUp = new JButton("Wake Up");
        JButton clear = new JButton("Clear All");
        JButton food = new JButton("Food");
        JButton color = new JButton("Change Color");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");

        // adding to action listener
        addAnimal.addActionListener(this);
        sleep.addActionListener(this);
        wakeUp.addActionListener(this);
        clear.addActionListener(this);
        food.addActionListener(this);
        color.addActionListener(this);
        info.addActionListener(this);
        exit.addActionListener(this);

        // adding buttons to panel
        actionPanel.add(addAnimal);
        actionPanel.add(sleep);
        actionPanel.add(wakeUp);
        actionPanel.add(clear);
        actionPanel.add(food);
        actionPanel.add(color);
        actionPanel.add(info);
        actionPanel.add(exit);

        // setting properties
        Font courier = new Font("Courier", Font.PLAIN, 14);
        TitledBorder actionBorder = BorderFactory.createTitledBorder("Select Option:");

        actionBorder.setTitleFont(courier);
        actionBorder.setTitleColor(Color.blue);

        actionPanel.setBorder(actionBorder);

        this.setLayout(new BorderLayout());
        this.add(actionPanel, BorderLayout.SOUTH);

        this.start();
    }

    public static ZooPanel getInstance() {
        if (instance == null)
            instance = new ZooPanel();
        return instance;
    }

    /**
     * repaints the zoo panel.
     * @see JComponent paintComponent
     * @param g graphic object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // if background image is null it will repaint default looks and feel of a graphics component.
        g.drawImage(backgroundImage, 0, 0,this);

        // if there is a food instance, draw it.
        if (food != null)
            food.drawObject(g);

        // drawing animals.
        for (Animal animal : model.getAnimalModel()) {
            animal.drawObject(g);
        }
    }

    /**
     * utility method presenting a food dialog.
     * user may select from a variety of food types and instantiate a food object.
     */
    private void createFoodDialog() {
        String[] options = {"Lettuce", "Cabbage", "Meat"};
        int result = JOptionPane.showOptionDialog(null,
                "What food would you like to add?",
                "Add Food Dialog",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        switch (result) {
            case 0 -> setPanelFood(Lettuce.getInstance());
            case 1 -> setPanelFood(Cabbage.getInstance());
            case 2 -> setPanelFood(Meat.getInstance());
        }
    }

    /**
     * food setter.
     * @param food Food object to set.
     * @return boolean value indicating if the setter was successful or not.
     */
    public boolean setPanelFood(Food food) {
        boolean isSuccess = false;
        if (food != null){
            this.food = food;
            this.food.setPan(this);
            isSuccess = true;
        } else {
            this.food = null;
        }
        return isSuccess;
    }

    /**
     * utility method used in manage zoo.
     * isChange is traversing the animal model, if any animal has changes set to true.
     * it will set the animal changes to false and return true.
     * otherwise, it returns false.
     * @return boolean value representing changes to the model.
     */
    public boolean isChange(){
        for (Animal animal : model.getAnimalModel()){
            if (animal.getChanges()){
                animal.setChanges(false);
                return true;
            }
        }
        return false;
    }

    /**
     * utility method to test conditions for eating animals.
     * @param predator Animal object that attempts to eat.
     * @param prey Animal object that might be eaten.
     * @return boolean value representing if the animal was eaten or not.
     */
    private boolean conditionalEating(Animal predator, Animal prey){
        boolean weightIsDouble = predator.getWeight() >= prey.getWeight() * 2;
        boolean distanceIsLowerThanSize = predator.calcDistance(prey.getLocation()) < prey.getSize();
        if (weightIsDouble && distanceIsLowerThanSize){
            return predator.eat(prey);
        }
        return false;
    }

    /**
     * attemptEatAnimal is a utility method used to attempt eating animals when manageZoo is called.
     * predators can eat their prey, prey may eat its predator.
     */
    private void attemptEatAnimal(){
        for (int i = 0; i < model.getAnimalModelSize(); i++) {
            Animal predator = model.getAnimalModel().get(i);
            for (int j = i + 1; j < model.getAnimalModelSize(); j++) {
                Animal prey = model.getAnimalModel().get(j);
                if (conditionalEating(predator, prey)){
                    // terminates the thread.
                    prey.stop();
                    // if predator eat prey, deduce the amount of eat count of prey off the total eat count.
                    setTotalEatCount(getTotalEatCount() - prey.getEatCount());
                    // remove the prey.
                    model.getAnimalModel().remove(j);
                    j--;
                    // increment the total eat count & the eat count of predator.
                    updateEatCount(predator);
                    // the model was changed.
                    model.setChangesState(true);
                } else if (conditionalEating(prey, predator)) {
                    // terminates the thread.
                    predator.stop();
                    // if prey eat predator, deduce the amount of eat count of predator off the total eat count.
                    setTotalEatCount(getTotalEatCount() - predator.getEatCount());
                    // remove the predator.
                    model.getAnimalModel().remove(i);
                    i--;
                    // increment the total eat count & the eat count of prey.
                    updateEatCount(prey);
                    // the model was changed.
                    model.setChangesState(true);
                }
            }
        }
    }

    /**
     * updateEatCount increments the passed animal's eat count and the total eat count.
     * @param animal Animal object to increment its eat count.
     */
    public void updateEatCount(Animal animal){
        animal.eatInc();
        totalEatCountInc();
    }

    /**
     * static method used to increment the total eat count.
     */
    public static void totalEatCountInc(){
        totalEatCount++;
    }

    /**
     * static method used to set the total eat count.
     * @param count integer value representing the count to set.
     */
    public static void setTotalEatCount(int count){
        totalEatCount = count;
    }

    /**
     * static method used to get the value of the total eat count.
     * @return integer value representing the totalEatCount.
     */
    public static int getTotalEatCount() {
        return totalEatCount;
    }

    /**
     * getFood is the ZooPanel food data field getter.
     * @return Food object of the zoo panel.
     */
    public Food getFood(){
        return food;
    }

    /**
     * setImageBackground sets the background image to a predetermined Savanna image and repaints the panel.
     */
    public void setImageBackground(){
        try {
            backgroundImage = ImageIO.read(new File(PrivateGraphicUtils.findBackgroundImagePath("png")));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * setGreenBackground sets the backgroundImage to null and the background to green and repaints the panel.
     */
    public void setGreenBackground(){
        backgroundImage = null;
        this.setBackground(Color.green);
        repaint();
    }

    /**
     * setNoBackground sets the backgroundImage to null and the background to null (default background) and repaints the panel.
     */
    public void setNoBackground(){
        backgroundImage = null;
        this.setBackground(null);
        repaint();
    }

    /**
     * infoTable getter.
     * @return InfoTableDialog object.
     */
    public InfoTableDialog getInfoTable() {
        return infoTable;
    }

    /**
     * @see ActionListener actionPerformed
     * @param e ActionEvent, events that take place in the ZooPanel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> new AddAnimalDialog(model, this);
            case "Sleep" -> model.sleep();
            case "Wake Up" -> model.wakeUp();
            case "Clear All" -> {
                model.stopAll();
                model.removeAllAnimals();
                setTotalEatCount(0);
                infoTable.setIsOpen(false);
                repaint();
                PrivateGraphicUtils.popInformationDialog(this,"Removed all animals from the panel!");
            }
            case "Food" -> {
                createFoodDialog();
                repaint();
            }
            case "Change Color" ->{
                if(model.getAnimalModelSize() > 0){
                    new MoveAnimalDialog(model, this);
                }
                else {
                    try {
                        String message = "Zoo is currently empty!";
                        throw new PrivateGraphicUtils.ErrorDialogException(this, message);
                    } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
                }
            }
                case "Info" -> {
                //creating animals details list
                if (model.getAnimalModelSize() > 0) {
                    if (!InfoTableDialog.getIsOpen()){
                        System.out.println("first");
                        infoTable = new InfoTableDialog(model);
                        System.out.println("ersdfsdfsdf");
                        infoTable.setIsOpen(true);
                    }
                } else {
                    String message = "Zoo is currently empty!";
                    try {
                        throw new PrivateGraphicUtils.ErrorDialogException(this, message);
                    } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
                }
            }
            case "Exit" -> {
                // terminating animal's threads.
                model.stopAll();
                // terminating ZooPanel thread.
                stop();
                System.exit(1);
            }
        }
    }

    @Override
    public void start(){
        controller = new Thread(this);
        controller.start();
    }

    @Override
    public void stop(){
        threadAlive.set(false);
    }

    @Override
    public void run() {
        while (threadAlive.get()) {
            if (isChange()) {
                repaint();
            }
            attemptEatAnimal();
            if (InfoTableDialog.getIsOpen())
                infoTable.updateTable();
        }
    }
}
