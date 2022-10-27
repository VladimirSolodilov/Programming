import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("Введите число для проверки: ");
            int startValue = scanner.nextInt();
            if (startValue == 0) break;

            int[] startArray = createStartArray(startValue);
            int[] endArray = new int[0];

            while (true) {
                System.out.print("""
                Выберите метод обработки:
                1. Последовательный алгоритм
                2. Параллельный алгоритм: декомпозиция по данным
                3. Параллельный алгоритм: декомпозиция набора простых чисел
                4. Параллельный алгоритм: последовательный перебор простых чисел
                Введите соответствующее число:
                """);
                int chkValue = scanner.nextInt();
                long time1 = System.nanoTime() / 1000;

                if (chkValue == 0) break; else {
                    switch (chkValue) {
                        case 1 -> endArray = consistentAlgorithm(startArray, startArray.length);
                        case 2, 3, 4 -> endArray = parallelAlgorithm(startArray, 2, chkValue - 1);
                    }
                }

                for (int i = 0; i < endArray.length; i++) {
                    System.out.print(endArray[i] + " ");
                    if (endArray[i] == startValue) {
                        System.out.println("\nДанное число - простое");
                        break;
                    }
                    if (i == endArray.length - 1) System.out.println("\nДанное число - составное");
                }

                System.out.println("Время выполнения = " + (System.nanoTime() / 1000 - time1) + " мкс\n");
            }
        }
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

        int round = (int) Math.round(Math.sqrt(startArray.length));
        int[] basicArray = consistentAlgorithm(startArray, round);
        int[] modifiedArray = new int[startArray.length];

        atomicInteger.set(0);
        atomicInteger1.set(round - 2);
        atomicInteger2.set(0);

        Runnable task1 = () -> { //Декомпозиция по данным
            int length = (int) Math.ceil((double)(startArray.length - (round - 2)) / M);
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

        Runnable task2 = () -> { //Декомпозиция набора базовых простых чисел
            int length;
            if (M > basicArray.length) length = 1;
            else length = (basicArray.length) / M;
            for (int i = atomicInteger.get(); i <= atomicInteger.get() + length; i++) {
                if (i >= basicArray.length) break;
                for (int j = round - 2; j < startArray.length; j++) {
                    if (modifiedArray[j] == 1) continue;
                    if (startArray[j] % basicArray[i] == 0) {
                        modifiedArray[j] = 1;
                    } else modifiedArray[j] = 0;
                }
            }
            atomicInteger.set(atomicInteger.get() + length);
        };

        Runnable task3 = () -> { //Последовательный перебор
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
            switch (value) {
                case 1 -> {
                    threads[i] = new Thread(task1);
                    threads[i].start();
                    threads[i].join();
                }
                case 2 -> {
                    threads[i] = new Thread(task2);
                    threads[i].start();
                    threads[i].join();
                }
                case 3 -> {
                    threads[i] = new Thread(task3);
                    threads[i].start();

                    if (atomicInteger2.get() == basicArray.length) break;
                    else if ((i + 1) == threads.length) i = 0;
                }
            }
        }

        for (int i = 0; i <= (round - 2); i++) {
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