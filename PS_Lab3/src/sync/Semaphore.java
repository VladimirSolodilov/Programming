package sync;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore extends Operations {
    private static final AtomicInteger endWriting = new AtomicInteger(0);
    private static Integer buffer = null;
    private static final java.util.concurrent.Semaphore semaphoreRead = new java.util.concurrent.Semaphore(1, true);
    private static final java.util.concurrent.Semaphore semaphoreWrite = new java.util.concurrent.Semaphore(1 , true);

    public static void main(String[] args) {

        long millis = System.currentTimeMillis();
        final int writingCount = 100;
        final int writersCount = 100;
        final int readersCount = 100;

        ArrayList<Thread> threadsWriters = new ArrayList<>();
        for (int i = 0; i < writersCount; i++) {
            threadsWriters.add(new Thread(() -> {
                for (int j = 0; j < writingCount; j++) {
                    try {
                        semaphoreWrite.acquire();
                        if (buffer == null) {
                            buffer = j;
                            write(writingCount, j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphoreWrite.release();
                    }
                }
                endWriting.incrementAndGet();
            }));
        }

        ArrayList<Thread> threadsReaders = new ArrayList<>();
        for (int i = 0; i < readersCount; i++) {
            threadsReaders.add(new Thread(() -> {
                while (endWriting.get() < writersCount - 1) {
                    try {
                        semaphoreRead.acquire();
                        if (buffer != null){
                            int current = buffer;
                            buffer = null;
                            read(writingCount, current);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphoreRead.release();
                    }
                }
            }));
        }

        for (Thread thread : threadsWriters) {
            thread.start();
        }
        for (Thread thread : threadsReaders) {
            thread.start();
        }

        System.out.println("\nProgram continued " + ((System.currentTimeMillis() - millis)) + " milliseconds.");
    }
}
