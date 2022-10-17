import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        consistentFunction();
    }

    public static double[] createPrimaryVector(int N) {
        double[] a = new double[N];
        for (int i = 0; i < a.length; i++) {
            System.out.print("Enter a[" + i + "] = ");
            a[i] = scanner.nextDouble();
        }

        return a;
    }

    public static void run(Object data) {

    }

    public static void parallelFunction() {
        System.out.print("Enter N: ");
        int N = scanner.nextInt();

        System.out.print("Enter M");
        int M = scanner.nextInt();

        System.out.print("Enter c: ");
        double c = scanner.nextDouble();

        double[] startVector = createPrimaryVector(N);




    }

    public static void consistentFunction() {
        System.out.print("Enter N: ");
        int N = scanner.nextInt();

        double[] primaryVector = createPrimaryVector(N);

        System.out.print("Enter c: ");
        double c = scanner.nextDouble();

        double[] endVector = new double[N];
        System.out.print("End vector: ");
        for (int i = 0; i < primaryVector.length; i++) {
            endVector[i] = primaryVector[i] * c;
            System.out.print(endVector[i] + " ");
        }
    }

    public static void run() {
    }
}