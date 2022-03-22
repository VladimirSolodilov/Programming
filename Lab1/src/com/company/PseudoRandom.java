package com.company;

import java.util.Random;

public class PseudoRandom extends Viginer {
    StringBuilder pseudoRandomKey = new StringBuilder();

    public StringBuilder getPseudoRandomKey() {
        return pseudoRandomKey;
    }

    public void setPseudoRandomKey(int length, int startSeed, String choice) {
        pseudoRandomKey.delete(0, pseudoRandomKey.length());
        Random random = new Random(startSeed);
        if (choice.equals("russian")) {
            for (int i = 0; i < length; i++) {
                pseudoRandomKey.append(russianAlphabet.toCharArray()[random.nextInt(0, russianAlphabet.length())]);
            }
        } else {
            if (choice.equals("english")) {
                for (int i = 0; i < length; i++) {
                    pseudoRandomKey.append(englishAlphabet.toCharArray()[random.nextInt(0, englishAlphabet.length())]);
                }
            }
        }
    }
}
