package cn.kevin.sms.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public static void databaseMenu() {
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
        databaseMenu();
        return choice();
    }

    public static boolean reRun() {
        Scanner input = new Scanner(System.in);
        System.out.print("--- Input 'y' to continue or 'n' to exit: ");
        String cont = input.next();
        return "Y".equals(cont) || "y".equals(cont);
    }

    public static int reChoose() {
        Scanner input = new Scanner(System.in);
        System.out.print("--- Choose database number: ");
        return input.nextInt();
    }

    public static void deleteSco() {
        System.out.println("--- Please choose the method on how to delete information: ");
        System.out.println("--- 1.By student's subject ID.(Delete 1 info with this student.)");
        System.out.println("--- 2.By student ID.(It will delete all info with this student!)");
        System.out.print("--- Input: ");
    }

    public static void selectStu() {
        System.out.println("--- Please choose the method on how to select information: ");
        System.out.println("--- 1.By student ID.");
        System.out.println("--- 2.By student age.");
        System.out.println("--- 3.By student name.");
//        System.out.println("--- 4.By students' birthday.");
        System.out.println("--- 4.By student Email.");
        System.out.println("--- 5.By students gender.");
        System.out.println("--- 6.By students last name.");
        System.out.println("--- 7.All students' information.");
        System.out.print("--- Input: ");
    }

    public static void selectSub() {
        System.out.println("--- Please choose the method on how to select information: ");
        System.out.println("--- 1.By subject ID.");
        System.out.println("--- 2.By subjects full name.");
        System.out.println("--- 3.By subjects' teacher ID.");
        System.out.println("--- 4.By subjects credit.");
        System.out.println("--- 5.By subjects part name.");
        System.out.println("--- 6.All subjects information.");
        System.out.print("--- Input: ");
    }

    public static void selectSco() {
        System.out.println("--- Please choose the method on how to select information: ");
        System.out.println("--- 1.By student's subject ID.");
        System.out.println("--- 2.By student ID.");
        System.out.println("--- 3.By subject ID.");
        System.out.println("--- 4.By subject name.");
        System.out.println("--- 5.By subject score.");
        System.out.println("--- 6.All scores information.");
        System.out.println("--- 7.Passing score information.");
        System.out.print("--- Input: ");
    }

    public static Date dateTranslate(String strDate) {
        //以下都是日期的转换操作
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
