import java.util.Arrays;

public class Main {

    public static double[] xFunction(double h, int N) { //Инициализация аргументов функции
        double[] x = new double[N];

        for (int i = 0; i < N; i++) {
            x[i] = h * i;
        }

        return x;
    }
    public static double[] Adams3Function(int N, double[] x, double h) { //Нахождение значения функции методом Адамса-Бэшфорта 3-го порядка
        double[] a3 = new double[N];
        a3[0] = 2; //Начальное значение

        for (int i = 1; i < N; i++) {
            if (i <= 3) {
                a3[i] = a3[i - 1] + h * (Math.sin(x[i - 1]) - a3[i - 1]); //Первые 3 элемента находим, используя метод Эйлера
            } else {
                a3[i] = a3[i - 1] + (h / 12) * (23 * (Math.sin(x[i - 1]) - a3[i - 1])
                        - 16 * (Math.sin(x[i - 2]) - a3[i - 2]) + 5 * (Math.sin(x[i - 3]) - a3[i - 3])); //Остальное находим методом Адамса-Бэшфорта
            }
        }

        return a3;
    }

    public static double[] Adams4Function(int N, double[] x, double h) { //Нахождение значения исходной функции методом Адамса-Бэшфорта 4-го порядка
        double[] a4 = new double[N];
        a4[0] = 2; //Исходное значение

        for (int i = 1; i < N; i++) {
            if (i <= 3) {
                a4[i] = a4[i - 1] + h * (Math.sin(x[i - 1]) - a4[i - 1]); //Первые 3 элемента находим, используя метод Эйлера
            } else {
                a4[i] = a4[i - 1] + (h / 24) * (55 * ((Math.sin(x[i - 1])) - a4[i - 1]) - 59 * ((Math.sin(x[i - 2])) - a4[i - 2])
                + 37 * ((Math.sin(x[i - 3])) - a4[i - 3]) - 9 * ((Math.sin(x[i - 4])) - a4[i - 4]));
            }
        }

        return a4;
    }
    public static double[] difFunction(int N, double[] x, double h, int p) { //Нахождение значений функции дифференциального уравнения
        double[] AdamsValue = new double[N];
        double[] f = new double[N];

        //Определяем порядок метода Адамса-Бэшфорта, используемый для нахождения значения, и инициализируем их
        if (p == 3) {
            AdamsValue = Adams3Function(N, x, h);
        } else if (p == 4) {
            AdamsValue = Adams4Function(N, x, h);
        }

        for (int i = 0; i < N; i++) {
            f[i] = Math.sin(x[i]) - AdamsValue[i]; //Находим значения
        }

        return f;
    }

    public static double[] analyticalFunction(int N, double[] x) { //Нахождение значений исходной функции, найденной аналитически
        double[] analyticalValue = new double[N];

        for (int i = 0; i < N; i++) {
            analyticalValue[i] = (Math.sin(x[i]) - Math.cos(x[i]) + 5 * Math.exp(-x[i])) / 2; //Находим значения
        }

        return analyticalValue;
    }
    public static void main(String[] args) {
        double error3, error4, h = 0.01; //погрешности измерений и шаг аргумента
        int N = 51; //число итераций

        System.out.println("""
                Решение дифференциального уравнения методом Адамса третьего и четвертого порядков.
                Дифференциальное уравнение имеет следующий вид: y'(x) = sin(x) - y(x), где y(0) = y0.
                Решение оформлено в виде таблицы:\s""");
        System.out.println("╔═══════════╦═══════╦═══════════════════════════╦═══════════════════════╦═══════════════════════════════╦═══════════════════════════╦════════════════════╗");
        System.out.println("║\t  N\t\t║\tx\t║\t3rd Rank Function\t\t║\t3rd Adams Value\t\t║\t4th Rank Function\t\t\t║\t4th Adams Value\t\t\t║ Analytic Value\t ║");
        System.out.println("║═══════════║═══════║═══════════════════════════║═══════════════════════║═══════════════════════════════║═══════════════════════════║════════════════════║");

        double[] x = xFunction(h, N); //нахождение аргументов функции
        double[] difFunction3Value = difFunction(N, x, h, 3); //нахождение значений диф. функции используя значения, полученные методом Адамса 3-го порядка
        double[] difFunction4Value = difFunction(N, x, h, 4); //нахождение значений диф. функции используя значения, полученные методом Адамса 4-го порядка
        double[] Adams3Value = Adams3Function(N, x, h); //нахождение значений функции методом Адамса 3-го порядка
        double[] Adams4Value = Adams4Function(N, x, h); //нахождение значений функции методом Адамса 4-го порядка
        double[] analyticValue = analyticalFunction(N, x); //нахождение значений функции аналитическим методом

        //Вывод всех найденных значений
        for (int i = 0; i < N; i++) {
            if ((i > 0 && i < 10) || i % 10 == 0) {
                System.out.println("║   " + i + "\t\t║ " + String.format("%.2f", x[i]) + "\t║       " + String.format("%.9f", difFunction3Value[i]) + "\t\t║     "
                        + String.format("%.9f", Adams3Value[i]) + "\t\t║       " + String.format("%.9f", difFunction4Value[i])
                        + "\t\t\t║       " + String.format("%.9f", Adams4Value[i]) + "\t\t\t║    " + String.format("%.9f", analyticValue[i]) + "\t ║");
            }
        }
        System.out.println("╚═══════════╨═══════╨═══════════════════════════╨═══════════════════════╨═══════════════════════════════╨═══════════════════════════╨════════════════════╝");

        System.out.println("Сравним значения, полученные методом Адамса третьего и четвертого порядков, с значениями, полученными аналитическим методом: ");
        error3 = Math.abs(Arrays.stream(analyticValue).sum() - Arrays.stream(Adams3Value).sum());
    
        System.out.println("1) суммарная погрешность значений, найденных методом Адамса 3-го порядка, от аналитических = " + error3 + ";");
        error4 = Math.abs(Arrays.stream(analyticValue).sum() - Arrays.stream(Adams4Value).sum());

        if (error3 > error4) {
            System.out.println("Получаем, что погрешность(3-ий порядок) - погрешность(4-ый порядок) = " + String.format("%.18f", error3 - error4) + ", т.е. более точным является метод Адамса четвертого порядка.");
        } else System.exit(-1);
    }
}
