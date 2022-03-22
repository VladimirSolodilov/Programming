package com.company;

public class Exercise2 extends Exercise {
    @Override
    double[] eSolution(double[] x) {
        u1[0] = 3;
        u2[0] = 5;
        for (int i = 1; i < 21; i++) {
            u1[i] = u1[i - 1] + (H * u2[i - 1]);
            u2[i] = u2[i - 1] + (H * (6 * Math.exp(x[i]) - (-3 * u1[i - 1])));
        }
        return u1;
    }

    @Override
    double[] rk2Solution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 3;
                u2[i] = 5;
            } else {
                u1[i] = u1[i - 1] + ((k1[i-1] + k2[i - 1]) / 2);
                u2[i] = u2[i - 1] + (H * (6 * Math.exp(x[i]) - (-3 * u1[i - 1])));

            }
            k1[i] = H * u2[i];
            l1[i] = H * (6 * Math.exp(x[i]) - (-3 * u2[i]));
            l2[i] = H * (6 * Math.exp(x[i] + H) - (-3 * (u2[i] + l1[i])));
            k2[i] = H * (((l1[i] + l2[i]) / 2) + l1[i]);
        }
        return u1;
    }

    @Override
    double[] rk4Solution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 3;
                u2[i] = 5;
            } else {
                u1[i] = u1[i-1] + ((k1[i-1] + 2 * k2[i-1] + 2 * k3[i-1] + k4[i-1]) / 6);
                u2[i] = u2[i-1] + ((l1[i-1] + 2 * l2[i-1] + 2 * l3[i-1] + l4[i-1]) / 6);
            }

            k1[i] = H * (6 * Math.exp(x[i]) - (-3 * u2[i]));
            l1[i] = H * 6 * Math.exp(x[i]);
            k2[i] = H * (6 * Math.exp(x[i] + 0.05) - (-3 * (u2[i] + l1[i] / 2)));
            l2[i] = H * 6 * Math.exp(x[i] + 0.05);
            k3[i] = H * (6 * Math.exp(x[i] + 0.05) - (-3 * (u2[i] + l2[i] / 2)));
            l3[i] = H * 6 * Math.exp(x[i] + 0.05);
            k4[i] = H * (6 * Math.exp(x[i] + 0.05) - (-3 * (u2[i] + l3[i])));
            l4[i] = H * 6 * Math.exp(x[i] + H);
        }
        return u1;
    }
}
