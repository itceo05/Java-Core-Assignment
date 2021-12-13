package com.bkap.util;

import com.bkap.entities.Product;
import com.sun.scenario.effect.impl.state.AccessHelper;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InputHelper {
    private static Scanner sc = new Scanner(System.in);
    private static boolean isPass = true;
    public static ResourceBundle bundle = Language.getBundle();

    public static String inputString(String field, String ...validators) {
        String str;
        do {
            isPass = true;
            System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s: ", field);
            str = sc.nextLine();
            if(validators != null)
                for (int i = 0; i < validators.length; i++) {
                    if(validators[i].contains("required")){
                        if(!checkRequired(str)){
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            isPass = false;
                            break;
                        }
                    }

                    if(validators[i].contains("max")){
                        int valueMax = Integer.parseInt(str.split(":")[1]);
                        if(!checkMax(str, valueMax)){
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            isPass = false;
                            break;
                        }
                    }

                    if(validators[i].contains("length")){
                        int length = Integer.parseInt(validators[i].split(":")[1]);
                        if(!checkLength(str, length)){
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            isPass = false;
                            break;
                        }
                    }

                    if(validators[i].contains("contains")){
                        String s = validators[i].split(":")[1];
                        if(!checkContains(str, s)){
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            isPass = false;
                            break;
                        }
                    }

                    if(validators[i].contains("consists")) {
                        String split = validators[i].split(":")[1];
                        int begin = Integer.parseInt(split.split("-")[0]);
                        int end = Integer.parseInt(split.split("-")[1]);
                        if(!checkConsists(str, begin, end)){
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            isPass = false;
                            break;
                        }
                    }

                }
        } while (!isPass);

        return str;
    }


    public static int inputInt(String field, String ...validators){
        int number = 0;
        do {
            isPass = true;
            System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s: ", field);
            try {
                if (sc.hasNextInt()){
                    number = sc.nextInt();
                    sc.nextLine();

                    if(validators != null)
                        for(int i = 0; i < validators.length; i++) {
                            if(validators[i].contains("positive")){
                                if(!checkPositive(number)){
                                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                                    isPass = false;
                                    break;
                                }
                            }
                        }

                }
                else {
                    isPass = false;
                    sc.nextLine();
                    throw new InputMismatchException(bundle.getString("inputError"));
                }
            }
            catch (InputMismatchException e){
                isPass = false;
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + e.getMessage());
            }
        }
        while(!isPass);

        return number;
    }


    private static boolean checkRequired(String s) {
        return s.length() != 0;
    }

    private static boolean checkMax(String s, int max) {
        return s.length() < max;
    }

    private static boolean checkLength(String s, int length) {
        return s.length() == length;
    }

    private static boolean checkContains(String str, String c) {
        return (str.charAt(0)+"").equals(c);
    }

    private static boolean checkPositive(int n) {
        return n > 0;
    }

    private static boolean checkConsists(String s, int begin, int end){
        if(s.length() >= begin && s.length() <= end)
            return true;

        return false;
    }

}
