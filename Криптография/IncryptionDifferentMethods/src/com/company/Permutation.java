package com.company;

public class Permutation extends Main {
    private int[] key = null;
    private String originalMessage = "";
    private String encryptedMessage = "";

    public void setKey(int[] key) {
        this.key = new int[key.length];
        System.arraycopy(key, 0, this.key, 0, key.length);
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public String Encode(String message) {
        StringBuilder result = new StringBuilder();

        StringBuilder messageBuilder = new StringBuilder(message);
        for (int i = 0; i < messageBuilder.length() % key.length; i++) {
            messageBuilder.append(messageBuilder.toString().toCharArray()[i]);
        }
        message = messageBuilder.toString();

        for (int i = 0; i < message.length(); i += key.length) {
            char[] chr = new char[key.length];

            for (int j = 0; j < key.length; j++) {
                chr[key[j] - 1] = message.toCharArray()[i + j];
            }

            for (int j = 0; j < key.length; j++) {
                result.append(chr[j]);
            }
        }

        return result.toString();
    }

    public String Decode(String message) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i += key.length)
        {
            char[] chr = new char[key.length];

            for (int j = 0; j < key.length; j++)
                chr[j] = message.toCharArray()[i + key[j] - 1];

            for (int j = 0; j < key.length; j++)
                result.append(chr[j]);
        }

        return result.toString();
    }

}
