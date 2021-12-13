package com.bkap.manager;

import com.bkap.util.Language;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AllManager {
    CategoryManager cateManager;
    ProductManager proManager;
    ResourceBundle bundle = Language.getBundle();
    Scanner sc = new Scanner(System.in);

    public AllManager() {
        cateManager = new CategoryManager();
        proManager = new ProductManager();
    }

    public void menu() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** *** " + bundle.getString("exercises") + " *** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("managementCate") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("managementPro") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("exit") + ".\t\t\t\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** *** **  ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public void chooseMenu() {
        int choose;
        do {
            do {
                menu();
                try {
                    if (sc.hasNextInt()) {
                        choose = sc.nextInt();
                        break;
                    } else {
                        sc.nextLine();
                        throw new InputMismatchException("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true);

            switch (choose) {
                case 1:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("managementCate"));
                    cateManager.chooseMenu();
                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("managementPro"));
                    proManager.chooseMenu();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [6] : %s", bundle.getString("program"), bundle.getString("exit"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }

        }
        while (choose != 3);
    }


}
