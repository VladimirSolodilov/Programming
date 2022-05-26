import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //	Добавление сообщения
    public static void addSteganography(String inputText) {
        try (FileInputStream fileInputStream = new FileInputStream("source.jpg")) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("result.jpg")) {
                System.out.println("\nИсходная фотография открыта...");

                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer, 0, buffer.length);
                buffer[buffer.length - buffer.length / 4] = (byte) inputText.length();

                for (int i = 0; i < inputText.length(); i++) {
                    buffer[buffer.length / 2 + i] = (byte) inputText.charAt(i);
                }

                System.out.println("Сообщение (" + inputText + ") скрыто в исходной фотографии...");

                fileOutputStream.write(buffer, 0, buffer.length);

                System.out.println("Фотография со скрытым сообщением успешно сохранена...\n");
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
        catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    //	Извлечение сообщения
    public static void deleteSteganography() {
        try (FileInputStream fileInputStream = new FileInputStream("result.jpg")) {
            System.out.println("\nФотография открыта...");

            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer, 0, buffer.length);

            System.out.print("Скрытое сообщение:");
            for (int i = 0; i < buffer[buffer.length - buffer.length / 4]; i++) {
                System.out.print((char) (buffer[buffer.length / 2 + i]));
            }

            System.out.println("\n");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText;

        try {
            while (true) {
                System.out.print("""
                        ..............Меню программы...............
                        1 - Добавить в фотографию скрытое сообщение
                        2 - Извлечь из фотографии скрытое сообщение
                        3 - Завершить работу программы
                        Введите соответствующую цифру:""");

                int menuValue = scanner.nextInt();

                if (menuValue <= 0 || menuValue > 3) {
                    while (menuValue <= 0 || menuValue > 3) {
                        System.out.print("Введите корректное значение:");
                        menuValue = scanner.nextInt();
                    }
                }

                scanner.nextLine();

                switch (menuValue) {
                    case (1) -> {
                        System.out.print("Введите текст, который будет скрыт в фотографии (ASCII):");
                        inputText = scanner.nextLine();

                        if (inputText.length() == 0) {
                            while (inputText.length() == 0) {
                                System.out.print("Сообщение пустое! Введите сообщение ещё раз:");
                                inputText = scanner.nextLine();
                            }
                        }

                        for (int i = 0; i < inputText.length(); i++) {
                            if (inputText.charAt(i) < 32 || inputText.charAt(i) > 127) {
                                while (inputText.charAt(i) < 32 || inputText.charAt(i) > 127) {
                                    System.out.print("Обнаружены неподдерживаемые символы! Введите сообщение ещё раз:");
                                    inputText = scanner.nextLine();
                                }
                            }
                        }

                        addSteganography(inputText);
                    }
                    case (2) -> deleteSteganography();
                    case (3) -> {
                        scanner.close();
                        System.exit(0);
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("Исключение! Перезапуск программы...\n");
            main(new String[]{"main"});
        }
    }
}