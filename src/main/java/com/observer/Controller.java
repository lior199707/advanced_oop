package com.observer;

import com.graphics.ZooPanel;

public class Controller implements Observer {

    @Override
    public void notify(Observable obi,ZooPanel pan) {
        pan.manageZoo();
    }

}
