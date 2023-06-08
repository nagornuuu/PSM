package com.company;

public class lab03 {
    public static void main(String[] args) {
        double dt = 0.0001;
        double g = -10;
        double l = 1;
        double m = 1;
        double t = 0;
        double alpha = Math.PI / 4;
        double omega = 0;
        double x = l * Math.cos(alpha - Math.toRadians(90));
        double y = l * Math.sin(alpha - Math.toRadians(90));
        double h = y + l;
        double v = l * omega;
        double ep = Math.abs(m * g * h);
        double ek = m * Math.pow(v, 2) / 2;
        double ec = ep + ek;


        // Euler method
        double alphaEuler = alpha;
        double omegaEuler = omega;
        while (t < 10) {
            double k1 = omegaEuler;
            double k2 = g / l * Math.sin(alphaEuler);
            alphaEuler += k1 * dt;
            omegaEuler += k2 * dt;
            t += dt;
        }
        double energyEuler = calculateEnergy(alphaEuler, omegaEuler, g, l, m);
        System.out.println("Euler method: angle = " + alphaEuler + ", energy = " + energyEuler);

        // Improved Euler method (midpoint)
        double alphaImprovedEuler = alpha;
        double omegaImprovedEuler = omega;
        while (t < 20) {
            double k1 = omegaImprovedEuler;
            double k2 = g / l * Math.sin(alphaImprovedEuler);
            double k3 = g / l * Math.sin(alphaImprovedEuler + dt / 2 * k1);
            alphaImprovedEuler += dt * k1;
            omegaImprovedEuler += dt * (k2 + k3) / 2;
            t += dt;
        }
        double energyImprovedEuler = calculateEnergy(alphaImprovedEuler, omegaImprovedEuler, g, l, m);
        System.out.println("Improved Euler method: angle = " + alphaImprovedEuler + ", energy = " + energyImprovedEuler);


        // RK4 method (Runge Kutta)
        double alphaRK4 = alpha;
        double omegaRK4 = omega;
        while (t < 30) {
            double k1 = omegaRK4;
            double l1 = g / l * Math.sin(alphaRK4);
            double k2 = omegaRK4 + dt / 2 * l1;
            double l2 = g / l * Math.sin(alphaRK4 + dt / 2 * k1);
            double k3 = omegaRK4 + dt / 2 * l2;
            double l3 = g / l * Math.sin(alphaRK4 + dt / 2 * k2);
            double k4 = omegaRK4 + dt * l3;
            double l4 = g / l * Math.sin(alphaRK4 + dt * k3);
            alphaRK4 += dt / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            omegaRK4 += dt / 6 * (l1 + 2 * l2 + 2 * l3 + l4);
            t += dt;

        }


        double energyRK4 = calculateEnergy(alphaRK4, omegaRK4, g, l, m);
        System.out.println("RK4 method: angle = " + alphaRK4 + ", energy = " + energyRK4);
        System.out.println("Position (x,y) = (" + x + "," + y + ")");
        System.out.println("Height h = " + h);
        System.out.println("Velocity v = " + v);
        System.out.println("Potential energy ep = " + ep);
        System.out.println("Kinetic energy ek = " + ek);
        System.out.println("Total energy ec = " + ec);


    }


    private static double calculateEnergy(double angle, double omega, double g, double l, double m) {
        double kineticEnergy = 0.5 * m * Math.pow(l * omega, 2);
        double potentialEnergy = m * g * l * (1 - Math.cos(angle));
        return kineticEnergy + potentialEnergy;

    }


}
