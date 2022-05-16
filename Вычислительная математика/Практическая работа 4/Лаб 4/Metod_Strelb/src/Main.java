public class Main {
    public static void main(String[] args){
        int z = 10;
        double x = 0, b = 1, u_ist = uIst(x), h = b / z, B = uIst(b);
        double[] pr;
        double[][] ur, k;
        double[] l = newton(x, b, B, z);
        double a = l[l.length - 1];
        pr = nachdan(x, a);
        ur = rungeFour(pr, x, h, z,0);
        pr = nachdan(x, a);
        k = rungeFour(pr, x, h, z, 1);

        System.out.println("Нахождение a - угла \"пристрелки\":");
        System.out.println("N\t|a\t\t\t|u(x,a)\t\t||u(x,a) - u(b)|");
        for(int i = 0; i < l.length; i++){
            pr = nachdan(x, l[i]);
            ur = rungeFour(pr, x, h, z,0);
            System.out.println(i + "\t|" + String.format("%.5f", l[i]) + "\t|" + String.format("%.5f", ur[z][0]) + "\t|" + String.format("%.5f", Math.abs(ur[z][0] - B)));
        }

        x = 0;

        System.out.println("\nПрограмма высчитывает приближённые значения в узлах задачи Коши для следующих условий: u'' + u' - 2u = cos(x) - 3sin(x); a = 0, b = 1; u(0) = 1, u(b) = " + String.format("%.5f", B) + ", u'(0) = " + String.format("%.5f", a) + " - подобранный угол \"пристрелки\"");
        System.out.println("Метод Рунге-Кутты IV-го порядка:");
        System.out.println("N\t|x\t\t\t|u\t\t\t|k1\t\t\t|l1\t\t\t|k2\t\t\t|l2\t\t\t|k3\t\t\t|l3\t\t\t|k4\t\t\t|l4\t\t\t\t|u'\t\t\t\t\t|u'' = 4u' - 2u + 4e^(5x)\t|u_аналитич\t\t|Погрешность");
        for (int i = 0; i <= z; i++){
            System.out.println(i + "\t|" + String.format("%.5f", x) + "\t|" + String.format("%.5f", ur[i][0]) + "\t|" + String.format("%.5f", k[i][0]) + "\t|" + String.format("%.5f", k[i][1]) + "\t|" + String.format("%.5f", k[i][2]) + "\t|" + String.format("%.5f", k[i][3]) + "\t|" + String.format("%.5f", k[i][4]) + "\t|" + String.format("%.5f", k[i][5]) + "\t|" + String.format("%.5f", k[i][6]) + "\t|" + String.format("%.5f", k[i][7]) + "\t\t|" + String.format("%.5f", ur[i][1]) + "\t\t\t|" + String.format("%.5f", ur[i][2]) + "\t\t\t\t\t|" + String.format("%.5f", u_ist) + "\t\t|" + String.format("%.5f", Math.abs(ur[i][0] - u_ist)));
            x += h;
            u_ist =  uIst(x);
        }
    }

    public static double[][] rungeFour(double[] pr, double x, double h, int z, int v1){ //Метод Рунге-Кутты IV-го порядка для ОДУ (любого порядка)
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
            t[0][n - 3] = h * difur(pr, x); //l1

            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[0][j] / 2;
            }
            r = x + h / 2;

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[1][j] = h * (pr[c] + t[0][c] / 2); //k2
            }
            t[1][n - 3] = h * difur(pr1, r); //l2

            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[1][j] / 2;
            }

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[2][j] = h * (pr[c] + t[1][c] / 2); //k3
            }
            t[2][n - 3] = h * difur(pr1, r); //l3

            r = x + h;
            for (int j = 0; j < n - 2; j++) {
                pr1[j] = pr[j] + t[2][j];
            }

            for (int j = 0; j < n - 3; j++, c++) {
                if (j == n - 4)
                    c = n - 3;
                t[3][j] = h * (pr[c] + t[2][c]); //k4
            }
            t[3][n - 3] = h * difur(pr1, r); //l4

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
            pr[n - 2] = difur(pr, x);
        }
        if(v1 == 0)
            return u;
        else
            return k;
    }

    public static double difur(double[] pr, double x){ //Метод для ОДУ I-го и II-го порядков
        return -pr[1] + 2 * pr[0] + Math.cos(x) - 3 * Math.sin(x);
    }

    public static double[] nachdan(double x, double a){ //Метод для начальных условий ОДУ I-го и II-го порядков
        double pr[] = new double[4];
            pr[0] = 1;
            pr[1] = a;
            pr[2] = -pr[1] + 2 * pr[0] + Math.cos(x) - 3 * Math.sin(x);
        return pr;
    }

    public static double uIst(double x){ //Метод для точных решений ОДУ
        return Math.exp(x) + Math.sin(x);
    }

    public static double[] newton(double x, double b, double B, int z){ //Метод Ньютона (используется разностная производная)
        double[] pr;
        double[][] ur0, ur;
        double h = (b - x) / z;
        double[] a = new double[5];
        a[0] = (uIst(b) - uIst(x)) / (b - x);
        a[1] = a[0] + 0.5;
        pr = nachdan(x, a[0]);
        ur0 = rungeFour(pr, x, h, z,0);

        pr = nachdan(x, a[1]);
        ur = rungeFour(pr, x, h, z,0);

        int i = 0;

        while (Math.abs(ur[z][0] - uIst(1)) > 0.00001) {
            i++;
            pr = nachdan(x, a[i]);
            ur = rungeFour(pr, x, h, z,0);
            a[i + 1] = a[i] - (ur[z][0] - B) * (a[i] - a[i - 1]) / (ur[z][0] - ur0[z][0]);
        }

        double[] c = new double[i + 1];
        for (int j = 0; j < i + 1; j++){
            c[j] = a[j];
        }
            return c;
    }
}
