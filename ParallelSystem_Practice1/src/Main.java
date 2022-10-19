import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nSequential vector: \n");

        for (int i = 10; i <= 100000; i = i * 10) {
            long time1 = System.nanoTime();

            double[] endVectorSequential1 = sequentialFunction(createStartVector(i), 5);
            long time2 = System.nanoTime() - time1;


            System.out.println("1 thread " + i + " vector = " + time2 + " ns");
        }

        System.out.println("\nParallel Vector: ");
        for (int i = 10; i <= 100000; i = i * 10) {
            long time1 = System.nanoTime();

            double[] endVectorParallel2 = parallelFunction(createStartVector(i), 2, 5);
            long time2 = System.nanoTime() - time1;

            double[] endVectorParallel3 = parallelFunction(createStartVector(i), 3, 5);
            long time3 = System.nanoTime() - time1 - time2;

            double[] endVectorParallel4 = parallelFunction(createStartVector(i), 4, 5);
            long time4 = System.nanoTime() - time1 - time2 - time3;

            double[] endVectorParallel5 = parallelFunction(createStartVector(i), 5, 5);
            long time5 = System.nanoTime() - time1 - time2 -time3 - time4;

            double[] endVectorParallel10 = parallelFunction(createStartVector(i), 10, 5);
            long time6 = System.nanoTime() - time1 - time2 -time3 - time4 - time5;

            System.out.println("\n2 threads " + i + " vector = " + time2 + " ns");
            System.out.println("3 threads " + i + " vector = " + time3 + " ns");
            System.out.println("4 threads " + i + " vector = " + time4 + " ns");
            System.out.println("5 threads " + i + " vector = " + time5 + " ns");
            System.out.println("10 threads " + i + " vector = " + time6 + " ns");
        }
    }

    public static double[] createStartVector(int N) {
        double[] startVector = new double[N];

        for (int i = 0; i < startVector.length; i++) {
            startVector[i] = i;
        }

        return startVector;
    }

    public static double[] sequentialFunction(double[] startVector, int C) {
        int K = 100;
        double[] endVector = new double[startVector.length];

        for (int i = 0; i < endVector.length; i++) {
            endVector[i] = Math.pow(startVector[i], C);
        }

        return endVector;
    }

    public static double[] parallelFunction(double[] startVector, int M, int C) throws InterruptedException {
        double[] endVector = new double[startVector.length];
        AtomicInteger tempValue = new AtomicInteger();

        Runnable runnable = () -> {
            int K = 100;

            float temp = (float) endVector.length / M;

            for (int i = tempValue.get(); i < tempValue.get() + temp; i++) {
                if (i == endVector.length) break;
                else {
                    for (int j = 0; j < K; j++) {
                        endVector[i] += Math.pow(startVector[i], C);
                    }

                    /*for (int j = 0; j < i; j++) {
                        endVector[i] += Math.pow(startVector[i], C);
                    }*/

                    //endVector[i] = Math.pow(startVector[i], C);
                }
            }

            tempValue.set(tempValue.get() + (int) Math.ceil(temp));
        };

        Thread[] threads = new Thread[M];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
            threads[i].join();
        }

        return endVector;
    }
}
