package com.company;

import java.text.DecimalFormat;

public class Main {
    protected static final double H = 0.1;

    public static void main(String[] args) {
        /*Exercise exercise1 = new Exercise1();
        double[] x = exercise1.xCreate();
        Exercise exercise2 = new Exercise2();
        double[] x1 = exercise2.xCreate();

        System.out.println("Задание 1");
        exercise1.print(x);
        System.out.println("\nЗадание 2");
        exercise2.print(x1);*/

        DecimalFormat df = new DecimalFormat("#.#");
        double[] x = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                x[i] = 0;
            } else {
                double h = 0.1;
                x[i] = df.format(H*i);
            }
            System.out.println(x[i]);
        }


        /*for (int i = 0; i < 21; i++) {
            System.out.println((7 * Math.exp(Math.sqrt(3) * 0.1 * i)
                    - Math.exp(-0.1 * i * Math.sqrt(3)) - 3 * Math.exp(0.1 * i)));
        }*/


    }
}