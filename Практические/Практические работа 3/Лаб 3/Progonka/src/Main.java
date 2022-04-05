public class Main {
    public static void main(String[] args){
        int n = 10;
        double p = 1, q = -2, a1 = 0, a2 = 1, h = (a2 - a1) / n;
        double a = 1 - h * p / 2, b = h * h * q - 2, c = 1 + h * p / 2;
        double A = 1, B = istFunc(a2);
        double[][] coeff;
        double[] u;
        double x = 0;
        double[][] arr;

        arr = matrix(a, b, c, n, x, h, A, B);
        coeff = progonka(arr);
        u = arg(coeff, a1, a2);

        System.out.println("Программа находит численные значения ОДУ II-го порядка методом прогонки.");
        System.out.println("\ni\t\t|x\t\t\t|P\t\t\t\t|Q\t\t\t\t|u\t\t\t\t|u(аналитич.)\t|Погрешность");
        for (int i = 0; i <= n; i++){
            System.out.println(i + "\t\t|" + String.format("%.1f", x)
                    + "\t\t|" + ((i == 0) ? "\t-\t" : (i == n) ? "\t-\t" : String.format("%.5f", coeff[i - 1][0]))
                    + "\t\t|" + ((i == 0) ? "\t-\t" : (i == n) ? "\t-\t" : String.format("%.5f", coeff[i - 1][1]))
                    + "\t\t|" + String.format("%.5f", u[i])
                    + "\t\t|" + String.format("%.5f", istFunc(x))
                    + "\t\t|" + String.format("%.5f", Math.abs(istFunc(x) - u[i])));
            x += h;
        }
    }

    public static double[][] progonka(double[][] matrix){ // Метод для нахождения прогоночных коэффициентов
        int n = matrix.length;
        double[][] c = new double[n][2];

        for (int i = 0; i < n; i++){
            if (i == 0){
                c[0][0] = -matrix[0][1] / matrix[0][0];
                c[0][1] = matrix[0][n] / matrix[0][0];
            }else{
                if(i != n - 1)
                    c[i][0] = matrix[i][i + 1] / (-matrix[i][i] - matrix[i][i - 1] * c[i - 1][0]);
                c[i][1] = (matrix[i][i - 1] * c[i - 1][1] - matrix[i][n]) / (-matrix[i][i] - matrix[i][i - 1] * c[i - 1][0]);
            }
        }

        return c;
    }

    public static double[][] matrix(double a, double b, double c, int n, double x, double h, double A, double B){ // Метод для заполнения матрицы коэффициентами
        double[][] arr = new double[n - 1][n];

            for (int i = 0; i < n - 1; i++) {
                if (i == 0) {
                    arr[0][0] = b;
                    arr[0][1] = c;
                    arr[0][n - 1] = h * h * func(x) - A * a;
                } else if (i == n - 2) {
                    arr[n - 2][n - 3] = a;
                    arr[n - 2][n - 2] = b;
                    arr[n - 2][n - 1] = h * h * func(x) - B * c;
                } else {
                    arr[i][i - 1] = a;
                    arr[i][i] = b;
                    arr[i][i + 1] = c;
                    arr[i][n - 1] = h * h * func(x);
                }
                x += h;
            }

        return arr;
    }

    public static double[] arg(double[][] matrix, double a1, double a2){ // Метод для нахождения значений функции в точках
        int n = matrix.length;
        double x[] = new double[n + 2];

        x[0] = istFunc(a1);
        for (int i = n; i > 0; i--){
            if(i == n)
                x[i] = matrix[i - 1][1];
            else
                x[i] = matrix[i - 1][0] * x[i + 1] + matrix[i - 1][1];
        }
        x[n + 1] = istFunc(a2);

        return x;
    }

    public static double func(double x){ // Метод для обозначения правой части исходного ОДУ
        return Math.cos(x) - 3 * Math.sin(x);
    }

    public static double istFunc(double x){ // Метод для нахождения аналитически найденного значения функции в точке
        return Math.exp(x) + Math.sin(x);
    }
}
