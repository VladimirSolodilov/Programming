import java.util.Scanner;
public class NoParallelMain extends Actions {
    private static final Scanner scanner = new Scanner(System.in);
    record MatrixResult(double[][] endMatrix, double[] freeMatrix) {}
    public static MatrixResult matrixTransformation(double[][] matrix, double[] freeMatrix) {
        for (int k = 0; k < matrix.length; k++) {
            directMethodFunction(matrix, freeMatrix, k);
        }
        return new NoParallelMain.MatrixResult(matrix, freeMatrix);
    }

    public static void main(String[] args) {
        System.out.println("Решение СЛАУ методом Гаусса без распараллеливания вычислений...");
        System.out.print("Введите порядок СЛАУ: ");
        int length = scanner.nextInt();

        double[][] startMatrix = new double[length][length];
        double[] freeMatrix = new double[length];
        double[] x;
        long time = System.currentTimeMillis();

        createStartMatrices(length, startMatrix, freeMatrix);
        printMatrix(startMatrix, freeMatrix);

        MatrixResult matrixResult = matrixTransformation(startMatrix, freeMatrix);
        x = xCalculation(matrixResult.endMatrix(), matrixResult.freeMatrix());
        printX(x);

        System.out.println("Время, затраченное на выполнение программы - " + (System.currentTimeMillis() - time) + " мс");
    }
}