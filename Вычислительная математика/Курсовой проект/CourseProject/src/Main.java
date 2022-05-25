public class Main {
    public static double[] Adams3(int N, double[] x, double h) {
        double[] a3 = new double[N];
        a3[0] = 2;

        for (int i = 1; i < N; i++) {
            if (i <= 3) {
                a3[i] = a3[i - 1] + h * (Math.sin(x[i]) - a3[i - 1]);
            } else {
                a3[i] = a3[i - 1] + (h / 12) * (23 * (Math.sin(x[i - 1]) - a3[i - 1])
                        - 16 * (Math.sin(x[i - 2]) - a3[i - 2]) + 5 * (Math.sin(x[i - 3]) - a3[i - 3]));
            }
        }

        return a3;
    }

    public static double[] Adams4(int N, double[] x, double h) {
        double[] a4 = new double[N];
        a4[0] = 2;

        for (int i = 1; i < N; i++) {
            if (i <= 3) {
                a4[i] = a4[i - 1] + (h * (Math.sin(x[i - 1] - a4[i - 1])));
            } else {
                a4[i] = a4[i - 1] + ((h / 24) * (55 * (Math.sin(x[i - 1]) - a4[i - 1]) - 59 * (Math.sin(x[i - 2]) - a4[i - 2])
                        + 37 * (Math.sin(x[i - 3]) - a4[i - 3]) - 9 * (Math.sin(x[i - 4]) - a4[i - 4])));
            }
        }

        return a4;
    }

    public static double[] xValue(double h, int N) {
        double[] x = new double[N];

        for (int i = 0; i < N; i++) {
            x[i] = h * i;
        }

        return x;
    }

    public static double[] difFunctionValue(int N, double[] x, double h) {
        double[] Adams3Value = Adams3(N, x, h);
        double[] f = new double[N];

        for (int i = 0; i < N; i++) {
            f[i] = Math.sin(x[i]) - Adams3Value[i];
        }

        return f;
    }

    public static double[] analyticalFunctionValue(int N, double[] x) {
        double[] analyticalValue = new double[N];

        for (int i = 0; i < N; i++) {
            analyticalValue[i] = (Math.sin(x[i]) - Math.cos(x[i]) + 5 * Math.exp(-x[i])) / 2;
        }

        return analyticalValue;
    }
    public static void main(String[] args) {
        int N = 51;
        double h = 0.01;

        System.out.println("Решение дифференциального уравнения методом Адамса третьего и четвертого порядков.");
        System.out.println("Дифференциальное уравнение имеет следующий вид: y'(x) = sin(x) - y(x), где y(0) = y0.");
        System.out.println("Решение оформлено в виде таблицы: ");

        System.out.println("|N\t\t\t|x\t\t|Function 3\t\t|Adams 3\t\t|Function 4\t\t\t|Adams 4\t\t|Analytic Function");
        for (int i = 0; i < N; i++) {
            if ((i > 0 && i < 10) || i % 10 == 0) {
                System.out.println("|" + i + "\t\t\t|" + String.format("%.3f", xValue(h, N)[i]) + "\t|" + String.format("%.5f", difFunctionValue(N, xValue(h, N), h)[i]) + "\t\t|"
                        + String.format("%.5f", Adams3(N, xValue(h, N), h)[i]) + "\t\t|" + String.format("%.5f", difFunctionValue(N, xValue(h, N), h)[i])
                        + "\t\t\t|" + String.format("%.5f", Adams4(N, xValue(h, N), h)[i]) + "\t\t|" + String.format("%.5f", analyticalFunctionValue(N, xValue(h, N))[i]));
            }
        }
    }
}
