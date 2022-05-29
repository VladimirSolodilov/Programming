import java.util.Scanner;

public class Main {

    private static int ROW;

    private static int COL;

    private static final Scanner scanner = new Scanner(System.in);

    private static double[] calcTemp(double[] temp, double[][] constLeft, double[] targetFunc, int[] basic) { // изменение целевой функции
        double[] calcTemp = new double[temp.length];
        for (int i = 0; i < COL; i++) {
            calcTemp[i] = 0;
            for (int j = 0; j < ROW; j++) {
                calcTemp[i] += targetFunc[basic[j]] * constLeft[j][i];
            }
            calcTemp[i] -= targetFunc[i];
        }
        return calcTemp;
    }

    private static int minimum(double[] arr) { // поиск минимальной ячейки
        double arrMin = arr[0];
        int minPos = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arrMin) {
                arrMin = arr[i];
                minPos = i;
            }
        }

        return minPos;
    }

    private static void printAll(double[] constraintRight, double[][] constraintLeft, int[] basic) { // вывод системы линейных ограничений
        System.out.println(
                "╔═══════╦═══════════════╦═══════════════╦═══════════════╦═══════════════╦═══════════════╦═══════════════╦═══════════════╗");
        System.out.println(
                "║   X" + "\t║   " + "\tx1" + "\t\t║    " + "\tx2" + "\t\t║    " + "\tx3" + "\t\t║   " + "\tx4" + "\t\t║   "
                        +
                        "\tx5" + "\t\t║   " + "\tx6" + "\t\t║   " + "\tx*" + "\t\t║   ");
        System.out.println(
                "╠═══════╬═══════════════╬═══════════════╬═══════════════╬═══════════════╬═══════════════╬═══════════════╬═══════════════╣");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ROW; i++) {
            sb.append("║   " + "x").append(basic[i] + 1).append("\t║");

            for (int j = 0; j < COL; j++) {
                sb.append("   ").append(String.format("%.6f", constraintLeft[i][j])).append("\t║");
            }

            sb.append("   ").append(String.format("%.6f", constraintRight[i])).append("\t║");
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void main(String[] args) {
        System.out.println("Укажите количество уравнений и переменных в системе линейных ограничений: ");

        System.out.print("количество уравнений = ");
        ROW = scanner.nextInt();

        System.out.print("количество переменных = ");
        COL = scanner.nextInt() + ROW;

        double[] targetFunc = new double[COL];
        double[][] constraintsLeft = new double[ROW][COL];
        double[] constraintsRight = new double[COL];

        System.out.println("Запишите коэффициенты системы линейных ограничений: ");
        for (int i = 0; i < ROW; i++) {
            System.out.print("Уравнение " + (i + 1) + ": ");
            for (int j = 0; j < ROW; j++) {
                constraintsLeft[i][j] = scanner.nextDouble();
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = ROW; j < COL; j++) {
                if (j == i + ROW) {
                    constraintsLeft[i][j] = 1;
                } else {
                    constraintsLeft[i][j] = 0;
                }
            }
        }

        System.out.print("Запишите допустимые значения для каждого уравнения системы линейных ограничений: ");
        for (int i = 0; i < ROW; i++) {
            constraintsRight[i] = scanner.nextDouble();
        }

        System.out.print("Запишите коэффициенты при аргументах целевой функции: ");
        for (int i = 0; i < ROW; i++) {
            targetFunc[i] = scanner.nextDouble();
        }

        for (int i = ROW; i < COL; i++) {
            targetFunc[i] = 0;
        }

        scanner.close();

        double[] temp = new double[COL]; // строка целевой функции
        double[] miniRatio = new double[ROW]; // строка системы линейных ограничений с минимальным частным допустимого плана и значения коэффициента
        double[] x = new double[COL]; // изменение значений целевой функции
        double key; // значение в этой ячейке
        double z; // поиск оптимального решения целевой функции
        int[] basic = new int[ROW]; // базовые номера ячеек
        int[] nonBasic = new int[ROW]; // номера ячеек целевой функции
        int goOutCol = 0; // номер этого столбца
        int counter = 0; // счётчик таблиц
        int tempMinPos; // минимальная позиция целевой функции
        int miniRatioMinPos; // номер ячейки пересечения минимальной строки и столбца
        boolean flag = false; // условие выхода из цикла решений

        for (int i = 0; i < ROW; i++) {
            basic[i] = (i + ROW);
            nonBasic[i] = i;
        }

        System.out.println("\nЗапишем задачу в каноничном виде симплекс-таблицами: ");
        while (!flag) { // цикл завершается при достижении оптимального решения и отсутствии отрицательных коэффициентов в строке целевой функции
            System.out.println("Таблица " + counter + ".");

            counter++;
            z = 0;

            temp = calcTemp(temp, constraintsLeft, targetFunc, basic); // изменение целевой функции
            tempMinPos = minimum(temp); // поиск минимального элемента целевой функции
            printAll(constraintsRight, constraintsLeft, basic); // вывод системы линейных ограничений

            System.out.print("║   " + "Z" + "\t║");
            for (int i = 0; i < COL; i++) {
                System.out.print("   " + String.format("%.6f", temp[i]) + "\t║");
            }

            System.out.print("   ");
            for (int i = 0; i < ROW; i++) { // изменение значений в целевой строке
                x[basic[i]] = constraintsRight[i];
                x[nonBasic[i]] = 0;
            }

            for (int i = 0; i < ROW; i++) { // изменение оптимального решения целевой строки
                z = z + targetFunc[i] * x[i];
            }

            System.out.println(String.format("%.6f", z) + "\t║");
            System.out
                    .print(
                            "╚═══════╩═══════════════╩═══════════════╩═══════════════╩═══════════════╩═══════════════╩═══════════════╩═══════════════╝\n");
            for (int i = 0; i < ROW; i++) { // поиск строки системы линейных ограничений, где частное от оптимального решения и коэффициента минимально
                if (constraintsLeft[i][tempMinPos] <= 0) {
                    miniRatio[i] = 999;
                    continue;
                }
                miniRatio[i] = constraintsRight[i] / constraintsLeft[i][tempMinPos];
            }

            miniRatioMinPos = minimum(miniRatio); // запись номера минимальной строки
            for (int i = 0; i < ROW; i++) { // поиск номера минимального столбца
                if (miniRatioMinPos == i) {
                    goOutCol = basic[i];
                }
            }
            // далее осуществляется преобразование всей таблицы с помощью ключа, являющегося
            // центральной ячейкой пересечения минимальной строки и минимального столбца по
            // целевой функции посредством преобразования минимального столбца в столбец
            // нулевых значений с единичной минимальной ячейкой
            basic[miniRatioMinPos] = tempMinPos;
            nonBasic[tempMinPos] = goOutCol;
            key = constraintsLeft[miniRatioMinPos][tempMinPos]; // разрешающий элемент
            constraintsRight[miniRatioMinPos] /= key;

            for (int i = 0; i < COL; i++) { // деление минимальной строки на разрешающий элемент
                constraintsLeft[miniRatioMinPos][i] /= key;
            }

            for (int i = 0; i < ROW; i++) { // цикл для осуществления преобразования системы линейных ограничений
                // посредством разности ячеек старого плана и произведения разрешающего
                // элемента и элемента минимальной строки
                if (miniRatioMinPos == i) {
                    continue;
                }
                key = constraintsLeft[i][tempMinPos];

                for (int j = 0; j < COL; j++) {
                    constraintsLeft[i][j] -= constraintsLeft[miniRatioMinPos][j] * key;
                }

                constraintsRight[i] -= constraintsRight[miniRatioMinPos] * key;
            }
            for (int i = 0; i < COL; i++) { // цикл для проверки знака ячеек целевой строки
                flag = true;

                if (temp[i] < 0) {
                    flag = false;
                    break;
                }
            }
        }
    }
}