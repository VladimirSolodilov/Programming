package com.company;

public class Main {

    public static void main(String[] args) {
        Viginer viginer = new Viginer();

        String str = viginer.Encode("Корабли лавировали", "ров");
        System.out.println(str);
    }
}
