import java.util.Scanner;

public class Main extends AlgoritmDH {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlgoritmDH Alice = new AlgoritmDH();
        AlgoritmDH Bob = new AlgoritmDH();

        System.out.print("Введите сообщение: ");
        String sourceMessage = scanner.nextLine();

        String encryptedMessage = Bob.encryptMessage(sourceMessage, Bob.getFullKey());
        String decryptedMessage = Alice.decryptMessage(encryptedMessage, Alice.getFullKey(), Bob.getNumbers());

        System.out.print("\nИсходное сообщение: " + sourceMessage);
        try {
            while (true) {
                System.out.print("""
                    
                    .....Меню программы.......
                    1 - Сформировать ключи
                    2 - Зашифровать сообщение
                    3 - Расшифровать сообщение
                    4 - Выход из программы
                    Введите соответствующую цифру:\040""");
                int chkValue = scanner.nextInt();
                scanner.nextLine();

                switch (chkValue) {
                    case 1 -> {
                        Alice.setPublicKey(setKey());
                        Alice.setPrivateKey(setKey());

                        System.out.println("\nПубличный ключ Алисы: " + Alice.getPublicKey());
                        System.out.println("Частный ключ Алисы: " + Alice.getPrivateKey());

                        Bob.setPublicKey(setKey());
                        Bob.setPrivateKey(setKey());

                        System.out.println("\nПубличный ключ Боба: " + Bob.getPublicKey());
                        System.out.println("Частный ключ Боба: " + Bob.getPrivateKey());

                        Alice.setPartialKey(Alice.generatePartionKey(Alice.getPublicKey(), Bob.getPublicKey(), Alice.getPrivateKey()));
                        Bob.setPartialKey(Bob.generatePartionKey(Bob.getPublicKey(), Alice.getPublicKey(), Bob.getPrivateKey()));

                        System.out.println("\nЧасть ключа Алисы = " + Alice.getPartialKey());
                        System.out.println("Часть ключа Боба = " + Bob.getPartialKey());

                        Alice.setFullKey(Alice.generateFullKey(Bob.getPartialKey(), Bob.getPublicKey(), Alice.getPrivateKey()));
                        Bob.setFullKey(Bob.generateFullKey(Alice.getPartialKey(), Bob.getPublicKey(), Bob.getPrivateKey()));

                        System.out.println("\nПолный ключ Алисы = " + Alice.getFullKey());
                        System.out.println("Полный ключ Боба = " + Bob.getFullKey());
                    }
                    case 2 -> System.out.print("\nЗашифрованное сообщение: " + encryptedMessage);
                    case 3 -> System.out.print("\nРасшифрованное сообщение: " + decryptedMessage);
                    case 4 -> {
                        System.out.println("Выход из прогаммы...");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Перезапуск программы...");
                        main(new String[]{"main"});
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Перезапуск программы...");
            main(new String[]{"main"});
        }
    }
}
