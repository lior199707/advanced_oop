package com.privateutil;

import com.animals.Animal;

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

    public static String findAnimalImagePath(String type, String color, int direction){
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

        if (direction == 1){
            path.append("1.png");
        } else {
            path.append("2.png");
        }

        System.out.println(path);
        return String.valueOf(path);
    }

    public static String findFoodImagePath(String type){
        StringBuilder path = new StringBuilder();
        String lowerCaseType = type.toLowerCase();
        path.append(PICTURE_PATH);
        path.append("food_images/");
        path.append(lowerCaseType);
        if (lowerCaseType.equals("meat")){
            path.append(".gif");
        } else {
            path.append(".png");
        }

        return String.valueOf(path);
    }

    // TODO: Consider naming for both methods including local variables.
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

    // TODO: Consider naming for both methods including local variables.
    public static ImageIcon createImageIcon(Animal animal){
        BufferedImage picture = animal.getImg1();
        Image modifiedImage;

        ImageIcon image = new ImageIcon(picture);
        Image toSizeImage = image.getImage();
        modifiedImage = toSizeImage.getScaledInstance(220, 180, Image.SCALE_SMOOTH);

        return new ImageIcon(modifiedImage);
    }
}
