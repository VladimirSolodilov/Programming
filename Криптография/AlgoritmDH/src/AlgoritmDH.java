import java.util.HashMap;
import java.util.Map;

public class AlgoritmDH {
    private Map<Integer, Character> numbers = new HashMap<>();
    private int publicKey;
    private int privateKey;
    private int partialKey;
    private int fullKey;

    public static int setKey() {
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

        return simpleNumberList[(int) (Math.random() * 143)];
    }
    public int generatePartionKey(int publicKey1, int publicKey2, int privateKey) {
        int partialKey = (int) Math.pow(publicKey1, privateKey);
        partialKey = partialKey % publicKey2;
        return partialKey;
    }

    public int generateFullKey(int partialKey, int publicKey2, int privateKey) {
        int fullKey = (int) Math.pow(partialKey, privateKey);
        fullKey = fullKey % publicKey2;
        return fullKey;
    }

    public String encryptMessage(String sourceMessage, int fullKey) {
        StringBuilder resultMessage = new StringBuilder();

        for (int i = 0; i < sourceMessage.length(); i++) {
            int subChar = Character.getNumericValue(sourceMessage.charAt(i));
            numbers.put(subChar, sourceMessage.toCharArray()[i]);
            subChar += fullKey;
            resultMessage.append(subChar).append(" ");
        }

        setNumbers(numbers);
        return resultMessage.toString();
    }

    public String decryptMessage(String encryptMessage, int fullKey, Map<Integer, Character> numbers) {
        StringBuilder sourceMessage = new StringBuilder();
        String[] subStr = encryptMessage.split(" ");

        for (String s : subStr) {
            int tempValue = Integer.parseInt(s) - fullKey;
            sourceMessage.append(numbers.get(tempValue));
        }

        return sourceMessage.toString();
    }
    public Map<Integer, Character> getNumbers() {
        return numbers;
    }

    public void setNumbers(Map<Integer, Character> numbers) {
        this.numbers = numbers;
    }

    public AlgoritmDH() {

    }
    public int getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(int publicKey) {
        this.publicKey = publicKey;
    }

    public int getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(int privateKey) {
        this.privateKey = privateKey;
    }

    public int getPartialKey() {
        return partialKey;
    }

    public void setPartialKey(int partialKey) {
        this.partialKey = partialKey;
    }

    public int getFullKey() {
        return fullKey;
    }

    public void setFullKey(int fullKey) {
        this.fullKey = fullKey;
    }
}
