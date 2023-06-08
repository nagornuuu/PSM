package com.company;
import java.lang.Math;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj wartosc kata: ");
        double x = sc.nextDouble();

        // sprawdzenie, czy podano wartość w radianach czy w stopniach
        System.out.println("Jezeli podales wartosc kata w radianach, wpisz 1, jesli w stopniach, wpisz 2");
        int option = sc.nextInt();
        if (option == 2) {
            x = Math.toRadians(x);
        }
        // sprowadzenie do przedziału 0..2π
        x = x % (2 * Math.PI);

        // przeliczenie kąta do jednej z czterech pierwszych ćwiartek
        if (x > 0 && x <= Math.PI / 2) {
            x = x;
        }
        if (x > Math.PI / 2 && x <= Math.PI) {
            x = Math.PI - x;
        }
        if (x > Math.PI && x <= 3 * Math.PI / 2) {
            x = x - Math.PI;
        }
        if (x > 3 * Math.PI / 2 && x < 2 * Math.PI) {
            x = 2 * Math.PI - x;
        }

        double sinApprox = 0.0; // wartość przybliżona
        double sinActual = Math.sin(x); // wartość rzeczywista

        // obliczenie wartości przybliżonej dla kolejnych wyrazów szeregu
        for (int i = 1; i < 11; i++) {
            double term = Math.pow(-1, i - 1) * Math.pow(x, 2 * i - 1) / factorial(2 * i - 1);
            sinApprox += term;

            // wyświetlenie wyników i różnicy
            System.out.printf("Wyraz %d: %.10f\n", i, sinApprox);
            System.out.printf("Biblioteka matematyczna: %.10f\n", sinActual);
            System.out.printf("Różnica: %.10f\n\n", Math.abs(sinActual - sinApprox));
        }
    }

    // metoda obliczająca silnię
    public static double factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

}