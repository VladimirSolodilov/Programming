package com.company;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Exercise {
    protected final double H = 0.1;
    protected double[] functionXY = new double[21];
    protected double[] u1 = new double[21];
    protected double[] u2 = new double[21];
    protected double[] k1 = new double[21];
    protected double[] k2 = new double[21];
    protected double[] k3 = new double[21];
    protected double[] k4 = new double[21];
    protected double[] l1 = new double[21];
    protected double[] l2 = new double[21];
    protected double[] l3 = new double[21];
    protected double[] l4 = new double[21];

    abstract double[] eSolution(double[] x);
    abstract double[] rk2Solution(double[] x);
    abstract double[] rk4Solution(double[] x);

    public void print(double[] x) {
        System.out.println("\tX\t\t\tМетод Эйлера \t\t\tМетод Рунге-Кутта 2-го порядка\t\tМетод Рунге-Кутта 4-го порядка");
        for (int i = 0; i < 21; i++) {
            System.out.format("%5f\t\t  ", x[i]);
            System.out.format("%5f\t\t\t\t\t\t", eSolution(x)[i]);
            System.out.format("%5f\t\t\t\t\t\t\t", rk2Solution(x)[i]);
            System.out.format("%5f\t\t\t\t\n", rk4Solution(x)[i]);
        }
    }

    public double[] xCreate() {
        DecimalFormat df = new DecimalFormat("#.#");
        double[] x = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                x[i] = 0;
            } else {
                x[i] = Double.parseDouble(df.format(H * i));
            }
        }
        return x;
    }
}
