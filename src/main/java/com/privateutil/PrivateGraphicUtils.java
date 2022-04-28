package com.privateutil;

import com.animals.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.graphics.IDrawable.PICTURE_PATH;

/**
 * PrivateGraphicUtils class holds utility methods used within the UI parts of the project.
 */
public class PrivateGraphicUtils {

    /**
     *
     * @param FRAME_X , integer value of the frame width in pixels.
     * @param FRAME_Y , integer value of the frame height in pixels.
     * @return Point reference for creating a UI, so it will be in the middle of the screen.
     */
    public static Point centerWindow(int FRAME_X, int FRAME_Y){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((screenSize.width / 2) - (FRAME_X / 2), (screenSize.height / 2) - (FRAME_Y / 2));
    }

    /**
     * @param type - String representing of the animal's type.
     * @param shortPath -   String used for loading animal picture.
     * @param color - String representing the animal's color.
     * @param direction - int representing animal's walking direction.
     *                  1 - right
     *                  -1 - left
     * @return String value of the path for loading the corresponding animal's picture based on animal's type,
     * shortPath, color and direction.
     */
    public static String findAnimalImagePath(String type, String shortPath, String color, int direction){
        StringBuilder path = new StringBuilder();
        path.append(PICTURE_PATH);
        path.append(type.toLowerCase());
        path.append("_images/");
//        switch (type) {
//            case "Lion" -> path.append("lio");
//            case "Bear" -> path.append("bea");
//            case "Giraffe" -> path.append("grf");
//            case "Turtle" -> path.append("trt");
//            case "Elephant" -> path.append("elf");
//        }
        path.append(shortPath);

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

    /**
     * @param type - String representing of the food's type.
     * @return String value of the path for loading the corresponding food's picture based on the food's type.
     */
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

    public static String findBackgroundImagePath(String format){
        StringBuilder path = new StringBuilder();
        path.append(PICTURE_PATH);
        path.append("background_images/");
        path.append("savanna");
        if (format.equals("png")){
            path.append(".png");
        } else {
            path.append(".jpg");
        }
        return String.valueOf(path);
    }
    // TODO: Consider naming for both methods including local variables.
    // change to path name.
    public static ImageIcon resizeImage(String path, int width, int height){
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            ImageIcon image = new ImageIcon(bufferedImage);
            Image resizedImage = image.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            System.err.println(path + " Not found!");
        }
        return null;
    }

    // TODO: Consider naming for both methods including local variables.
    public static ImageIcon setAnimalImageIcon(Animal animal){
        BufferedImage bufferedImage = animal.getImg1();
        ImageIcon image = new ImageIcon(bufferedImage);
        Image resizedImage = image.getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static TitledBorder createTitledBorder(String title, int titlePosition, int titleJustification){
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitlePosition(titlePosition);
        border.setTitleJustification(titleJustification);
        return border;
    }

    public static class ErrorDialogException extends Exception {
        public ErrorDialogException(Container container, String message, String title){
            JOptionPane.showMessageDialog(container, message,title,JOptionPane.ERROR_MESSAGE);
        }
        public ErrorDialogException(Container container, String message){
            JOptionPane.showMessageDialog(container, message,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static class InformationDialogException extends Exception {
        public InformationDialogException(Container container, String message){
            JOptionPane.showMessageDialog(container, message,"Information",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
