public class Main {
    public static void main(String[] args) {
        int N = 80;
        int[] startArray = createStartArray(N);
        int[] consistentArray = consistentAlgorithm(startArray, N);

        System.out.println("Последовательный алгоритм...");
        for (int i = 0; i < consistentArray.length; i++) {
            System.out.print(consistentArray[i] + " ");
        }

        System.out.println();

        int[] arr2 = modifiedConsistentAlgorithm(startArray, 105);
    }

    public static int[] createStartArray(int N) {
        int[] startArray = new int[N];

        for (int i = 0; i < startArray.length; i++) {
            startArray[i] = i + 2;
        }

        return startArray;
    }

    public static int[] consistentAlgorithm(int[] startArray, int N) {
        int[] sieveArray = new int[N - 1];
        int[] tempArray = new int[sieveArray.length];

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (sieveArray[i - 2] == 1) continue;

            for (int j = 0; j < sieveArray.length; j++) {
                if (sieveArray[j] == 1) continue;

                if (startArray[j] == i) sieveArray[j] = 0;
                    else if ((startArray[j] % i) == 0) {
                        sieveArray[j] = 1;
                    } else sieveArray[j] = 0;
            }
        }

        int j = 0;
        for (int i = 0; i < sieveArray.length; i++) {
            if (sieveArray[i] == 0) {
                tempArray[j] = startArray[i];
                j++;
            }
        }

        int[] endArray = new int[j];
        System.arraycopy(tempArray, 0, endArray, 0, j);

        return endArray;
    }

    public static int[] modifiedConsistentAlgorithm(int[] startArray, int N) {
        double floor = Math.floor(Math.sqrt(startArray.length));
        int[] basicArray = consistentAlgorithm(startArray, (int) floor);
        int[] tempArray = new int[startArray.length];

        for (int i = 0; i < basicArray.length; i++) {
            System.out.println("Basic Arr[" + i + "] = " + basicArray[i]);
        }

        Runnable runnable = () -> {
            for (int i = (int) (floor - 2); i < startArray.length; i++) {
                //if (startArray[i] % )
            }
        };

        Thread[] threads = new Thread[2];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        return new int[] {1};
    }

}