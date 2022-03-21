package com.company;

public class Exercise2 extends Exercise {
    @Override
    double[] eilerSolution(double[] x) {
        double[] u1iEiler = new double[21];
        double[] u2iEiler = new double[21];
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1iEiler[i] = 3;
                u2iEiler[i] = 5;
            } else {
                u1iEiler[i] = u1iEiler[i-1] + (H * u2iEiler[i-1]);
                u2iEiler[i] = u2iEiler[i-1] + (H * (6 * Math.exp(x[i]) - (-3 * u1iEiler[i-1])));
            }
        }
        return u1iEiler;
    }

    @Override
    double[] rk2Solution(double[] x) {
        double[] u1iEiler = new double[21];
        double[] u2iEiler = new double[21];
        double[] k1 = new double[21];
        double[] k2 = new double[21];
        double[] l1 = new double[21];
        double[] l2 = new double[21];

        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1iEiler[i] = 3;
                u2iEiler[i] = 5;
            } else {
                u1iEiler[i] = u1iEiler[i-1] + (H * u2iEiler[i-1]);
                u2iEiler[i] = u2iEiler[i-1] + (H * (6 * Math.exp(x[i]) - (-3 * u1iEiler[i-1])));
            }
        }

    }

    @Override
    double[] rk4Solution(double[] x) {
        return new double[0];
    }
}
