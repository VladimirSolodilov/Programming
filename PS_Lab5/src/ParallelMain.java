import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelMain extends Actions {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Решение СЛАУ методом Гаусса с распараллеливанием вычислений...");
        System.out.print("Введите порядок СЛАУ: ");
        int length = scanner.nextInt();

        AtomicInteger directMethodValue = new AtomicInteger(0);
        AtomicInteger reverseMethodValue = new AtomicInteger(length - 1);
        Lock lock = new ReentrantLock();

        int cores = Runtime.getRuntime().availableProcessors();
        double[][] startMatrix = new double[length][length];
        double[] freeMatrix = new double[length];
        double[] x = new double[length];
        double n =  Math.ceil((double) length / cores);

        createStartMatrices(length, startMatrix, freeMatrix);
        long time = System.currentTimeMillis();

        Runnable directMethod = () -> {
            try {
                if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    for (int k = directMethodValue.get(); k < directMethodValue.get() + n; k++) {
                        if (k >= length) {
                            break;
                        } else {
                            directMethodFunction(startMatrix, freeMatrix, k);
                        }
                        directMethodValue.incrementAndGet();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        };

        Runnable reverseMethod = () -> {
            try {
                if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    for (int k = reverseMethodValue.get(); k >= reverseMethodValue.get() - n; k--) {
                        if (reverseMethodValue.get() < 0) {
                            break;
                        } else {
                            double d = 0;
                            for (int j = k + 1; j < startMatrix.length; j++) {
                                double s = startMatrix[k][j] * x[j];
                                d += s;
                            }
                            x[k] = (freeMatrix[k] - d) / startMatrix[k][k];
                            reverseMethodValue.decrementAndGet();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        };

        createStartMatrices(length, startMatrix, freeMatrix);

        printMatrix(startMatrix, freeMatrix);

        Thread[] threadsDirectMethod = new Thread[cores];
        for (int i = 0; i < threadsDirectMethod.length; i++) {
            threadsDirectMethod[i] = new Thread(directMethod);
            threadsDirectMethod[i].start();
            threadsDirectMethod[i].join();
        }

        Thread[] threadsReverseMethod = new Thread[cores];
        for (int i = 0; i < threadsDirectMethod.length; i++) {
            threadsReverseMethod[i] = new Thread(reverseMethod);
            threadsReverseMethod[i].start();
            threadsReverseMethod[i].join();
        }

        printX(x);

        System.out.println("Время, затраченное на выполнение программы - " + (System.currentTimeMillis() - time) + " мс");

        System.exit(0);
    }
}
