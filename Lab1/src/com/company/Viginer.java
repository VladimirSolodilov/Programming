package com.company;

public class Viginer {
    String upperAlphabet = new String(new char[] {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И',
            'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ',
            'Э','Ю', 'Я'});
    String lowAlphabet = new String(new char[] {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и',
            'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'ю', 'я'});

    public String Encode(String message, String key) {
        String result = new String();
        int key_index = 0;
        key = key.toLowerCase();

        for (char symbol: message.toCharArray()) {
            if (symbol == ' ') {
                result += ' ';
            }
                else {
                    if (upperAlphabet.contains(new String(new char[] {symbol}))) {
                        int c = (upperAlphabet.indexOf(symbol) + upperAlphabet.indexOf(key.toCharArray()[key_index])) % upperAlphabet.length();
                        result += upperAlphabet.toCharArray()[c];
                        key_index++;
                        if ((key_index) == key.length()) key_index = 0;
                } else {
                        if (lowAlphabet.contains(new String(new char[] {symbol}))) {
                            int c = (lowAlphabet.indexOf(symbol) + lowAlphabet.indexOf(key.toCharArray()[key_index])) % lowAlphabet.length();
                            result += lowAlphabet.toCharArray()[c];
                            key_index++;
                            if ((key_index) == key.length()) key_index = 0;
                        }
                    }
            }
        }
        return result;
    }

   /* public String Decode(String message, String key) {

        message = message.toUpperCase();
        key = key.toUpperCase();

        String result = new String();

        int key_index = 0;

        for (char symbol:message.toCharArray()) {
            int p = (symbols.indexOf(symbol) + N
                    - symbols.indexOf(key.toCharArray()[key_index])) % N;

            result += symbols.toCharArray()[p];

            key_index++;

            if((key_index + 1) == key.length()) key_index = 0;

        }
     return result;
    }
*/
}
