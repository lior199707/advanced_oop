package com.graphics;

import javax.swing.*;

public interface CardinalDialog {
    /**
     * abstract method to create the north side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createNorthPanel();
    /**
     * abstract method to create the west side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createWestPanel();
    /**
     * abstract method to create the east side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createEastPanel();
    /**
     * abstract method to create the south side of the panel.
     * @return JPanel containing different items each descendant can implement.
     */
    JPanel createSouthPanel();

    /**
     * createDialog will call the directional panel methods and add them to dialog.
     */
    void createDialog();
}
