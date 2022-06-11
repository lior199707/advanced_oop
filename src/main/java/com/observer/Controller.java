package com.observer;

import com.graphics.ZooPanel;

/**
 * Controller is an Observer which controls the zoo panel.
 */
public class Controller implements Observer {
    /**
     * notify method.
     * @param obi Observable object.
     * @param pan ZooPanel object.
     */
    @Override
    public void notify(Observable obi,ZooPanel pan) {
        pan.manageZoo();
    }

}
