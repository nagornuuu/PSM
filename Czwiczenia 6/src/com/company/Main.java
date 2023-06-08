package com.company;

public class Main {
    static double L = Math.PI;
    static int N = 10;
    static double DELTA_X = L / N;
    static double C = 1;

    public static void main(String[] args) {
        double[] y = new double[N + 1]; // wartosci y(x, t) w punktach x
        double[] v = new double[N + 1]; // predkosci w punktach x
        double[] a = new double[N + 1]; // przyspieszenia w punktach x

        // warunki poczatkowe
        for (int i = 0; i <= N; i++) {
            y[i] = Math.sin(i * DELTA_X) / 1000; // poczatkowa fala sinusa
            v[i] = 0;                            // poczatkowa predkosc 0
            a[i] = 0;                            // poczatkowe przyspieszenie 0
        }

        double t = 0;    // czas poczatkowy
        double dt = 0.2; // krok czasowy

        System.out.println("t\t\tEk\t\t\tEp\t\t\tEc"); // naglowek kolumn

        // rownanie struny
        while (t <= 3) {
            // predkosci i pozycje w punktach x
            for (int i = 0; i <= N; i++) {
                double vMid = v[i] + a[i] * dt / 2; // predkosc w punkcie x[i] po kroku polowy
                double yMid = y[i] + v[i] * dt / 2; // pozycja w punkcie x[i] po kroku polowy
                double aMid = 0; // x[i] po kroku polowy (na razie ustawione na 0)

                // x[i] po kroku polowy, korzystaac ze sredniej wazonej
                if (i > 0 && i < N) {
                    double yLeft = y[i - 1];
                    double yRight = y[i + 1];
                    double yMidPrev = yMid - vMid * dt / 2;
                    double yMidNext = yMid + vMid * dt / 2;
                    aMid = (yLeft - 2 * yMidPrev + 2 * yMidNext - yRight) / (2 * DELTA_X * DELTA_X) * C * C;
                }
                // predkosc i pozycję w punkcie x[i] po calym kroku
                v[i] += aMid * dt;
                y[i] += vMid * dt;
            }
            for (int i = 1; i < N; i++) {
                double yLeft = y[i - 1];
                double yRight = y[i + 1];
                double yMid = y[i];
                a[i] = (yLeft - 2 * yMid + yRight) / (DELTA_X * DELTA_X) * C * C;
            }

            // obliczamy energie
            double kineticEnergy = 0.0;
            double potentialEnergy = 0.0;

            for (int i = 0; i < N; i++) {
                double deltaY = y[i + 1] - y[i];
                potentialEnergy += deltaY * deltaY / (2 * DELTA_X);
                kineticEnergy += DELTA_X * v[i] * v[i] / 2;
            }

            // ostatni element ma predkosc 0, dlatego dodajy dla niego osobno
            kineticEnergy += DELTA_X * v[N] * v[N] / 2;
            double totalEnergy = kineticEnergy + potentialEnergy;

            // wypisujemy wartosci energii
            System.out.printf("%.2f\t%8f\t%.8f\t%.8f\n", t, kineticEnergy, potentialEnergy, totalEnergy);
            t += dt;
        }
    }
}