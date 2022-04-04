package com.company;

import java.util.InputMismatchException;
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
        Homophonic homophonic = new Homophonic();
        Viginer viginer = new Viginer();
        PseudoRandom pseudoRandom = new PseudoRandom();
        Permutation permutation = new Permutation();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;

        System.out.print("\nЛабораторная работа 1");

        try {
            while (check) {
                System.out.println("\n........................Меню программы........................");
                System.out.print("""
                        1 - Шифр Вижинера
                        2 - Шифр с гаммированием
                        3 - Шифр перестановки
                        4 - Омофонический шифр
                        5 - Выход программы
                        Для продолжения введите соответствующую цифру:\s""");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.print("""
                                \n........................Шифр Вижинера........................
                                1 - Шифрование
                                2 - Расшифрование
                                3 - Выход
                                Для продолжения введите соответствующую цифру:\s""");
                        int vintnerChoice = scanner.nextInt();

                        switch (vintnerChoice) {
                            case 1 -> {
                                scanner.nextLine();

                                System.out.print("Введите исходное сообщение: ");
                                viginer.setOriginalMessage(scanner.nextLine());

                                System.out.print("Введите ключ: ");
                                viginer.setKey(scanner.nextLine());

                                System.out.println("Зашифрованное сообщение: " + viginer.Encode(viginer.getOriginalMessage(), viginer.getKey()));
                            }
                            case 2 -> {
                                scanner.nextLine();

                                System.out.print("Введите зашифрованное сообщение: ");
                                viginer.setEncryptedMessage(scanner.nextLine());

                                System.out.print("Введите ключ: ");
                                viginer.setKey(scanner.nextLine());

                                System.out.println("Расшированное сообщение: " + viginer.Decode(viginer.getEncryptedMessage(), viginer.getKey()));
                            }
                            default -> {
                                System.out.println("Неправильный ввод. Выход в главное меню...");
                                main(new String[]{" "});
                            }
                        }
                    }
                    case 2 -> {
                        System.out.print("""
                                \n........................Шифр с гаммированием........................
                                1 - Шифрование
                                2 - Расшифрование
                                3 - Выход
                                Для продолжения введите соответствующую цифру:\s""");
                        int scalingChoice = scanner.nextInt();

                        switch (scalingChoice) {
                            case 1 -> {
                                System.out.print("Введите длину ключа: ");
                                int a = scanner.nextInt();

                                System.out.print("""
                                        Выберите язык, введя соответствующую цифру
                                        1 - russian,
                                        2 - english:\s""");

                                int c = scanner.nextInt();
                                if (c != 1 && c != 2) {
                                    while (c != 1 && c != 2) {
                                        System.out.print ("Введите цифру ещё раз: ");
                                        c = scanner.nextInt();
                                    }
                                }

                                if (c == 1) {
                                    pseudoRandom.setPseudoRandomKey(a, (int) (Math.random() * 1000), "russian");
                                } else {
                                    pseudoRandom.setPseudoRandomKey(a, (int) (Math.random() * 1000), "english");
                                }

                                System.out.println("Сгенерированный случайный ключ: " + pseudoRandom.getPseudoRandomKey());

                                scanner.nextLine();

                                System.out.print("Введите сообщение для шифрования: ");
                                String message = scanner.nextLine();

                                System.out.println("Зашифрованное сообщение: " + viginer.Encode(message, String.valueOf(pseudoRandom.getPseudoRandomKey())));
                            }
                            case 2 -> {
                                scanner.nextLine();

                                System.out.print("Введите ключ: ");
                                String pseudoRandomKey = scanner.nextLine();

                                System.out.print("Введите зашифрованное сообщение: ");
                                String message = scanner.nextLine();

                                System.out.println("Расшифрованное сообщение: " + viginer.Decode(message, pseudoRandomKey));
                            }
                            default -> {
                                System.out.println("Неправильный ввод. Выход в главное меню...");
                                main(new String[]{" "});
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("""
                                \n........................Шифр перестановки........................
                                1 - Шифрование
                                2 - Расшифрование
                                3 - Выход
                                Для продолжения введите соответствующую цифру:\s""");

                        int permutationChoice = scanner.nextInt();
                        switch (permutationChoice) {
                            case 1 -> {
                                System.out.print("Введите длину ключа: ");
                                int numbersLength = scanner.nextInt();

                                int[] numbers = new int[numbersLength];
                                System.out.print("Введите ключ: ");

                                for (int i = 0; i < numbersLength; i++) {
                                    numbers[i] = scanner.nextInt();
                                }

                                permutation.setKey(numbers);

                                scanner.nextLine();

                                System.out.print("Введите сообщение для шифрования: ");

                                permutation.setOriginalMessage(scanner.nextLine());

                                System.out.print("Зашифрованное сообщение: " + permutation.Encode(permutation.getOriginalMessage()) + "\n");
                            }
                            case 2 -> {
                                scanner.nextLine();

                                System.out.print("Введите сообщение для расшифрования: ");
                                permutation.setEncryptedMessage(scanner.nextLine());

                                System.out.print("Введите длину ключа: ");
                                int numbersLength = scanner.nextInt();

                                int[] numbers = new int[numbersLength];

                                System.out.print("Введите ключ: ");

                                for (int i = 0; i < numbersLength; i++) {
                                    numbers[i] = scanner.nextInt();
                                }

                                permutation.setKey(numbers);

                                System.out.print("Расшифрованное сообщение: " + permutation.Decode(permutation.getEncryptedMessage()));
                            }
                            default -> {
                                System.out.println("Неправильный ввод. Выход в главное меню...");
                                main(new String[]{" "});
                            }
                        }
                    }
                    case 4 -> {
                        System.out.print("""
                                \n........................Омофонический шифр........................
                                1 - Шифрование
                                2 - Расшифрование
                                3 - Выход
                                Для продолжения введите соответствующую цифру:\s""");

                        int permutationChoice = scanner.nextInt();
                        switch (permutationChoice) {
                            case 1 -> {
                                scanner.nextLine();

                                System.out.print("Введите сообщение для расшифрования: ");
                                String str = scanner.nextLine();

                                homophonic.Encode(str);
                            }

                            case 2 -> {
                                scanner.nextLine();

                                System.out.print("Введите сообщение для расшифрования: ");
                                String str2 = scanner.nextLine();

                                homophonic.Decode(str2);
                            }
                            default -> {
                                System.out.println("Неправильный ввод. Выход в главное меню...");
                                main(new String[]{" "});
                            }
                        }
                    }

                    case 5 -> check = false;
                    default -> {
                        System.out.println("Неправильный ввод. Выход в главное меню...");
                        main(new String[]{" "});
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Неправильный ввод. Выход в главное меню...");
            main(new String[]{" "});
        }
    }
}
