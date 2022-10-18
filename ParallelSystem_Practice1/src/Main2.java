import java.util.Scanner;

public class Main2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double[] startVector = new double[2];
        int M = 2, N = 3;
        parallelFunction(startVector, M, N);
    }

    public static double[] createStartVector(int N) {
        double[] startVector = new double[N];
        for (int i = 0; i < startVector.length; i++) {
            System.out.println("Enter startVector[" + i + "] = ");
            startVector[i] = scanner.nextDouble();
        }
        return startVector;
    }

    public static double[] sequentialFunction(double[] startVector, int C) {
        double[] endVector = new double[startVector.length];
        for (int i = 0; i < endVector.length; i++) {
            endVector[i] = Math.pow(startVector[i], C);
            System.out.print(endVector[i] + " ");
        }
        return endVector;
    }

    public static double[] parallelFunction(double[] startVector, int M, int C) {
        double[] endVector = new double[startVector.length];

        Runnable task = () -> {

        };

        for (int i = 0; i < M; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        return endVector;
    }
}
