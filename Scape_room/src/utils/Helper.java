package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    private static Scanner input = new Scanner(System.in);

    public static boolean readBoolean(String message){
        boolean data = true;
        boolean dataOk = false;
        String answer = "";

        do{
            try{
                System.out.println(message);
                answer = input.nextLine();
                if(answer.equalsIgnoreCase("yes")){
                    dataOk = true;
                    data = false;
                }else if(answer.equalsIgnoreCase("no")){
                    dataOk = true;
                }
            } catch (Exception e) {
                System.err.println("Format error. Please, try again. Type only Yes or No.");
            }

        }while(!dataOk);
        return data;
    }
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
                input.nextLine();
                System.out.println(message);
                data = input.nextLine();
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


    public static String readEmail(String message) {
        String data = "";
        boolean dataOk = false;

        while(!dataOk) {
            System.out.println(message);
            data = input.next();
            input.nextLine();
            if(!validateEmail(data)) {
                System.err.println("Invalid email address. Please, try again.");
            } else {
                dataOk = true;
            }

        }
        return data;

    }

    public static boolean validateEmail(String data) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(data).matches();
    }

}
