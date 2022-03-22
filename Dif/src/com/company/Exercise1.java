package com.company;

public class Exercise1 extends Exercise {

    @Override
    public double[] eSolution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 0;
            } else {
                u1[i] = u1[i - 1] + functionXY[i - 1];
            }
            functionXY[i] = H * (Math.exp(2 * H * i) - Math.exp(H * i) * u1[i]);
        }
        return u1;
    }

    @Override
    public double[] rk2Solution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 0;
            } else {
                u1[i] = u1[i - 1] + k2[i - 1];
            }
            k1[i] = H * (Math.exp(2 * H * i) - Math.exp(H * i) * u1[i]);
            k2[i] = H * (Math.exp(2 * H * i + 0.05)) - Math.exp(H * i + (k1[i] / 2)) * u1[i]);
        }
        return u1;
    }

    @Override
    public double[] rk4Solution(double[] x) {
        for (int i = 0; i < 21; i++) {
            if (i == 0) {
                u1[i] = 0;
            } else {
                u1[i] = u1[i - 1] + ((k1[i - 1] + 2 * k2[i - 1] + 2 * k3[i - 1] + k4[i - 1]) / 6);
            }
            k1[i] = H * (Math.exp(2 * H * i) - Math.exp(x[i]) * x[i]);
            k2[i] = H * (Math.exp(2 * (H * i + 0.05)) - Math.exp(x[i] + 0.05) * (u1[i] + (k1[i] / 2)));
            k3[i] = H * (Math.exp(2 * (H * i + 0.05)) - Math.exp(x[i] + 0.05) * (u1[i] + (k2[i] / 2)));
            k4[i] = H * (Math.exp(2 * (H * i + 0.1)) - Math.exp(x[i] + 0.1) * (u1[i] + k3[i]));
        }
        return u1;
    }
}
