package sync;

public class ReadWrite {
    public static void read(int nWritings, int current) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("\t").append(Thread.currentThread().getName()).append("R - ");
        for (int k = 0; k < nWritings; k++) {
            tmp.append(k > current ? "_" : "R");
        }
        System.out.println(tmp);
    }

    public static void write(int nWritings, int current) {
        StringBuilder tmp = new StringBuilder();
        tmp.append(Thread.currentThread().getName()).append("W - ");
        for (int k = 0; k < nWritings; k++) {
            tmp.append(k > current ? "_" : "W");
        }
        System.out.println(tmp);
    }

}
