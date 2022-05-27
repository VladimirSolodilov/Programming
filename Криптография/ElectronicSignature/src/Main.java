import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 	Генерация ключей
    public static int[] keysGenerator() {
        int[] simpleNumberList = new int[]{101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241,
                251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
                337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
                421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
                503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
                601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677,
                683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773,
                787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
                881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977,
                983, 991, 997};
        int[] listOfSimpleNumbers = new int[31];
        int p = simpleNumberList[(int)(Math.random() * 143)];
        int q = simpleNumberList[(int)(Math.random() * 143)];
        int n = p * q;
        int f = (p - 1) * (q - 1);

        for (int i = 0, c = 0; i < 143; i++) {
            if (c == 31)
                break;
            if (NOD(simpleNumberList[i], f) == 1) {
                listOfSimpleNumbers[c] = simpleNumberList[i];
                c++;
            }
        }

        int e = listOfSimpleNumbers[(int)(Math.random() * 31)];
        int d = 1;

        while (((d * e) % f) != 1) {
            d++;
        }

        return new int[] {e, d, n};
    }

    // 	Шифрование
    public static int[] encryption(String source, int[] key) {
        int[] result = new int[source.length()];
        int tempValue;

        for (int i = 0; i < source.length(); i++) {
            tempValue = source.toCharArray()[i];
            for (int j = 0; j < key[0] - 1; j++) {
                tempValue = (tempValue * source.charAt(i)) % (key[1]);
            }
            result[i] = tempValue;
        }

        return result;
    }

    // 	Расшифрование
    public static String decryption(int[] ciphertext, int[] key) {
        StringBuilder result = new StringBuilder();
        long tempValue;

        for (int k : ciphertext) {
            tempValue = k;
            for (int j = 0; j < key[0] - 1; j++) {
                tempValue = (tempValue * k) % key[1];
            }
            result.append((char) tempValue);
        }

        return result.toString();
    }

    //	Алгоритм Евклида (поиск НОД)
    public static int NOD (int a, int b) {
        while (b != 0) {
            int tempValue = a % b;
            a = b;
            b = tempValue;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sourceText = "";
        String inputNewText;

        int[] key1 = new int[2];
        int[] key2 = new int[2];
        int[] numToText = new int[0];
        int[] keys;

        boolean menuChk = true;
        while (true) {
            try {
                System.out.println("\nИсходное сообщение: " + sourceText);
                if (menuChk) {
                    System.out.println(".....Меню программы.....\n" +
                            "1 - Ввести новое сообщение\n2 - Получить подпись");
                } else {
                    System.out.print("Преобразованное сообщение (подпись): ");
                    System.out.println(Arrays.toString(numToText));
                    System.out.println("""
                            1 - Ввести новый текст
                            3 - Расшифровать сообщение""");
                }

                System.out.print("4 - Завершить работу\n" +
                        "Для продолжения введите соответствующую цифру: ");

                int menuValue = scanner.nextInt();
                if (menuValue <= 0 || menuValue > 4) {
                    while (menuValue <= 0 || menuValue > 4) {
                        System.out.print("Введите корректное значение: ");
                        menuValue = scanner.nextInt();
                    }
                }

                switch (menuValue) {
                    case (1) -> {
                        System.out.print("Введите новое сообщение: ");
                        scanner.nextLine();
                        inputNewText = scanner.nextLine();

                        if (inputNewText.length() == 0) {
                            while (inputNewText.length() == 0) {
                                System.out.println("Введите сообщение ещё раз: ");
                                inputNewText = scanner.nextLine();
                            }
                        }

                        sourceText = inputNewText;
                        menuChk = true;
                    }
                    case (2) -> {
                        keys = keysGenerator();
                        key1[0] = keys[0];
                        key1[1] = keys[2];
                        key2[0] = keys[1];
                        key2[1] = keys[2];

                        System.out.println("Закрытый ключ: " + Arrays.toString(key2));

                        numToText = encryption(sourceText, key2);
                        System.out.println("Цифровая подпись: ");
                        System.out.println(Arrays.toString(numToText));

                        menuChk = false;
                    }
                    case (3) -> {
                        System.out.print("Шифртекст: ");
                        System.out.println(Arrays.toString(numToText));
                        System.out.println("\Открытый ключ: " + Arrays.toString(key1));

                        sourceText = decryption(numToText, key1);
                        System.out.println("Исходный текст:" + sourceText);

                        menuChk = true;
                    }
                    case (4) -> {
                        scanner.close();
                        System.exit(0);
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Исключение! Перезапуск программы...");
                main(new String[]{"main"});
            }
        }

    }
}
