import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int writingsCount = 3;
        final int writersCount = 3;
        final int readersCount = 3;

        StringBuffer buf = new StringBuffer();

        StringBuilder stringBuilderWriters = new StringBuilder();
        StringBuilder stringBuilderReaders = new StringBuilder();

        ArrayList<Thread> threadsWriters = new ArrayList<>();
        for (int i = 0; i < writersCount; i++) {
            threadsWriters.add(new Thread(() -> {
                for (int j = 0; j < writingsCount; j++) {
                    buf.append("W");
                    stringBuilderWriters.append(Thread.currentThread().getName()).append("W - ").append(buf).append(";\n");
                }
            }));
        }

        ArrayList<Thread> threadsReaders = new ArrayList<>();
        for (int i = 0; i < readersCount; i++) {
            threadsReaders.add(new Thread(() -> {
                for (int j = 0; j < readersCount; j++) {
                    stringBuilderReaders.append(Thread.currentThread().getName()).append("R - ").append(buf).append(";\n");
                    buf.deleteCharAt(buf.length() - 1);
                }
            }));
        }

        for (Thread thread : threadsWriters) thread.start();
        for (Thread thread : threadsReaders) thread.start();

        System.out.println("\n" + stringBuilderWriters);
        System.out.println("\n" + stringBuilderReaders);
    }
}