package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Homophonic extends Main {
    protected Map<Character, String> numbers = new HashMap<>();

    public void Encode(String inputStr) {
        StringBuilder resultStr = new StringBuilder();
        String[] tempStr = new String[russianAlphabet.length()];

        double[] propability = new double[russianAlphabet.length()];
        int[] count = new int[russianAlphabet.length() + specialSymbols.length()];
        int countAll = 0;
        int i = 0;

        for (char bet: russianAlphabet.toUpperCase().toCharArray()) {
           for (char symbol: inputStr.toUpperCase().toCharArray()) {
                if (symbol == bet) {
                    count[i]++;
                }
           }
           countAll += count[i];
           i++;
        }

        for (int j = 0; j < russianAlphabet.length(); j++) {
            propability[j] = (double) count[j] / countAll;
            if (propability[j] != 0) {
                if (propability[j] <= 0.2) {
                    tempStr[j] = (int) (Math.random() * 1000) + " ";
                } else if (propability[j] <= 0.6) {
                    tempStr[j] = (int) (Math.random() * 1000) + " ";
                    tempStr[j] += (int) (Math.random() * 1000) + " ";
                } else {
                    tempStr[j] = (int) (Math.random() * 1000) + " ";
                    tempStr[j] += (int) (Math.random() * 1000) + " ";
                    tempStr[j] += (int) (Math.random() * 1000) + " ";
                }
            }
            numbers.put(russianAlphabet.toCharArray()[j], tempStr[j]);
        }

        System.out.println("\nТаблица замены: ");
        for (char symbol : russianAlphabet.toUpperCase().toCharArray()) {
            if (numbers.get(symbol) != null) {
                System.out.println(symbol + " = " + numbers.get(symbol));
            }
        }

        for (char symbol: resultStr.toString().toCharArray()) {
            System.out.println(symbol);
        }

        for (char symbol : inputStr.toUpperCase().toCharArray()) {
            if (specialSymbols.contains(String.valueOf(symbol))) {
                resultStr.append("");
            } else resultStr.append(numbers.get(symbol));
        }

        System.out.println("\nЗашифрованный текст: " + resultStr);
    }

    public void Decode(String inputStr) {
        String[] tempNumbers = inputStr.split(" ");
        StringBuilder resultStr = new StringBuilder();
        String tempStr = "";
        Set<Map.Entry<Character, String>> entries = numbers.entrySet();

        for (String tempNumber : tempNumbers) {
            tempStr += tempNumber + " ";
            for (Map.Entry<Character, String> entry : entries) {
                if (entry.getValue() != null) {
                    if (entry.getValue().equals(tempStr)) {
                        resultStr.append(entry.getKey());
                        tempStr = "";
                    }
                }
            }
        }

        System.out.println("\nЗашифрованный текст: " + resultStr);
    }
}
