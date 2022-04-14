package com.privateutil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.graphics.IDrawable.PICTURE_PATH;

public class PrivateGraphicUtils {
    public static Point centerWindow(int FRAME_X, int FRAME_Y){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((screenSize.width / 2) - (FRAME_X / 2), (screenSize.height / 2) - (FRAME_Y / 2));
    }

    public static String findImagePath(String type, String color){
        StringBuilder path = new StringBuilder();
        path.append(PICTURE_PATH);
        path.append(type.toLowerCase());
        path.append("_images/");
        switch (type) {
            case "Lion" -> path.append("lio");
            case "Bear" -> path.append("bea");
            case "Giraffe" -> path.append("grf");
            case "Turtle" -> path.append("trt");
            case "Elephant" -> path.append("elf");
        }

        switch (color.toUpperCase()) {
            case "NATURAL" -> path.append("_n_");
            case "RED" -> path.append("_r_");
            case "BLUE" ->  path.append("_b_");
        }

        path.append("1.png");

        System.out.println(path);
        return String.valueOf(path);
    }

    // change to path name.
    public static ImageIcon createImageIcon(String path){
        BufferedImage picture = null;
        Image modifiedImage = null;

        try {
            picture = ImageIO.read(new File(path));
            ImageIcon image = new ImageIcon(picture);
            Image toSizeImage = image.getImage();
            modifiedImage = toSizeImage.getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println(path + "Not found");
        }

        assert picture != null;
        return new ImageIcon(modifiedImage);
    }
}
