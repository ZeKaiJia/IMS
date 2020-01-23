package cn.kevin.sms.controller;


import java.util.Scanner;

/**
 * @author kevin
 */
public class MenuController {
    public static void menu() {
        System.out.println("------------------------------------------------------");
        System.out.println("--- Welcome to our new student manage system 2.0 ! ---");
        System.out.println("--- All the message are storing in MySQL database  ---");
        System.out.println("------------------------------------------------------");
    }

    public static void datebaseMenu() {
        System.out.println("------------------------------------------------------");
        System.out.println("---   Please firstly choice a database to modify   ---");
        System.out.println("---               1.Student database               ---");
        System.out.println("---               2.Subject database               ---");
        System.out.println("---                3.Score database                ---");
        System.out.println("------------------------------------------------------");
    }

    public static void functionMenu() {
        System.out.println("------------------------------------------------------");
        System.out.println("--- Please input the number of following functions ---");
        System.out.println("---            1.Insert new information            ---");
        System.out.println("---          2.Delete useless information          ---");
        System.out.println("---            3.Update old information            ---");
        System.out.println("---          4.Select various information          ---");
        System.out.println("------------------------------------------------------");
    }

    public static int choice() {
        Scanner input = new Scanner(System.in);
        System.out.print("---: ");
        return input.nextInt();
    }

    public static void unCorrect() {
        System.out.println("--- Please input correct number of choice!");
    }

    public static int initialMenu() {
        menu();
        datebaseMenu();
        return choice();
    }

    public static boolean reRun() {
        Scanner input = new Scanner(System.in);
        System.out.print("--- Input 'y' to continue or 'n' to exit: ");
        String cont = input.next();
        return cont.equals("Y") || cont.equals("y");
    }

    public static int reChoose() {
        Scanner input = new Scanner(System.in);
        System.out.print("--- Choose database number: ");
        return input.nextInt();
    }
}
