package com.company;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MD5Hash md5Hash = new MD5Hash();
        String str = scanner.nextLine();
        System.out.println("md5 " + str + " = " + md5Hash.hashing(str));
    }
}
