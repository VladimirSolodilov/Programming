import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String stringBuffer;


        while (true) {
            try {
                System.out.println("1 - Добавить к аудиофайлу скрытое сообщение\n2 - Извлечь из аудиофайла скрытое сообщение\n0 - Завершить работу");

                String menuValue = scan.nextLine();
                System.out.println();
                if(menuValue.charAt(0) < 48 || menuValue.charAt(0) > 50 || menuValue.length() == 0) {
                    System.out.println("Введите корректное значение!\n");
                    continue;
                }
                switch (menuValue) {
                    case ("1") -> {
                        System.out.println("Введите текст, который будет скрыт в аудиофайле (Буквы латинского алфавита, цифры и другие символы кодировки ASCII):");
                        stringBuffer = scan.nextLine();
                        if (stringBuffer.length() == 0) {
                            System.out.println("Нельзя оставлять поле пустым!\n");
                            break;
                        }
                        boolean c = false;
                        for (int i = 0; i < stringBuffer.length(); i++) {
                            if (stringBuffer.charAt(i) > 127 || stringBuffer.charAt(i) < 32) {
                                c = true;
                                break;
                            }
                        }
                        if (c) {
                            System.out.println("Обнаружены неподдерживаемые символы!\n");
                            break;
                        }
                        stegFun(stringBuffer);
                    }
                    case ("2") -> unStegFun();
                    case ("0") -> {
                        scan.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Введите корректное значение\n");
                }
            }
            catch(Exception e) {
                System.out.println("Введите корректное значение\n");
            }
        }
    }


//	Добавление сообщения

    public static void stegFun(String text) {
        try(FileInputStream fin=new FileInputStream("source.wav");
            FileOutputStream fos=new FileOutputStream("result.wav"))
        {
            System.out.println("Исходный аудиофайл открыт");
            byte[] buffer = new byte[fin.available()];

            fin.read(buffer, 0, buffer.length);

            buffer[1000] = 19;
            buffer[1500] = 86;
            buffer[1986] = (byte)text.length();

            for(int i = 0; i < text.length(); i++) {
                buffer[2000 + (i * 2500)] = (byte)text.charAt(i);
            }

            System.out.println("Сообщение скрыто в исходном аудиофайле");

            fos.write(buffer, 0, buffer.length);
            System.out.println("Аудиофайл со скрытым сообщением успешно сохранен");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


//	Извлечение сообщения

    public static void unStegFun() {
        try(FileInputStream fin=new FileInputStream("result.wav"))
        {
            System.out.println("Аудиофайл открыт");
            byte[] buffer = new byte[fin.available()];

            fin.read(buffer, 0, buffer.length);

            if(buffer[1000] == 19 && buffer[1500] == 86) {
                System.out.println("Скрытое сообщение:");
                for(int i = 0; i < buffer[1986]; i++) {
                    System.out.print((char)(buffer[2000 + (i * 2500)]));

                }
                System.out.println("\n");
            }
            else {
                System.out.println("В аудиофайле нет скрытого сообщения\n");
            }

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}