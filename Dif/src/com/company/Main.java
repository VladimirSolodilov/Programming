package com.company;

public class Main {
    protected static final double H = 0.1;

    public static void main(String[] args) {
        Exercise exercise1 = new Exercise1();
        double[] x = exercise1.xCreate();
        Exercise exercise2 = new Exercise2();
        double[] x1 = exercise2.xCreate();

        System.out.println("Задание 1");
        exercise1.print(x);
        System.out.println("\nЗадание 2");
        exercise2.print(x1);
    }
}