package com.company;

public class Main {
    public static void main(String[] args) {
        Exercise exercise1 = new Exercise1();
        double[] x = exercise1.createX();
        Exercise exercise2 = new Exercise2();
        double[] x1 = exercise2.createX();

        System.out.println("Задание 1");
        exercise1.print(x);
        System.out.println("\nЗадание 2");
        exercise2.print(x1);
    }
}