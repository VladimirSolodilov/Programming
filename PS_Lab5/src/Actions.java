public class Actions {
    static void directMethodFunction(double[][] startMatrix, double[] freeMatrix, int k) {
        for (int j = k + 1; j < startMatrix.length; j++) {
            double d = startMatrix[j][k] / startMatrix[k][k];
            for (int i = k; i < startMatrix.length; i++) {
                startMatrix[j][i] = startMatrix[j][i] - d * startMatrix[k][i];
            }
            freeMatrix[j] = freeMatrix[j] - d * freeMatrix[k];
        }
    }
    public static double[] xCalculation(double[][] matrix, double[] freeMatrix) {
        double d, s;
        double[] x = new double[matrix.length];
        for (int k = matrix.length - 1; k >= 0; k--) {
            d = 0;
            for (int j = k + 1; j < matrix.length; j++) {
                s = matrix[k][j] * x[j];
                d = d + s;
            }
            x[k] = (freeMatrix[k] - d) / matrix[k][k];
        }
        return x;
    }

    static void createStartMatrices(int length, double[][] startMatrix, double[] freeMatrix) {
        for (int i = 0; i < length; i++) {
            freeMatrix[i] = (int) (Math.random() * 10);
            for (int j = 0; j < length; j++) {
                startMatrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    static void printMatrix(double[][] startMatrix, double[] freeMatrix) {
        System.out.println("Вывод элементов расширенной матрицы:");
        for (int i = 0; i < startMatrix.length; i++) {
            for (int j = 0; j < startMatrix.length; j++) {
                System.out.print(startMatrix[i][j] + " ");
            }
            System.out.println("| " + freeMatrix[i]);
        }
    }

    static void printX(double[] x) {
        System.out.println("Вывод значений x:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }
    }
}
