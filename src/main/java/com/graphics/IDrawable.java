package com.graphics;

import java.awt.*;

public interface IDrawable {
    public final static String PICTURE_PATH = "src/main/resources/assignment2_pictures/";
    public void loadImages(String shortPathName);
    public void drawObject (Graphics g);
    public String getColor();
}
