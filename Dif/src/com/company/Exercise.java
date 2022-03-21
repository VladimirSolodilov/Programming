package com.company;

public abstract class Exercise {
    protected final double H = 0.1;
    abstract double[] eilerSolution(double[] x);
    abstract double[] rk2Solution(double[] x);
    abstract double[] rk4Solution(double[] x);

    public void print(double[] x) {
        System.out.println("\tX\t\t\tМетод Эйлера \t\t\tМетод Рунге-Кутта 2-го порядка\t\tМетод Рунге-Кутта 4-го порядка");
        for (int i = 0; i < 21; i++) {
            System.out.format("%5f\t\t  ", x[i]);
            System.out.format("%5f\t\t\t\t\t\t", eilerSolution(x)[i]);
            System.out.format("%5f\t\t\t\t\t\t\t", rk2Solution(x)[i]);
            System.out.format("%5f\t\t\t\t\n", rk4Solution(x)[i]);
        }
    }

    public double[] xCreate() {
        double[] x = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                x[i] = 0;
            } else {
                x[i] = 0.1 * i;
            }
        }
        return x;
    }
}
