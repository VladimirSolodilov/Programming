import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int K;

        System.out.print("Enter taskValue: ");
        int taskValue = scanner.nextInt();

        if (taskValue == 1) {
            System.out.print("Enter K = ");
            K = scanner.nextInt();
        } else K = 100;

        System.out.println("\nSequential:");
        for (int i = 10; i <= 100000; i = i * 10) {
            long time1 = System.nanoTime() / 1000;


            sequentialFunction(createStartVector(i), 5, K, taskValue);
            long time2 = System.nanoTime() / 1000 - time1;

            System.out.println("1 thread " + i + " vector = " + time2 + " us");
        }

        System.out.println("\nParallel:");
        for (int i = 10; i <= 100000; i = i * 10) {
            long time1 = System.nanoTime() / 1000;

            parallelFunction(createStartVector(i), 2, 5, K, taskValue);
            long time2 = System.nanoTime() / 1000 - time1;
            System.out.println("\n2 threads " + i + " vector = " + time2 + " us");

            parallelFunction(createStartVector(i), 3, 5, K, taskValue);
            long time3 = System.nanoTime() / 1000 - time1 - time2;
            System.out.println("3 threads " + i + " vector = " + time3 + " us");

            parallelFunction(createStartVector(i), 4, 5, K, taskValue);
            long time4 = System.nanoTime() / 1000 - time1 - time2 - time3;
            System.out.println("4 threads " + i + " vector = " + time4 + " us");

            parallelFunction(createStartVector(i), 5, 5, K, taskValue);
            long time5 = System.nanoTime() / 1000 - time1 - time2 -time3 - time4;
            System.out.println("5 threads " + i + " vector = " + time5 + " us");

            parallelFunction(createStartVector(i), 10, 5, K, taskValue);
            long time6 = System.nanoTime() / 1000 - time1 - time2 -time3 - time4 - time5;
            System.out.println("10 threads " + i + " vector = " + time6 + " us");
        }

        parallelCircleFunction(createStartVector(100), 2, 5);
    }

    public static double[] createStartVector(int N) {
        double[] startVector = new double[N];

        for (int i = 0; i < startVector.length; i++) {
            startVector[i] = i;
        }
        return startVector;
    }

    public static void sequentialFunction(double[] startVector, int C, int K, int taskValue) {
        //int K = 10000;
        double[] endVector = new double[startVector.length];

        for (int i = 0; i < endVector.length; i++) {
            switch (taskValue) {
                case 0 -> endVector[i] = Math.pow(startVector[i], C);
                case 1 -> {
                    for (int j = 0; j < K; j++) {
                        endVector[i] += Math.pow(startVector[i], C);
                    }
                }
                case 2 -> {
                    for (int j = 0; j < i; j++) {
                        endVector[i] += Math.pow(startVector[i], C);
                    }
                }
            }
        }
    }

    public static void parallelFunction(double[] startVector, int M, int C, int K, int taskValue) {
        double[] endVector = new double[startVector.length];
        AtomicInteger tempValue = new AtomicInteger();
        Object lock = new Object();

        Runnable runnable = () -> {
            synchronized (lock) {
                float temp = (float) endVector.length / M;

                for (int i = tempValue.get(); i < tempValue.get() + temp; i++) {
                    if (i >= endVector.length) break;
                    else {
                        switch (taskValue) {
                            case 0 -> endVector[i] = Math.pow(startVector[i], C);
                            case 1 -> {
                                for (int j = 0; j < K; j++) {
                                    endVector[i] += Math.pow(startVector[i], C);
                                }
                            }
                            case 2 -> {
                                for (int j = 0; j < i; j++) {
                                    endVector[i] += Math.pow(startVector[i], C);
                                }
                            }
                        }
                    }
                }
                tempValue.set(tempValue.get() + (int) Math.ceil(temp));
            }
        };

        Thread[] threads = new Thread[M];

        synchronized (lock) {
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(runnable);
                threads[i].start();
            }
        }
    }

    public static void parallelCircleFunction(double[] startVector, int M, int C) {
        double[] endVector = new double[startVector.length];
        AtomicInteger tempValue1 = new AtomicInteger();
        AtomicInteger tempValue2 = new AtomicInteger();
        Object lock = new Object();
        tempValue2.set(1);

        Runnable runnable1 = () -> {
            synchronized (lock) {
                int j = 0;
                int k = 0;
                //System.out.println("Even Thread");
                for (int i = tempValue1.get(); i < endVector.length; i++) {
                    if (i % 2 == 0) {
                        endVector[i] = Math.pow(startVector[i], C);
                        //System.out.println("EndVector[" + i + "] = " + endVector[i]);
                        j++;
                    }
                    k = i;
                    if (j == endVector.length / M) break;
                }
                tempValue1.set(tempValue1.get() + k + 2);
            }
        };

        Runnable runnable2 = () -> {
            synchronized (lock) {
                int j = 0;
                int k = 0;
                //System.out.println("Odd Thread");
                for (int i = tempValue2.get(); i < endVector.length; i++) {
                    if (i % 2 != 0) {
                        endVector[i] = Math.pow(startVector[i], C);
                        //System.out.println("EndVector[" + i + "] = " + endVector[i]);
                        j++;
                    }
                    k = i;
                    if (j == endVector.length / M) break;
                }
                tempValue2.set(tempValue2.get() + k + 2);
            }
        };

        Thread[] threads = new Thread[M];

        long time1 = System.nanoTime() / 1000;

        synchronized (lock) {
            for (int i = 0; i < threads.length; i++) {
                if (i % 2 == 0) {
                    threads[i] = new Thread(runnable1);
                    threads[i].start();
                } else {
                    threads[i] = new Thread(runnable2);
                    threads[i].start();
                }
            }
        }

        long time2 = System.nanoTime() / 1000 - time1;
        System.out.println("Circle Parallel 100 vector 2 threads = " + time2 + " us");
    }

}
