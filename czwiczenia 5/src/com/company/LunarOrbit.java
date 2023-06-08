package com.company;

public class LunarOrbit {

    // Stałe
    static double G = 6.6743e-11;
    static double MS = 1.989e30;
    static double MZ = 5.972e24;
    static double RZS = 1.5e8;
    static double RZK = 3.844e5;

    // Parametry symulacji
    private static final double DT = 3600; // Czas kroku (w sekundach)
    private static final double DURATION = 72000; // Czas trwania symulacji (w sekundach)

    public static void main(String[] args) {
        // Pozycja i prędkość Ziemi
        double xz = RZS;
        double yz = 0;
        double vxz = 0;
        double vyz = Math.sqrt(G * MS / RZS);

        // Pozycja i prędkość Księżyca
        double xk = RZS + RZK;
        double yk = 0;
        double vxk = 0;
        double vyk = vyz + Math.sqrt(G * MZ / RZK);

        // Wyświetlanie początkowych pozycji
        System.out.println("Czas [s]\t XZ [km]\t\t\t\t\tYZ [km]\t\t\t\t\t\tXK [km]\t\t\t\t\t\tYK [km]");

        // Symulacja
        int t = 0;
        while (t < DURATION) {
            // Obliczanie przyspieszenia Ziemi
            double axz = -G * MS * xz / Math.pow(Math.sqrt(xz * xz + yz * yz), 3);
            double ayz = -G * MS * yz / Math.pow(Math.sqrt(xz * xz + yz * yz), 3);

            // Obliczanie przyspieszenia Księżyca
            double axk = -G * MZ * (xk - xz) / Math.pow(Math.sqrt((xk - xz) * (xk - xz) + (yk - yz) * (yk - yz)), 3)
                    - G * MS * xk / Math.pow(Math.sqrt(xk * xk + yk * yk), 3);
            double ayk = -G * MZ * (yk - yz) / Math.pow(Math.sqrt((xk - xz) * (xk - xz) + (yk - yz) * (yk - yz)), 3)
                    - G * MS * yk / Math.pow(Math.sqrt(xk * xk + yk * yk), 3);

            // Ulepszona metoda Eulera (MidPoint)
            double xzm = xz + vxz / 2 * DT;       // Środkowa wartość pozycji x Ziemi
            double yzm = yz + vyz / 2 * DT;       // Środkowa wartość pozycji y Ziemi
            double vxzm = vxz + axz / 2 * DT;     // Środkowa wartość prędkości x Ziemi
            double vyzm = vyz + ayz / 2 * DT;     // Środkowa wartość prędkości y Ziemi
            double xkm = xk + vxk / 2 * DT;       // Środkowa wartość pozycji x Księżyca
            double ykm = yk + vyk / 2 * DT;       // Środkowa wartość pozycji y Księżyca
            double vxkm = vxk + axk / 2 * DT;     // Środkowa wartość prędkości x Księżyca
            double vykm = vyk + ayk / 2 * DT;     // Środkowa wartość prędkości y Księżyca

            // Obliczanie przyspieszeń w środkowej pozycji
            double axzm = -G * MS * xzm / Math.pow(Math.sqrt(xzm * xzm + yzm * yzm), 3);
            double ayzm = -G * MS * yzm / Math.pow(Math.sqrt(xzm * xzm + yzm * yzm), 3);

            double axkm = -G * MZ * (xkm - xzm) / Math.pow(Math.sqrt((xkm - xzm) * (xkm - xzm) + (ykm - yzm) * (ykm - yzm)), 3)
                    - G * MS * xkm / Math.pow(Math.sqrt(xkm * xkm + ykm * ykm), 3);
            double aykm = -G * MZ * (ykm - yzm) / Math.pow(Math.sqrt((xkm - xzm) * (xkm - xzm) + (ykm - yzm) * (ykm - yzm)), 3)
                    - G * MS * ykm / Math.pow(Math.sqrt(xkm * xkm + ykm * ykm), 3);

            // Aktualizacja pozycji i prędkości Ziemi i Księżyca
            xz = xz + vxzm * DT;
            yz = yz + vyzm * DT;
            vxz = vxz + axzm * DT;
            vyz = vyz + ayzm * DT;

            xk = xk + vxkm * DT;
            yk = yk + vykm * DT;
            vxk = vxk + axkm * DT;
            vyk = vyk + aykm * DT;

            // Aktualizacja czasu
            t = (int) (t + DT);

            // Wyświetlanie wyniku
            System.out.println(t + "\t\t" + xz + "\t\t" + yz + "\t\t" + xk + "\t\t" + yk);
        }
    }
}
