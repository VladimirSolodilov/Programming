import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int N = 225;
        int[] startArray = createStartArray(N);
        int[] consistentArray = consistentAlgorithm(startArray, N);
        long time1 = System.nanoTime() / 1000;

        System.out.println("Последовательный алгоритм...");
        for (int j : consistentArray) {
            System.out.print(j + " ");
        }

        long time2 = System.nanoTime() / 1000 - time1;
        System.out.println("\nВремя = " + time2 + " мкс");

        System.out.println();

        System.out.println("Параллельные алгоритмы...");

        int[] modifiedArray1 = parallelAlgorithm(startArray, 2, 1);

        for (int j : modifiedArray1) {
            System.out.print(j + " ");
        }

        long time3 = System.nanoTime() / 1000 - time1 - time2;
        System.out.println("\nВремя = " + time3 + " мкс");

        System.out.println();

        int[] modifiedArray2 = parallelAlgorithm(startArray, 2, 2);

        for (int j : modifiedArray2) {
            System.out.print(j + " ");
        }

        long time4 = System.nanoTime() / 1000 - time1 - time2 - time3;
        System.out.println("\nВремя = " + time4 + " мкс");

        System.out.println();

        int[] modifiedArray3 = parallelAlgorithm(startArray, 2, 3);

        for (int j : modifiedArray3) {
            System.out.print(j + " ");
        }

        long time5 = System.nanoTime() / 1000 - time1 - time2 - time3 - time4;
        System.out.println("\nВремя = " + time5 + " мкс");

    }

    public static int[] createStartArray(int N) {
        int[] startArray = new int[N];
        for (int i = 0; i < startArray.length; i++) {
            startArray[i] = i + 2;
        }
        return startArray;
    }

    public static int[] convertArray(int[] startArray, int[] modifiedArray) {
        int[] tempArray = new int[modifiedArray.length];

        int j = 0;
        for (int i = 0; i < modifiedArray.length; i++) {
            if (modifiedArray[i] == 0) {
                tempArray[j] = startArray[i];
                j++;
            }
        }

        int[] endArray = new int[j];
        System.arraycopy(tempArray, 0, endArray, 0, j);

        return endArray;
    }

    public static int[] consistentAlgorithm(int[] startArray, int N) {
        int[] sieveArray = new int[N];

        for (int i = 2; i <= (int) Math.round(Math.sqrt(N)); i++) {
            if (sieveArray[i - 2] == 1) continue;

            for (int j = 0; j < sieveArray.length; j++) {
                if (sieveArray[j] == 1) continue;

                if (startArray[j] == i) sieveArray[j] = 0;
                else if ((startArray[j] % i) == 0) {
                    sieveArray[j] = 1;
                } else sieveArray[j] = 0;
            }
        }

        return convertArray(startArray, sieveArray);
    }

    public static int[] parallelAlgorithm(int[] startArray, int M, int value) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicInteger atomicInteger1 = new AtomicInteger();
        AtomicInteger atomicInteger2 = new AtomicInteger();
        Object lock = new Object();

        int floor = (int) Math.round(Math.sqrt(startArray.length));
        int[] basicArray = consistentAlgorithm(startArray, floor);
        int[] modifiedArray = new int[startArray.length];

        atomicInteger.set(0);
        atomicInteger1.set(floor - 2);
        atomicInteger2.set(0);

        Runnable task = () -> {
            int length = (basicArray.length) / M;
            for (int i = atomicInteger.get(); i <= atomicInteger.get() + length; i++) {
                if (i >= basicArray.length) break;
                for (int j = floor - 2; j < startArray.length; j++) {
                    if (modifiedArray[j] == 1) continue;
                    if (startArray[j] % basicArray[i] == 0) {
                        modifiedArray[j] = 1;
                    } else modifiedArray[j] = 0;
                }
            }
            atomicInteger.set(atomicInteger.get() + length);
        };

        Runnable task1 = () -> {
            int length = (startArray.length - (floor - 2)) / M;
            for (int i = atomicInteger1.get(); i <= atomicInteger1.get() + length; i++) {
                if (i >= startArray.length) break;
                for (int k : basicArray) {
                    if (startArray[i] % k == 0) {
                        modifiedArray[i] = 1;
                        break;
                    } else modifiedArray[i] = 0;
                }
            }
            atomicInteger1.set(atomicInteger1.get() + length);
        };

        Runnable task2 = () -> {
            synchronized (lock) {
                for (int i = atomicInteger2.get(); i <= atomicInteger2.get() + 1; i++) {
                    if (i >= basicArray.length) break;
                    else for (int j = 0; j < startArray.length; j++) {
                        if (modifiedArray[j] == 1) continue;
                        if (startArray[j] % basicArray[i] == 0) {
                            modifiedArray[j] = 1;
                        } else modifiedArray[j] = 0;
                    }
                }
                atomicInteger2.set(atomicInteger2.get() + 1);
            }
        };

        Thread[] threads = new Thread[M];

        for (int i = 0; i < threads.length; i++) {
            if (value == 1) {
                threads[i] = new Thread(task);
                threads[i].start();
                threads[i].join();
            } else if (value == 2) {
                threads[i] = new Thread(task1);
                threads[i].start();
                threads[i].join();
            } else if (value == 3) {
                threads[i] = new Thread(task2);
                threads[i].start();
                if (atomicInteger2.get() == basicArray.length) break;
                else if (i == threads.length - 1) i = -1;
            }
        }

        for (int i = 0; i <= (floor - 2); i++) {
            for (int k : basicArray) {
                if (startArray[i] == k) {
                    modifiedArray[i] = 0;
                    break;
                } else modifiedArray[i] = 1;
            }
        }

        return convertArray(startArray, modifiedArray);
    }
}