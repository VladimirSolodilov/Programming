import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double[] startVector = {2, 3, 5};
        double[] endVector = parallelFunction(startVector, 2, 3);

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

    public static void parallelFunction(double[] startVector, int M, int C) {
        double[] endVector = new double[startVector.length];

        Runnable runnable = () -> {
            
        };

        for (int i = 0; i < M; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
