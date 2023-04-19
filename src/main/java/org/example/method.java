package org.example;
import java.util.Scanner;

public class method {
    static Scanner sc =new Scanner(System.in);
    public static void clearConsole(){
        for(int i=0; i<100;i++)
            System.out.println();
    }
    public static void printLine(int n){
        for(int i = 0; i<n;i++)
            System.out.print(".");
        System.out.println(" ");
    }
    public static void enterContinue(){
        System.out.println("Click on enter to continue");
        sc.nextLine();


    }
    public static int scInt(String prompt, int userChoice){
        int input = 0;
        do{
            System.out.println(prompt);
            try {
                input=Integer.parseInt(sc.next());
            }catch (Exception e){
                input=-1;
                System.out.println("please enter a number");
            }
        }while ((input<1|| input>userChoice));
        return input;
    }
    public static void printTitle(String title){
        printLine(50);
        System.out.println(title);
        printLine(50);
    }

}