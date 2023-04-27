package org.example;
import java.util.*;
import org.example.mechanic.Method;
import org.example.mechanic.Start;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Start.regle();
        Start.start();
        Start.GameLogic();

    }
}