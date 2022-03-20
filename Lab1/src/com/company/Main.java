package com.company;

import java.util.Scanner;

public class Main {
    protected final String russianAlphabet = new String(new char[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И',
            'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ',
            'Э', 'Ю', 'Я'});
    protected final String englishAlphabet = new String(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'});

    protected final String specialSymbols = new String(new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', ';',
            ':', '"', '|', '/', '?', '.', ',', '№', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});

    public static void main(String[] args) {
        Viginer viginer = new Viginer();
        PseudoRandom pseudoRandom = new PseudoRandom();
        Permutation permutation = new Permutation();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;

        System.out.println("Лабораторная работа 1");

        while (check) {
            System.out.println("\n........................Меню программы........................");
            System.out.print("1 - Шифр Вижинера\n" +
                    "2 - Шифр с гаммированием\n" +
                    "3 - Шифр перестановки\n" +
                    "4 - Омофонический шифр\n" +
                    "5 - Выход программы\n" +
                    "Для продолжения введите соответствующую цифру: ");
            int choise = scanner.nextInt();

            switch (choise) {
                case 1: {
                    System.out.print("\n........................Шифр Вижинера........................\n" +
                            "1 - Шифрование\n" +
                            "2 - Расшифрование\n" +
                            "3 - Выход\n" +
                            "Для продолжения введите соответствующую цифру: ");
                    int viginerChoise = scanner.nextInt();

                    switch (viginerChoise) {
                        case 1: {
                            scanner.nextLine();

                            System.out.print("Введите исходное сообщение: ");
                            viginer.setOriginalMessage(scanner.nextLine());

                            System.out.print("Введите ключ: ");
                            viginer.setKey(scanner.nextLine());

                            System.out.println("Зашифрованное сообщение: " + viginer.Encode(viginer.getOriginalMessage(), viginer.getKey()));

                            break;
                        }
                        case 2: {
                            scanner.nextLine();

                            System.out.print("Введите зашифрованное сообщение: ");
                            viginer.setEncryptedMessage(scanner.nextLine());

                            System.out.print("Введите ключ: ");
                            viginer.setKey(scanner.nextLine());

                            System.out.println("Расшированное сообщение: " + viginer.Decode(viginer.getEncryptedMessage(), viginer.getKey()));

                            break;
                        }

                        case 3: {
                            break;
                        }

                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.print("\n........................Шифр с гаммированием........................\n" +
                            "1 - Шифрование\n" +
                            "2 - Расшифрование\n" +
                            "3 - Выход\n" +
                            "Для продолжения введите соответствующую цифру: ");
                    int scalingChoise = scanner.nextInt();

                    switch (scalingChoise) {
                        case 1: {
                            String languageCheck = new String();

                            System.out.print("Введите длину ключа: ");
                            int a = scanner.nextInt();

                            System.out.print("Введите любое число: ");
                            int b = scanner.nextInt();

                            System.out.print("Выберите язык, введя соответствующую цифру" +
                                    "\n1 - russian, 2 - english: ");
                            int c = scanner.nextInt();

                            if (c == 1) languageCheck = "russian";
                            else if (c == 2) languageCheck = "english";

                            pseudoRandom.setPseudoRandomKey(a, b, languageCheck);
                            System.out.println("Сгенерированный случайный ключ: " + pseudoRandom.getPseudoRandomKey());

                            scanner.nextLine();

                            System.out.print("Введите сообщение для шифрования: ");
                            String message = scanner.nextLine();

                            System.out.println("Зашифрованное сообщение: " + viginer.Encode(message, pseudoRandom.getPseudoRandomKey()));

                            break;
                        }
                        case 2: {
                            scanner.nextLine();

                            System.out.print("Введите ключ: ");
                            String pseudoRandomKey = scanner.nextLine();

                            System.out.print("Введите зашифрованное сообщение: ");
                            String message = scanner.nextLine();

                            System.out.println("Расшифрованное сообщение: " + viginer.Decode(message, pseudoRandomKey));

                            break;
                        }
                        case 3: {
                            break;
                        }

                        default: {
                            break;
                        }
                    }

                    break;
                }

                case 3: {
                    System.out.print("\n........................Шифр перестановки........................\n" +
                            "1 - Шифрование\n" +
                            "2 - Расшифрование\n" +
                            "3 - Выход\n" +
                            "Для продолжения введите соответствующую цифру: ");
                    int permutationChoise = scanner.nextInt();

                    switch (permutationChoise) {
                        case 1: {
                            System.out.print("Введите длину ключа: ");
                            int numbersLength = scanner.nextInt();

                            int[] numbers = new int[numbersLength];

                            System.out.print("Введите числовую последовательность: ");

                            for (int i = 0; i < numbersLength; i++) {
                                numbers[i] = scanner.nextInt();
                            }

                            permutation.setKey(numbers);

                            scanner.nextLine();

                            System.out.print("Введите сообщение для шифрования: ");
                            String message = scanner.nextLine();

                            System.out.print("Зашифрованное сообщение: " + permutation.Encode(message) + "\n");

                            break;
                        }
                        case 2: {

                        }

                        case 3 : {
                            break;
                        }

                        default: {
                            break;
                        }
                    }

                    break;
                }

                case 5: {
                    check = false;
                    continue;
                }

                default: {
                    break;
                }

            }


        }






    }
}
