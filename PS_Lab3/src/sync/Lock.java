package sync;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Lock extends ReadWrite {
    private static final AtomicInteger finishOfWriting = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        long timeMillis = System.currentTimeMillis();

        final Buffer<Integer> buffer = new Buffer<>();
        final int writingCount = 3;
        final int writersCount = 5;
        final int readersCount = 5;

        ArrayList<Thread> threadsWriters = new ArrayList<>();

        for (int i = 0; i < writersCount; i++) {
            threadsWriters.add(new Thread(() -> {
                for (int j = 0; j < writingCount; ) {
                    if (buffer.writeInBuffer(j)) {
                        write(writingCount, j);
                        j++;
                    }
                }
                finishOfWriting.incrementAndGet();
            }));
            threadsWriters.get(i).join();
        }

        ArrayList<Thread> threadsReaders = new ArrayList<>();
        for (int i = 0; i < readersCount; i++) {
            threadsReaders.add(new Thread(() -> {
                while (finishOfWriting.get() < writersCount - 1) {
                    Integer current = buffer.readFromBuffer();
                    if (current != null) {
                        read(writingCount, current);
                    }
                }
            }));
            threadsReaders.get(i).join();
        }

        for (Thread thread : threadsWriters) thread.start();
        for (Thread thread : threadsReaders) thread.start();

        System.out.println("\nProgram completed in " + ((System.currentTimeMillis() - timeMillis)) + " milliseconds.");
    }
}
