package com.company;

public class Euler {
    int t;
    double Sx;
    double Sy;
    double Vx;
    double Vy;
    double DSx;
    double DSy;
    double DVx;
    double DVy;

    public Euler(int t, double Sx, double Sy, double Vx, double Vy, double DSx, double DSy, double DVx, double DVy) {
        this.t = t;
        this.Sx = Sx;
        this.Sy = Sy;
        this.Vx = Vx;
        this.Vy = Vy;
        this.DSx = DSx;
        this.DSy = DSy;
        this.DVx = DVx;
        this.DVy = DVy;


    }

    public String toString() {
        return "t = " + t + ", Sx = " + Sx + ", Sy = " + Sy + ", Vx = " + Vx + ", Vy = " + Vy + ", DSx = " + DSx + ", DSy = " + DSy + ", DVx = " + DVx + ", DVy = " + DVy;
    }
}