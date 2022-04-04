package com.company;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MD5Hash md5Hash = new MD5Hash();
        System.out.print("Введите текст: ");
        String str = scanner.nextLine();
        System.out.println("MD5Hash(" + str + ") = " + md5Hash.hashing(str));
    }
}
