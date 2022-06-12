package com.observer;

import com.graphics.ZooPanel;

/**
 * Observer interface implemented by classes who wish to observe other objects.
 */
public interface Observer {
    /**
     * notify method.
     * @param pan ZooPanel object.
     */
    public void notify(ZooPanel pan);
}
