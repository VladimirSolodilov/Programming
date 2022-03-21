package com.company;

public class Exercise1 extends Exercise {

    @Override
    public double[] eilerSolution(double[] x) {
        double[] functionXY = new double[21];
        double[] uiEiler = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                uiEiler[i] = 0;
            } else {
                uiEiler[i] = uiEiler[i - 1] + functionXY[i - 1];
            }
            functionXY[i] = H * (Math.exp(2 * x[i]) - Math.exp(x[i]) * uiEiler[i]);
        }
        return uiEiler;
    }

    @Override
    public double[] rk2Solution(double[] x) {
        double[] k1 = new double[21];
        double[] k2 = new double[21];
        double[] uiRG2 = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                uiRG2[i] = 0;
            } else {
                uiRG2[i] = uiRG2[i - 1] + k2[i - 1];
            }
            k1[i] = H * (Math.exp(2 * x[i]) - Math.exp(x[i]) * uiRG2[i]);
            k2[i] = H * (Math.exp(2 * (x[i] + 0.05)) - Math.exp(x[i] + (k1[i] / 2)) * uiRG2[i]);
        }
        return uiRG2;
    }

    @Override
    public double[] rk4Solution(double[] x) {
        double[] k1 = new double[21];
        double[] k2 = new double[21];
        double[] k3 = new double[21];
        double[] k4 = new double[21];
        double[] uiRG4 = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                uiRG4[i] = 0;
            } else {
                uiRG4[i] = uiRG4[i - 1] + (k1[i - 1] + 2 * k2[i - 1] + 2 * k3[i - 1] + k4[i - 1]) / 6;
            }
            k1[i] = H * x[i];
            k2[i] = H * (Math.exp(2 * (x[i] + 0.05)) - Math.exp(x[i] + 0.05) * (uiRG4[i] + (k1[i] / 2)));
            k3[i] = H * (Math.exp(2 * (x[i] + 0.05)) - Math.exp(x[i] + 0.05) * (uiRG4[i] + (k2[i] / 2)));
            k4[i] = H * (Math.exp(2 * (x[i] + 0.1)) - Math.exp(x[i] + 0.1) * (uiRG4[i] + k3[i]));
        }
        return uiRG4;
    }
}
