public class Main {
    public static void main(String[] args){
        int z = 10;
        double x = 0, b = Math.PI, u_ist = uIst(x, 0), h = b / z;
        double[] pr;
        double[][] ur, k;
        pr = nachdan(x, 0);
        ur = eiler(pr, x, h, z, 0);

        System.out.println("Программа высчитывает приближённые значения в узлах задачи Коши для следующих условий: u' = -u + cos(x); a = 0, b = pi; u0 = 0,5");
        System.out.println("Метод Эйлера:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|u' = -u + cos(x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist = uIst(x, 0);
        }

        x = 0; h = b / z;
        u_ist = uIst(x, 0);
        pr = nachdan(x, 0);
        ur = rungeTwo(pr, x, h, z, 0, 0);
        pr = nachdan(x, 0);
        k = rungeTwo(pr, x, h, z, 0, 1);

        System.out.println("\n\nМетод Рунге-Кутты II-го порядка:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|k1\t\t\t|k2\t\t\t|u' = -u + cos(x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", k[i][0]) + "\t|" + String.format("%.5f", k[i][1]) + "\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist = uIst(x, 0);
        }

        x = 0; h = b / z;
        u_ist = uIst(x, 0);
        pr = nachdan(x, 0);
        ur = rungeFour(pr, x, h, z, 0, 0);
        pr = nachdan(x, 0);
        k = rungeFour(pr, x, h, z, 0, 1);

        System.out.println("\n\nМетод Рунге-Кутты IV-го порядка:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|k1\t\t\t|k2\t\t\t|k3\t\t\t|k4\t\t\t|u' = -u + cos(x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", k[i][0]) + "\t|" + String.format("%.5f", k[i][1]) + "\t|" + String.format("%.5f", k[i][2]) + "\t|" + String.format("%.5f", k[i][3]) + "\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist = uIst(x, 0);
        }

        z = 100;
        x = 0; b = 3; u_ist = uIst(x, 1); h = b / z;
        pr = nachdan(x, 1);
        ur = eiler(pr, x, h, z, 1);

        System.out.println("\n\nПрограмма высчитывает приближённые значения в узлах задачи Коши для следующих условий: u'' - 4u' + 2u = 4e^(5x); a = 0, b = 3; u(0) = 3, u'(0) = 4");
        System.out.println("Метод Эйлера:");
        System.out.println("N\t|x\t\t\t|u\t\t\t\t|u'\t\t\t\t\t|u'' = 4u' - 2u + 4e^(5x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", ur[i][2]) + "\t\t\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist =  uIst(x, 1);
        }

        x = 0; h = b / z;
        u_ist = uIst(x, 1);
        pr = nachdan(x, 1);
        ur = rungeTwo(pr, x, h, z, 1, 0);
        pr = nachdan(x, 1);
        k = rungeTwo(pr, x, h, z, 1, 1);

        System.out.println("\n\nМетод Рунге-Кутты II-го порядка:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|k1\t\t\t|l1\t\t\t|k2\t\t\t|l2\t\t\t\t|u'\t\t\t\t\t|u'' = 4u' - 2u + 4e^(5x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", k[i][0]) + "\t|" + String.format("%.5f", k[i][1]) + "\t|" + String.format("%.5f", k[i][2]) + "\t|" + String.format("%.5f", k[i][3]) + "\t\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", ur[i][2]) + "\t\t\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist =  uIst(x, 1);
        }

        x = 0; h = b / z;
        u_ist = uIst(x, 1);
        pr = nachdan(x, 1);
        ur = rungeFour(pr, x, h, z, 1, 0);
        pr = nachdan(x, 1);
        k = rungeFour(pr, x, h, z, 1, 1);

        System.out.println("\n\nМетод Рунге-Кутты IV-го порядка:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|k1\t\t\t|l1\t\t\t|k2\t\t\t|l2\t\t\t|k3\t\t\t|l3\t\t\t|k4\t\t\t|l4\t\t\t\t|u'\t\t\t\t\t|u'' = 4u' - 2u + 4e^(5x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", k[i][0]) + "\t|" + String.format("%.5f", k[i][1]) + "\t|" + String.format("%.5f", k[i][2]) + "\t|" + String.format("%.5f", k[i][3]) + "\t|" + String.format("%.5f", k[i][4]) + "\t|" + String.format("%.5f", k[i][5]) + "\t|" + String.format("%.5f", k[i][6]) + "\t|" + String.format("%.5f", k[i][7]) + "\t\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", ur[i][2]) + "\t\t\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist =  uIst(x, 1);
        }
    }

    public static double uIst(double x, int v){ //Метод для точных решений ОДУ
        double ist;
        if (v == 0)
            ist = 0.5 * (Math.sin(x) + Math.cos(x));
        else
            ist = (17 - 13 * Math.sqrt(2)) * Math.exp((2 + Math.sqrt(2)) * x) / 14 + (17 + 13 * Math.sqrt(2)) * Math.exp((2 - Math.sqrt(2)) * x) / 14 + 4 * Math.exp(5 * x) / 7;
        return ist;
    }

    public static double[][] eiler(double[] pr, double x, double h, int z, int v){ //Метод Эйлера для ОДУ (любого порядка)
        int n = pr.length;
        double[][] u = new double[z + 1][n];
        for (int i = 0; i <= z; i++) {
            x += h;
            for (int j = 0; j < n - 1; j++) {
                u[i][j] = pr[j];
                pr[j] += pr[j + 1] * h;
            }
            pr[n - 2] = difur(pr, x, v);
        }
        return u;
    }

    public static double[][] rungeTwo(double[] pr, double x, double h, int z, int v, int v1){ //Метод Рунге-Кутты II-го порядка для ОДУ (любого порядка)
        int n = pr.length, c = n - 3;
        double[][] u = new double[z + 1][n];
        double[][] t = new double[2][n - 2]; // t[0][n - 2] - k1, t[1][n - 2] - k2
        double[][] k = new double[z + 1][2 * n - 4];
        double[] pr1 = new double[n];
        double r;
        for (int i = 0; i <= z; i++) {
            for (int j = 0; j < n - 1; j++){
                u[i][j] = pr[j];
                pr1[j] = pr[j];
            }

            for (int j = 0; j < n - 3; j++, c++){
                if(j == n - 4)
                    c = n - 3;
                t[0][j] = h * pr[c]; //k1
            }
            t[0][n - 3] = h * difur(pr, x, v); //l1

            for (int j = 0; j < n - 2; j++){
                pr1[j] = pr[j] + t[0][j] / 2;
            }
            r = x + h / 2;

            for (int j = 0; j < n - 3; j++, c++){
                if(j == n - 4)
                    c = n - 3;
                t[1][j] = h * (pr[c] + t[0][c] / 2); //k2
            }
            t[1][n - 3] = h * difur(pr1, r, v); //l2

            for (int d = 1; d < 3; d++) {
                int j = 0;
                for (int p = n - 2; p > 0; p--, j++) {
                    k[i][(n - 2) * d - p] = t[d - 1][j];
                }
            }

            for (int j = 0; j < n - 2; j++){
                pr[j] = pr[j] + t[1][j];
            }

            x += h;
            pr[n - 2] = difur(pr, x, v);
        }
        if (v1 == 0)
            return u;
        else
            return k;
    }

    public static double[][] rungeFour(double[] pr, double x, double h, int z, int v, int v1){ //Метод Рунге-Кутты IV-го порядка для ОДУ (любого порядка)
        int n = pr.length, c = n - 3;
        double[][] u = new double[z + 1][n];
        double[][] t = new double[4][n - 2]; // t[0][n - 2] - k1, t[1][n - 2] - k2, t[2][n - 2] - k3, t[3][n - 2] - k4
        double[][] k = new double[z + 1][(int) Math.pow(2, n - 1)];
        double[] pr1 = new double[n];
        double r;
        for (int i = 0; i <= z; i++) {
            for (int j = 0; j < n - 1; j++) {
                u[i][j] = pr[j];
                pr1[j] = pr[j];
            }

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[0][j] = h * pr[c]; //k1
            }
            t[0][n - 3] = h * difur(pr, x, v); //l1

            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[0][j] / 2;
            }
            r = x + h / 2;

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[1][j] = h * (pr[c] + t[0][c] / 2); //k2
            }
            t[1][n - 3] = h * difur(pr1, r, v); //l2

