package org.example;
import java.util.*;
import org.example.mechanic.Method;




public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Method.regle();
        Method.start();
        Method.GameLogic();

    }
}