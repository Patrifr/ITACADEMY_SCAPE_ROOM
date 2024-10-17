package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {

    private static Scanner input = new Scanner(System.in);

    public static int readInt(String message) {
        int data = 0;
        boolean dataOk = false;

        while(!dataOk) {
            try {
                System.out.println(message);
                data = input.nextInt();
                dataOk = true;
            } catch(InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                input.nextLine();
            }
        }
        return data;

    }

    public static String readString(String message) {
        String data = "";
        boolean dataOk = false;

        while(!dataOk) {
            try {
                System.out.println(message);
                data = input.next();
                input.nextLine();
                dataOk = true;
            } catch (Exception e) {
                System.err.println("Error. Please, try again.");
                input.nextLine();
            }
        }
        return data;

    }

    public static double readDouble(String message) {
        //falta comprovar que el preu no sigui negatiu...
        double data = 0d;
        boolean dataOk = false;

        while(!dataOk) {
            try {
                System.out.println(message);
                data = input.nextDouble();
                dataOk = true;
            } catch(InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                input.nextLine();
            }
        }
        return data;

    }


    public static void readEmail(String email) {
        //comprovar que l'email és correcte...
    }
}
