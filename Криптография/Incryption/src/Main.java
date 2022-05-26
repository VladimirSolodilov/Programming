import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String stringBuffer;

        String sourceText = "Hello world!";
        int[] numText = new int[0];

        int[] keys;
        int[] privateKey = new int[2];
        int[] publicKey = new int[2];

        boolean menuFlag = true;

        while (true) {
            try {
                if(menuFlag) {
                    System.out.println("Исходный текст:\n" + sourceText + "\n\n1 - Ввести новый текст\n2 - Зашифровать");
                }
                else {
                    System.out.println("Исходный текст:");
                    System.out.println(Arrays.toString(numText));
                    System.out.println("\n1 - Ввести новый текст\n3 - Расшифровать");
                }
                System.out.println("0 - Завершить работу\n");

                String menuValue = scan.nextLine();
                if(menuValue.charAt(0) < 48 || menuValue.charAt(0) > 51 || menuValue.length() == 0) {
                    System.out.println("Введите корректное значение!\n");
                    continue;
                }
                if(menuFlag && menuValue.charAt(0) == 51 || !menuFlag && menuValue.charAt(0) == 50) {
                    System.out.println("Введите корректное значение!\n");
                    continue;
                }
                switch (menuValue) {
                    case ("1") -> {
                        System.out.println("Введите новый текст:");
                        stringBuffer = scan.nextLine();
                        if (stringBuffer.length() == 0) {
                            System.out.println("Нельзя оставлять поле пустым!\n");
                            break;
                        }
                        sourceText = stringBuffer;
                        menuFlag = true;
                    }
                    case ("2") -> {
                        System.out.println("Исходный текст:\n" + sourceText);
                        keys = keysGenerator();
                        privateKey[0] = keys[0];
                        privateKey[1] = keys[2];
                        publicKey[0] = keys[1];
                        publicKey[1] = keys[2];
                        System.out.println("Открытый ключ:\n" + Arrays.toString(publicKey));
                        numText = incryption(sourceText, publicKey);
                        System.out.println("Результат шифрования:");
                        System.out.println(Arrays.toString(numText));
                        menuFlag = false;
                    }
                    case ("3") -> {
                        System.out.println("Шифртекст:");
                        System.out.println(Arrays.toString(numText));
                        System.out.println("Закрытый ключ:\n" + Arrays.toString(privateKey));
                        sourceText = decryption(numText, privateKey);
                        System.out.println("Результат расшифрования:\n" + sourceText + "\n");
                        menuFlag = true;
                    }
                    case ("0") -> {
                        scan.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Введите корректное значение\n");
                }
            }
            catch(Exception e) {
                System.out.println("Введите корректное значение\n");
            }
        }

    }

//	Алгоритм Евклида (поиск НОД)

    public static int gcd (int a,int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

// 	Генерация ключей

    public static int[] keysGenerator() {
        int[] primeNumList = {37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241,
                251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
                337, 347, 349, 353,	359, 367, 373, 379, 383, 389, 397, 401, 409, 419,
                421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
                503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
                601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677,
                683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773,
                787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
                881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977,
                983, 991, 997};

        int[] listMPN = new int[31];

        int p = primeNumList[(int)(Math.random() * 157)];
        int q = primeNumList[(int)(Math.random() * 157)];

        int n = p * q;
        int f = (p - 1) * (q - 1);

        for(int i = 0, c = 0; i < 157; i++) {
            if(c == 31)
                break;
            if(gcd(primeNumList[i], f) == 1) {
                listMPN[c] = primeNumList[i];
                c++;
            }
        }

        int e = listMPN[(int)(Math.random() * 31)];

        int d = 1;
        while (((d * e) % f) != 1) {
            d++;
        }

        return new int[]{e, d, n};
    }

// 	Шифрование

    public static int[] incryption(String source, int[] key) {
        int[] result = new int[source.length()];
        int charBuffer;

        for(int i = 0; i < source.length(); i++) {
            charBuffer = source.charAt(i);
            for(int j = 0; j < key[0] - 1; j++) {
                charBuffer = (charBuffer * source.charAt(i)) % key[1];
            }
            result[i] = charBuffer;
        }

        return result;
    }


// 	Расшифрование

    public static String decryption(int[] ciphertext, int[] key) {
        StringBuilder result = new StringBuilder();
        long charBuffer;

        for (int k : ciphertext) {
            charBuffer = k;
            for (int j = 0; j < key[0] - 1; j++) {
                charBuffer = (charBuffer * k) % key[1];
            }
            result.append((char) charBuffer);
        }

        return result.toString();
    }
}
