package com.company;

public class Permutation extends Main {
    private int[] key = null;

    public void setKey(int[] key) {
        this.key = new int[key.length];

        for (int i = 0; i < key.length; i++) {
            this.key[i] = key[i];
        }
    }

    public String Encode(String message) {
        String result = new String();

        for (int i = 0; i < message.length() % key.length; i++) {
            message += message.toCharArray()[i];
        }

        for (int i = 0; i < message.length(); i += key.length) {
            char[] chr = new char[key.length];

            for (int j = 0; j < key.length; j++) {
                chr[key[j] - 1] = message.toCharArray()[i + j];
            }

            for (int j = 0; j < key.length; j++) {
                result += chr[j];
            }
        }

        return result;
    }

    public String Decode(String message) {
        String result = new String();

        for (int i = 0; i < message.length(); i += key.length)
        {
            char[] transposition = new char[key.length];

            for (int j = 0; j < key.length; j++)
                transposition[j] = message.toCharArray()[i + key[j] - 1];

            for (int j = 0; j < key.length; j++)
                result += transposition[j];
        }

        return result;
    }

}
