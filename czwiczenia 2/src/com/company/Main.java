package com.company;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        double dt = 0.01;
        double gx = 0;
        double gy = -10;
        double mass = 1;
        double q = 0.2;

        LinkedList<Euler> basic = new LinkedList<>();
        LinkedList<Euler> improved = new LinkedList<>();

        int t = 0;
        double Sx = 0;
        double Sy = 0;
        double Vx = 10;
        double Vy = 10;
        double DSx = Vx * dt;
        double DSy = Vy * dt;
        double DVx = gx * dt;
        double DVy = gy * dt;

        double Sx2 = 0;
        double Sy2 = 0;
        double Vx2 = 10;
        double Vy2 = 10;
        double Vx3 = Vx2 + gx * dt/2;
        double Vy3 = Vy2 + gy * dt/2;
        double DSx2 = Vx3 * dt;
        double DSy2 = Vy3 * dt;
        double DVx2 = gx * dt;
        double DVy2 = gy * dt;

        for (double i = 0; i <= 2; i += dt) {
            Sx += DSx;
            Sy += DSy;
            Vx += DVx;
            Vy += DVy;
            DSx = Vx * dt;
            DSy = Vy * dt;
            DVx = gx * dt;
            DVy = gy * dt;


            Sx2 += DSx2;
            Sy2 += DSy2;
            Vx2 += DVx2;
            Vy2 += DVy2;
            Vx3 = Vx2 + gx * dt/2;
            Vy3 = Vy2 + gy * dt/2;
            DVx2 = gx * dt;
            DVy2 = gy * dt;
            DSx2 = Vx3 * dt;
            DSy2 = Vy3 * dt;

            improved.add(new Euler(t,Sx2, Sy2, Vx2, Vy2, DSx2, DSy2, DVx2, DVy2));
            basic.add(new Euler(t,Sx, Sy, Vx, Vy, DSx, DSy, DVx, DVy));
            t++;
        }
        //improved.forEach(System.out::println);
        basic.forEach(System.out::println);
    }
}

