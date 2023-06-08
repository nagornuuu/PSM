package com.company;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // Constants
        double h = 20;
        double a = 45;
        double m = 1;
        double g = 10;
        double r = 3;
        double lk = (2.0/5.0) * m * r * r;
        double dt = 0.05;
        double alpha = a * Math.PI/180;
        double acc = g * Math.sin(alpha) / (1 + lk / (m * r * r));
        double epsilon = acc / r;
        double Sx = 0;
        double Sy = 3;
        double V = 0;
        double DSx = 0;
        double DV = 0;
        double xsm_r, ysm_r;
        double b = 0;
        double w = 0;
        double Db = 0;
        double Dw = 0;
        double x, y;
        double Ek , Ep, Ec;

        for (double t = 0; t <= 3.35; t += dt) {
            // Update distance traveled and velocity
            Sx += DSx;
            V += DV;
            DSx = V * dt;
            DV = acc * dt;

            // Update xsm_r and ysm_r
            xsm_r = Sx * Math.cos(-alpha) - Sy * Math.sin(-alpha);
            ysm_r = Sx * Math.sin(-alpha) + Sy * Math.cos(-alpha) + h;

            // Update angle and angular velocity
            b += Db;
            w += Dw;
            Db = w * dt;
            Dw = epsilon * dt;

            // Update x, y, and height
            x = r * Math.cos(Math.toRadians(90) - b) + xsm_r;
            y = r * Math.sin(Math.toRadians(90) - b) + ysm_r;

            // Update energies
            h = ysm_r - r;
            Ep = m * g * h;
            Ek = m * V * V / 2 + lk * w * w / 2;
            Ec = Ep + Ek;

            // Print out results to console
            System.out.printf("t: %.6f\t Sx: %.6f\t Sy: %.6f\t V: %.6f\t DSx: %.6f\t" +
                            "DV: %.6f\t xsm_r: %.6f\t ysm_r: %.6f\t b: %.6f\t w: %.6f\t" +
                            "Db: %.6f\t Dw: %.6f\t x: %.6f\t y: %.6f\t h: %.6f\t t: %.6f\t" +
                            "Ep: %.6f\t Ek: %.6f\t Ec: %.6f\n",
                    t, Sx, Sy, V, DSx, DV, xsm_r, ysm_r,
                    b, w, Db, Dw, x, y, h, t, Ep, Ek, Ec);
        }
    }
}
