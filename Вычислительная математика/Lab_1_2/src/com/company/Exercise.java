package com.company;

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
    protected double[] x = new double[21];

    abstract double[] eSolution(double[] x);
    abstract double[] rk2Solution(double[] x);
    abstract double[] rk4Solution(double[] x);

    public double[] createX() {
        for (int i = 0; i < 21; i++) {
            x[i] = H * i;
        }
        return x;
    }

    public void print(double[] x) {
        System.out.println("\tX\t\t\tМетод Эйлера \t\t\tМетод Рунге-Кутта 2-го порядка\t\tМетод Рунге-Кутта 4-го порядка");
        for (int i = 0; i < 21; i++) {
            System.out.format("%5f\t\t  ", x[i]);
            System.out.format("%5f\t\t\t\t\t\t", eSolution(x)[i]);
            System.out.format("%5f\t\t\t\t\t\t\t", rk2Solution(x)[i]);
            System.out.format("%5f\t\t\t\t\n", rk4Solution(x)[i]);
        }
    }
}