///////////////////////////////////////////////////////////////////////////

            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[1][j] / 2;
            }

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[2][j] = h * (pr[c] + t[1][c] / 2); //k3
            }
            t[2][n - 3] = h * difur(pr1, r, v); //l3

///////////////////////////////////////////////////////////////////////////

            r = x + h;
            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[2][j];
            }

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[3][j] = h * (pr[c] + t[2][c]); //k4
            }
            t[3][n - 3] = h * difur(pr1, r, v); //l4

///////////////////////////////////////////////////////////////////////////

            for (int d = 1; d < 5; d++) {
                int j = 0;
                for (int p = n - 2; p > 0; p--, j++) {
                    k[i][(n - 2) * d - p] = t[d - 1][j];
                }
            }

            for (int j = 0; j < n - 2; j++){
                pr[j] = pr[j] + (t[0][j] + 2 * t[1][j] + 2 * t[2][j] + t[3][j]) / 6;
            }

            x += h;
            pr[n - 2] = difur(pr, x, v);
        }
        if(v1 == 0)
            return u;
        else
            return k;
    }

    public static double difur(double[] pr, double x, int v){ //Метод для ОДУ I-го и II-го порядков
        double dif;
        if (v == 0)
            dif = -pr[0] + Math.cos(x);
        else
            dif = 4 * pr[1] - 2 * pr[0] + 4 * Math.exp(5 * x);
        return dif;
    }

    public static double[] nachdan(double x, int v){ //Метод для начальных условий ОДУ I-го и II-го порядков
        double pr[];
        if (v == 0){
            pr = new double[3];
            pr[0] = 0.5;
            pr[1] = -pr[0] + Math.cos(x);
        }
        else{
            pr = new double[4];
            pr[0] = 3;
            pr[1] = 4;
            pr[2] = 4 * pr[1] - 2 * pr[0] + 4 * Math.exp(5 * x);
        }
        return pr;
    }
}
