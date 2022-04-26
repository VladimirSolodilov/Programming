public class Main {
    public static void printValue(double[][] coef, double[] u, double x) {
        System.out.println("\ni\t\t x\t\t\t P\t\t\t\t Q\t\t\t\t u\t\t\t\t u(аналитич.)\t Погрешность");
        for (int i = 0; i <= 10; i++){
            System.out.println(i + "\t\t " + String.format("%.1f", x)
                    + "\t\t " + ((i == 0) ? "\t-\t" : (i == 10) ? "\t-\t" : String.format("%.5f", coef[i - 1][0]))
                    + "\t\t " + ((i == 0) ? "\t-\t" : (i == 10) ? "\t-\t" : String.format("%.5f", coef[i - 1][1]))
                    + "\t\t " + String.format("%.5f", u[i])
                    + "\t\t " + String.format("%.5f", allFunc(x))
                    + "\t\t " + String.format("%.5f", Math.abs(allFunc(x) - u[i])));
            x += 0.1;
        }
    }

    public static double[][] progonkaMethod(double[][] matr) {
        int length = matr.length;
        double[][] tempArr = new double[length][2];

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                tempArr[0][0] = -matr[0][1] / matr[0][0];
                tempArr[0][1] = matr[0][length] / matr[0][0];
            } else {
                if(i != length - 1)
                    tempArr[i][0] = matr[i][i + 1] / (-matr[i][i] - matr[i][i - 1] * tempArr[i - 1][0]);
                tempArr[i][1] = (matr[i][i - 1] * tempArr[i - 1][1] - matr[i][length]) / (-matr[i][i] - matr[i][i - 1] * tempArr[i - 1][0]);
            }
        }
        return tempArr;
    }

    public static double[][] matr(double a, double b, double c, int n, double x, double h, double A, double B){
        double[][] arr = new double[n - 1][n];

        for (int i = 0; i < n - 1; i++) {
            if (i == 0) {
                arr[0][0] = b;
                arr[0][1] = c;
                arr[0][n - 1] = h * h * rightFunc(x) - A * a;
            } else if (i == n - 2) {
                arr[n - 2][n - 3] = a;
                arr[n - 2][n - 2] = b;
                arr[n - 2][n - 1] = h * h * rightFunc(x) - B * c;
            } else {
                arr[i][i - 1] = a;
                arr[i][i] = b;
                arr[i][i + 1] = c;
                arr[i][n - 1] = h * h * rightFunc(x);
            }
            x += h;
        }

        return arr;
    }

    public static double[] arg(double[][] matr, double a1, double a2){
        int n = matr.length;
        double[] x = new double[n + 2];

        x[0] = allFunc(a1);
        for (int i = n; i > 0; i--){
            if(i == n)
                x[i] = matr[i - 1][1];
            else
                x[i] = matr[i - 1][0] * x[i + 1] + matr[i - 1][1];
        }
        x[n + 1] = allFunc(a2);

        return x;
    }

    public static double allFunc(double x) {
        return -Math.exp(x) * Math.cos(x) + Math.exp(x) * Math.sin(x) + x + 1;
    }

    public static double rightFunc(double x) {
        return 2 * x;
    }

    public static void main(String[] args) {
        System.out.println("Практическая работа 3. Метод прогонки");
        System.out.println("Начало программы...");

        double p = -2, q = 2;
        double a1 = 0, a2 = 1, h = (a2 - a1) / 10;
        double a = 1 - h * p / 2, b = h * h * q - 2, c = 1 + h * p / 2;
        double A = 0, B = allFunc(a2);
        double x = 0;
        double[] u;
        double[][] arr, coef;

        arr = matr(a, b, c, 10, x, h, A, B);
        coef = progonkaMethod(arr);
        u = arg(coef, a1, a2);

        printValue(coef, u, x);

        System.out.println("Конец программы...");
    }
}
