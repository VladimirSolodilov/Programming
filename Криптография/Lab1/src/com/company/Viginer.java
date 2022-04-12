package com.company;

public class Viginer extends Main {
    private String originalMessage = null;
    private String encryptedMessage = null;
    private String key = null;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String Encode(String message, String key) {
        StringBuilder result = new StringBuilder();
        int key_index = 0;

        for (char symbol : message.toCharArray()) {
            if (specialSymbols.contains(String.valueOf(symbol)))
                result.append(symbol);
            else {
                if (russianAlphabet.contains(String.valueOf(symbol))) {
                    key = key.toUpperCase();
                    int c = (russianAlphabet.indexOf(symbol) + russianAlphabet.indexOf(key.toCharArray()[key_index])) % russianAlphabet.length();
                    result.append(russianAlphabet.toCharArray()[c]);
                    key_index++;
                    if (key_index == key.length()) key_index = 0;
                } else if (russianAlphabet.toLowerCase().contains(String.valueOf(symbol))) {
                    key = key.toLowerCase();
                    int c = (russianAlphabet.toLowerCase().indexOf(symbol) + russianAlphabet.toLowerCase().indexOf(key.toCharArray()[key_index])) % russianAlphabet.length();
                    result.append(russianAlphabet.toLowerCase().toCharArray()[c]);
                    key_index++;
                    if ((key_index) == key.length()) key_index = 0;
                } else {
                    if (englishAlphabet.contains(String.valueOf(symbol))) {
                        key = key.toUpperCase();
                        int c = (englishAlphabet.indexOf(symbol) + englishAlphabet.indexOf(key.toCharArray()[key_index])) % englishAlphabet.length();
                        result.append(englishAlphabet.toCharArray()[c]);
                        key_index++;
                        if (key_index == key.length()) key_index = 0;
                    } else if (englishAlphabet.toLowerCase().contains(String.valueOf(symbol))) {
                        key = key.toLowerCase();
                        int c = (englishAlphabet.toLowerCase().indexOf(symbol) + englishAlphabet.toLowerCase().indexOf(key.toCharArray()[key_index])) % englishAlphabet.length();
                        result.append(englishAlphabet.toLowerCase().toCharArray()[c]);
                        key_index++;
                        if ((key_index) == key.length()) key_index = 0;
                    }
                }
            }
        }
        return result.toString();
    }

    public String Decode(String message, String key) {
        StringBuilder result = new StringBuilder();
        int key_index = 0;

        for (char symbol : message.toCharArray()) {
            if (specialSymbols.contains(String.valueOf(symbol)))
                result.append(symbol);
            else {
                if (russianAlphabet.contains(String.valueOf(symbol))) {
                    key = key.toUpperCase();
                    int c = (russianAlphabet.indexOf(symbol) + russianAlphabet.length() - russianAlphabet.indexOf(key.toCharArray()[key_index])) % russianAlphabet.length();
                    result.append(russianAlphabet.toCharArray()[c]);
                    key_index++;
                    if (key_index == key.length()) key_index = 0;
                } else if (russianAlphabet.toLowerCase().contains(String.valueOf(symbol))) {
                    key = key.toLowerCase();
                    int c = (russianAlphabet.toLowerCase().indexOf(symbol) + russianAlphabet.toLowerCase().length() - russianAlphabet.toLowerCase().indexOf(key.toCharArray()[key_index])) % russianAlphabet.length();
                    result.append(russianAlphabet.toLowerCase().toCharArray()[c]);
                    key_index++;
                    if ((key_index) == key.length()) key_index = 0;
                } else {
                    if (englishAlphabet.contains(String.valueOf(symbol))) {
                        key = key.toUpperCase();
                        int c = (englishAlphabet.indexOf(symbol) + englishAlphabet.length() - englishAlphabet.indexOf(key.toCharArray()[key_index])) % englishAlphabet.length();
                        result.append(englishAlphabet.toCharArray()[c]);
                        key_index++;
                        if (key_index == key.length()) key_index = 0;
                    } else if (englishAlphabet.toLowerCase().contains(String.valueOf(symbol))) {
                        key = key.toLowerCase();
                        int c = (englishAlphabet.toLowerCase().indexOf(symbol) + englishAlphabet.length() - englishAlphabet.toLowerCase().indexOf(key.toCharArray()[key_index])) % englishAlphabet.length();
                        result.append(englishAlphabet.toLowerCase().toCharArray()[c]);
                        key_index++;
                        if ((key_index) == key.length()) key_index = 0;
                    }
                }
            }
        }
        return result.toString();
    }
}

