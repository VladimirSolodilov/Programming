package com.company;

public class Exercise2 extends Exercise {
    @Override
    double[] eSolution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 3;
                u2[i] = 5;
            } else {
                u1[i] = u1[i - 1] + (H * u2[i - 1]);
                u2[i] = u2[i - 1] + (H * (6 * Math.exp(x[i - 1]) + 3 * u1[i - 1]));
            }
        }
        return u2;
    }

    @Override
    double[] rk2Solution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 3;
                u2[i] = 5;
            } else {
                u1[i] = u1[i - 1] + ((k1[i - 1] + k2[i - 1]) / 2);
                u2[i] = u2[i - 1] + (H * (6 * Math.exp(x[i - 1]) +
                        3 * u1[i - 1]));
            }
            k1[i] = H * u2[i];
            l1[i] = H * (6 * Math.exp(x[i]) + 3 * u1[i]);
            l2[i] = H * (6 * Math.exp(x[i] + H) + 3 * (u1[i] + k1[i]));
            k2[i] = H * (((l1[i] + l2[i]) / 2) + l1[i]);
        }
        return u2;
    }

    @Override
    double[] rk4Solution(double[] x) {

        return u1;
    }
}
