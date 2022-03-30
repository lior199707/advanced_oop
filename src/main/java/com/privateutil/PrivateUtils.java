package com.privateutil;

import com.mobility.Point;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PrivateUtils {
    public static int userChoiceInput(int MIN_RANGE, int MAX_RANGE) {
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;

        while (true) {
            try {
                userChoice = sc.nextInt();
                if (userChoice < MIN_RANGE || userChoice > MAX_RANGE) {
                    System.out.print("Choice out of range, please try again: ");
                } else {
                    return userChoice;
                }
            } catch (InputMismatchException e) {
                System.out.println(e + " expecting integer!");
                System.out.print("Invalid choice, please try again: ");
                if (sc.next().isEmpty()) {
                    break;
                }
            }
        }
        sc.close();
        return userChoice;
    }

    public static Point pointInput() {
        Scanner sc = new Scanner(System.in);
        boolean isValidX = false;
        boolean isValidY = false;
        int x = 0, y = 0;

        while(true) {
            while (!isValidX) {
                try {
                    System.out.print("Please enter the x value of the point: ");
                    isValidX = true;
                    x = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                    isValidX = false;
                }
            }

            while (!isValidY) {
                try {
                    System.out.print("Please enter the y value of the point: ");
                    isValidY = true;
                    y = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e + " expecting integer!");
                    if (sc.next().isEmpty()) {
                        break;
                    }
                    isValidY = false;
                }
            }

            sc.close();

            Point point = new Point(x,y);
            if (Point.checkBoundaries(point))
                return point;

            System.out.println(point + " values are out of bound");

            isValidX = false;
            isValidY = false;
        }
    }

}
